package hu.bme.aut.android.together.features.addevent.fragment

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentPhotoUploaderBinding

//TODO The ImageView's initial picture should be set, which is specified by the event's chosen category
class PhotoUploaderFragment : Fragment() {

    private lateinit var binding: FragmentPhotoUploaderBinding

    companion object {
        private const val REQUEST_CODE_TAKE_PICTURE = 0
        private const val REQUEST_CODE_CHOOSE_PICTURE = 1
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
        setWidgetsBehaviour()
    }

    private fun setWidgetsBehaviour() {
        setImageChooserButtonBehaviour()
        setNextButtonBehaviour()
    }

    //TODO should be refactored later to be cleaner
    private fun setImageChooserButtonBehaviour() {
        val imageChoiceOptionArray =
            resources.getStringArray(R.array.add_event_photo_upload_options)
        binding.btnAddEventPhotoChooser.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(getString(R.string.add_event_upload_photo_dialog_title))
                setItems(imageChoiceOptionArray) { dialog: DialogInterface, i: Int ->
                    when (i) {
                        0 -> {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE)
                        }
                        1 -> Intent(
                            Intent.ACTION_PICK,
                        ).apply {
                            type = "image/*"
                        }.let { intent ->
                            startActivityForResult(intent, REQUEST_CODE_CHOOSE_PICTURE)
                        }
                        else -> {
                            dialog.dismiss()
                        }
                    }
                }
            }.show()
        }
    }

    private fun setNextButtonBehaviour() {
        binding.btnAddEventPhotoUploadNext.setOnClickListener {
            PhotoUploaderFragmentDirections.actionPhotoUploaderFragmentToDateSetterFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    //TODO should be refactored later to be cleaner
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                REQUEST_CODE_TAKE_PICTURE -> {
                    if (resultCode == RESULT_OK) {
                        data?.let { intent ->
                            intent.extras?.let { extras ->
                                binding.ivAddEventPhotoUploader.setImageBitmap(extras.get("data") as Bitmap)
                            }
                        }
                    }
                }
                REQUEST_CODE_CHOOSE_PICTURE -> {
                    if (resultCode == RESULT_OK) {
                        data?.let {
                            try {
                                binding.ivAddEventPhotoUploader.setImageBitmap(
                                    BitmapFactory.decodeStream(
                                        requireActivity().contentResolver.openInputStream(data.data!!)
                                    )
                                )
                            } catch (exception: Exception) {
                                Log.w("Together!", exception.stackTrace.toString())
                                Snackbar.make(
                                    binding.root,
                                    getString(R.string.error_add_event_photo_upload_file_not_found),
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

}