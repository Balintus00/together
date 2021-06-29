package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.photo.fragment

import android.app.Activity.RESULT_OK
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentPhotoUploaderBinding

//TODO The ImageView's initial picture should be set, which is specified by the event's chosen category
/**
 * This [Fragment] provides an user interface, that can be used by the user to set the image of
 * the event which is under creation.
 */
class PhotoUploaderFragment : Fragment() {
    private lateinit var binding: FragmentPhotoUploaderBinding

    /**
     * This [androidx.activity.result.ActivityResultLauncher] instance can be used to launch some
     * kind of camera activity to get the image of the event. The picture returned will be set
     * as the content of the fragment's ImageView widget.
     */
    private val cameraActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { intent ->
                    setCameraActivityImage(intent)
                }
            }
        }

    private fun setCameraActivityImage(intent: Intent) {
        intent.extras?.let { extras ->
            binding.ivAddEventPhotoUploader.setImageBitmap(extras.get("data") as Bitmap)
        }
    }

    /**
     * This [androidx.activity.result.ActivityResultLauncher] instance can be used to launch some
     * kind of photo picker activity to get the image of the event. The picture returned will be set
     * as the content of the fragment's ImageView widget. If some kind of error happens, the user
     * will be notified about it using a [Snackbar].
     */
    private val photoPickerActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                with(result) {
                    data?.let { intent ->
                        tryToSetPhotoPickerActivityImage(intent)
                    }
                }
            }
        }

    private fun tryToSetPhotoPickerActivityImage(intent: Intent) {
        try {
            setPhotoPickerActivityImage(intent)
        } catch (exception: Exception) {
            Log.i("Together!", exception.stackTraceToString())
            displayPhotoPickerImageSettingError()
        }
    }

    private fun setPhotoPickerActivityImage(intent: Intent) {
        binding.ivAddEventPhotoUploader.setImageBitmap(
            BitmapFactory.decodeStream(
                requireActivity().contentResolver.openInputStream(intent.data!!)
            )
        )
    }

    private fun displayPhotoPickerImageSettingError() {
        Snackbar.make(
            binding.root,
            getString(R.string.error_add_event_photo_upload_file_not_found),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoUploaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageChooserButtonBehaviour()
    }

    /**
     * Sets the Photo Chooser button behaviour. If the button is clicked, a menu will be displayed,
     * which the user can use to pick the method of the image choosing for the event.
     */
    private fun setImageChooserButtonBehaviour() {
        binding.btnAddEventPhotoChooser.setOnClickListener {
            displayImageTakingOptionMenu()
        }
    }

    /**
     * Displays an [AlertDialog], that contains the menu to choose the preferred image taking method.
     * Two options are available: taking a photo, picking a photo from the gallery.
     */
    private fun displayImageTakingOptionMenu() {
        val imageChoiceOptionArray =
            resources.getStringArray(R.array.add_event_photo_upload_options)
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.add_event_upload_photo_dialog_title))
            setItems(imageChoiceOptionArray) { dialog: DialogInterface, i: Int ->
                when (i) {
                    0 -> tryToUseCamera()
                    1 -> startPhotoPickerActivity()
                    else -> dialog.dismiss()
                }
            }
        }.show()
    }

    /**
     * This function tries to start a camera activity. If some kind of error happens, the user
     * will be notified using a [Snackbar] message.
     */
    private fun tryToUseCamera() {
        if (requireContext().packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            startCameraActivity()
        } else {
            Snackbar.make(
                binding.root,
                getString(R.string.error_camera_not_found),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Launches a camera activity. Use [tryToUseCamera] method instead to handle possible errors.
     */
    private fun startCameraActivity() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityLauncher.launch(intent)
    }

    /**
     * Launches an activity, that lets the user use some kind of gallery to pick the photo.
     */
    private fun startPhotoPickerActivity() {
        Intent(
            Intent.ACTION_PICK,
        ).apply {
            type = "image/*"
        }.let { intent ->
            photoPickerActivityLauncher.launch(intent)
        }
    }
}