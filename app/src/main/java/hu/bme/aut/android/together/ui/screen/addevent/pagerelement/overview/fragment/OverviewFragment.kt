package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.overview.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentOverviewBinding
import hu.bme.aut.android.together.ui.screen.addevent.pager.pagercallback.EventAddingPagerContainer
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.overview.viewmodel.*
import hu.bme.aut.android.together.ui.model.UploadResponse

/**
 * This Fragment displays the event's data set by the user.
 * The container Fragment must implement the [hu.bme.aut.android.together.features.addevent.pager.pagercallback.EventAddingPagerContainer]
 * interface.
 */
@AndroidEntryPoint
class OverviewFragment : RainbowCakeFragment<OverviewState, OverviewViewModel>() {

    /**
     * The pageable container which contains this fragment.
     */
    private lateinit var eventAddingPagerContainer: EventAddingPagerContainer

    private val overviewViewModel: OverviewViewModel by viewModels()

    private lateinit var binding: FragmentOverviewBinding

    override fun provideViewModel(): OverviewViewModel = overviewViewModel

    override fun render(viewState: OverviewState) {
        when(viewState){
            is ReviewState -> {
                showOverview()
            }
            is Loading -> {
                showLoading()
            }
            is EventUploaded -> {
                showOverview()
                onEventUpload(viewState.response)
            }
        }.exhaustive
    }

    private fun showOverview() {
        with(binding) {
            cpiOverviewLoading.isVisible = false
            clContent.isVisible = true
        }
    }

    private fun showLoading() {
        with(binding) {
            clContent.isVisible = false
            cpiOverviewLoading.isVisible = true
        }
    }

    private fun onEventUpload(response: UploadResponse) {
        if(response.wasSuccessful) {
            eventAddingPagerContainer.eventCreated()
        } else {
            Snackbar.make(requireView(), response.errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setPagerContainer()
    }

    /**
     * Sets [eventAddingPagerContainer] to parentFragment.
     */
    private fun setPagerContainer() {
        //TODO Using dependency injection pattern (somehow) would be better than this.
        eventAddingPagerContainer = parentFragment as EventAddingPagerContainer
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
    }

    /**
     * Sets the toolbar navigation icon behaviour and menu.
     */
    private fun setUpToolbar() {
        binding.tbAddEventOverview.let { toolbar ->
            setUpToolbarNavigationBehaviour(toolbar)
            setUpToolBarMenu(toolbar)
        }
    }

    /**
     * Sets the Toolbar NavigationIcon's behaviour. When the icon is clicked, [eventAddingPagerContainer]
     * is signaled, that the previous page should be displayed.
     */
    private fun setUpToolbarNavigationBehaviour(toolbar: Toolbar) {
        with(toolbar) {
            setNavigationOnClickListener {
                eventAddingPagerContainer.pageBack()
            }
        }
    }

    /**
     * Sets the toolbar menu. The used menu resource is [R.menu.add_event_overview_menu].
     * The two menu item can be used to mark the event adding process completed, or discard
     * this event. When the event is tried to be discarded, the user's confirmation is necessary
     * to do so.
     */
    private fun setUpToolBarMenu(toolbar: Toolbar) {
        with(toolbar) {
            inflateMenu(R.menu.add_event_overview_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionCompleteEventAdding -> {
                        viewModel.uploadEvent(eventAddingPagerContainer.getCurrentAddableEvent())
                        true
                    }
                    R.id.actionCancelEventAdding -> {
                        displayEventCancellationConfirmationDialog()
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
    }

    /**
     * When the user tries to discard the event, this function is called to show it a confirmation
     * [AlertDialog]. If the user confirms that the event should be discarded, [eventAddingPagerContainer]
     * will be notified about this decision.
     */
    private fun displayEventCancellationConfirmationDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.title_dialog_event_cancellation))
            setMessage(getString(R.string.message_dialog_event_cancellation))
            setPositiveButton(getString(R.string.option_discard_event)) { _: DialogInterface, _: Int ->
                eventAddingPagerContainer.eventDiscarded()
            }
            setNegativeButton(getString(R.string.option_cancel)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
        }.show()
    }

    override fun onStart() {
        super.onStart()
        initializeDisplayedContent()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeDisplayedContent() {
        with(binding) {
            eventAddingPagerContainer.let {
                tvEventName.text = it.getCurrentEventTitle()
                tvVisibility.text =
                    if (it.isEventInCurrentlyPrivateMode()) resources.getString(R.string.visibility_private) else resources.getString(
                        R.string.visibility_public
                    )
                if (it.isMaxParticipantCountRuleSet()) {
                    tvMaxParticipantCount.isVisible = true
                    tvMaxParticipantCount.text = resources.getString(
                        R.string.max_participant_count_template,
                        it.getMaxParticipantCount()
                    )
                } else {
                    tvMaxParticipantCount.isVisible = false
                }
                tvAutoJoinRequest.text = resources.getString(
                    R.string.auto_join_request_template,
                    resources.getString(if (it.isJoinRequestAutoAcceptAllowed()) R.string.state_on else R.string.state_off)
                )
                tvCategory.text = it.getCategory()
                tvFromDate.text = "${it.getStartDateString()} ${it.getStartTimeString()}"
                tvToDate.text = "${it.getEndDateString()} ${it.getEndTimeString()}"
                tvEventPlace.text = it.getLocation()
                tvDescription.text = it.getDescription()
            }
        }
    }
}