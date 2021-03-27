package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentOverviewBinding
import hu.bme.aut.android.together.features.addevent.interfaces.EventAddingPagerContainer

/**
 * This Fragment displays the event's data set by the user.
 * The container Fragment must implement the [hu.bme.aut.android.together.features.addevent.interfaces.EventAddingPagerContainer]
 * interface.
 */
class OverviewFragment : Fragment() {

    /**
     * The pageable container which contains this fragment.
     */
    private lateinit var eventAddingPagerContainer: EventAddingPagerContainer

    private lateinit var binding: FragmentOverviewBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setPagerContainer()
    }

    /**
     * Sets [eventAddingPagerContainer] to parentFragment.
     */
    private fun setPagerContainer() {
        //TODO Using dependency injection pattern would be better than this.
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
                        createEventSelected()
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
     * Signaling [eventAddingPagerContainer] that user completed setting the event.
     */
    private fun createEventSelected() {
        eventAddingPagerContainer.eventCreated()
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
}