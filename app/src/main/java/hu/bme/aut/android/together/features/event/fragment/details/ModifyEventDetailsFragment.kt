package hu.bme.aut.android.together.features.event.fragment.details

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentModifyEventDetailsBinding
import hu.bme.aut.android.together.features.event.dialogfragment.EventAttributeModifierDialogFragment
import java.util.*

/**
 * On this fragment the user can modify an event.
 */
class ModifyEventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentModifyEventDetailsBinding

    /**
     * This [androidx.activity.result.ActivityResultLauncher] instance can be used to launch some
     * kind of camera activity to get the image of the event. The picture returned will be set
     * as the content of the fragment's ImageView widget.
     */
    private val cameraActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    setCameraActivityImage(intent)
                }
            }
        }

    private fun setCameraActivityImage(intent: Intent) {
        intent.extras?.let { extras ->
            binding.ivModifiedEventImage.setImageBitmap(extras.get("data") as Bitmap)
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
            if (result.resultCode == Activity.RESULT_OK) {
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
        binding.ivModifiedEventImage.setImageBitmap(
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
        binding = FragmentModifyEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIWidgets()
    }

    private fun setUpUIWidgets() {
        setUpToolBarBehaviour()
        setFabBehaviour()
        setSimpleModifiableTextViewsBehaviour()
        setPhotoModifierButtonBehaviour()
        setCategorySpinner()
        setDateTextViewBehaviour(binding.tvFromDate)
        setDateTextViewBehaviour(binding.tvToDate)
    }

    /**
     * Sets the Toolbar's navigation icon onclick behaviour.
     * When the icon is clicked, a dialog will be displayed, that asks the user's confirmation
     * for this action.
     */
    private fun setUpToolBarBehaviour() {
        with(binding.tbModifyEvent) {
            setNavigationOnClickListener {
                displayAreYouSureDialogBeforeClosing()
            }
        }
    }

    /**
     * Displays an [AlertDialog], that asks for the user's confirmation of the modification
     * action's abort. If the user abort the modification, the BackStack will be popped.
     */
    private fun displayAreYouSureDialogBeforeClosing() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Are sure?")
            setMessage("Every modification will be discarded.")
            setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog?.dismiss() }
            setPositiveButton("Discard") { _, _ ->
                findNavController().popBackStack()
            }
        }.show()
    }

    /**
     * The FAB on this is used to save the modification of the event.
     * Clicking this FAB saves the event, then pops the BackStack to navigate back to the
     * event's details screen.
     */
    private fun setFabBehaviour() {
        //TODO sending actual data to backend
        binding.fabModifyEvent.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setSimpleModifiableTextViewsBehaviour() {
        with(binding) {
            setOnClickDialogToAppearOnTextView(tvEventName, tvTitleNameAttribute.text.toString())
            setOnClickDialogToAppearOnTextView(
                tvEventLocation,
                tvTitleLocationAttribute.text.toString()
            )
            setOnClickDialogToAppearOnTextView(
                tvDescription,
                tvTitleDescriptionAttribute.text.toString()
            )
        }
    }

    /**
     * Displays an [EventAttributeModifierDialogFragment] which can be used to modify the content of TextView given as
     * parameter.
     * @param textView the TextView, of which content can be modified using the dialog.
     */
    private fun setOnClickDialogToAppearOnTextView(textView: TextView, attributeName: String) {
        textView.setOnClickListener {
            EventAttributeModifierDialogFragment(attributeName).show(
                parentFragmentManager,
                ""
            )
        }
    }

    /**
     * Sets the photo modifier button's onclick behaviour. After the button was clicked, an [AlertDialog]
     * is displayed to user, on that it can choose the preferred image taking method, or just can simply
     * close this dialog.
     */
    private fun setPhotoModifierButtonBehaviour() {
        binding.btnModifyImage.setOnClickListener {
            displayImageTakingOptionMenu()
        }
    }

    /**
     * Displays an [AlertDialog], that contains the menu to choose the preferred image modification method.
     * Two options are available: taking a photo, picking a photo from the gallery.
     */
    private fun displayImageTakingOptionMenu() {
        val imageChoiceOptionArray =
            resources.getStringArray(R.array.add_event_photo_upload_options)
        androidx.appcompat.app.AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.title_modification_event_image))
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

    /**
     * Sets the contained spinner's content with category options. It also sets the spinner's
     * current value to represent the event's current category.
     */
    private fun setCategorySpinner() {
        with(binding.spinnerCategory) {
            adapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.event_category_types_array)
            )
        }
        //TODO initializing its location to represent the currently set option
    }

    /**
     * Set the date containing TextView's onclick behaviour. After TextView was clicked, a [DatePickerDialog],
     * then a [TimePickerDialog] will be displayed, then the TextView's content will be refreshed
     * with the freshly chosen values.
     * @param textView the date containing TextView, which behaviour will be set.
     */
    @SuppressLint("SetTextI18n") //In this it works perfectly; using a placeholder resource is unnecessary
    private fun setDateTextViewBehaviour(textView: TextView) {
        textView.setOnClickListener {
            val calendar = Calendar.getInstance()
            //TODO these strings initial value will be the currently set date and time
            var dateString: String
            var timeString: String
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    dateString =
                        getString(R.string.date_year_month_day, year, month, day)
                    TimePickerDialog(
                        requireContext(),
                        { _, hour, minute ->
                            timeString =
                                getString(R.string.time_hour_minute, hour, minute)
                            textView.text = "$dateString $timeString"
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            //TODO modifying the data
        }
    }
}