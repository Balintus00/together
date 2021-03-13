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

class OverviewFragment : Fragment() {

    private lateinit var eventAddingPagerContainer: EventAddingPagerContainer

    private lateinit var binding: FragmentOverviewBinding

    //TODO It's really important to document, that the parentFragment must implement PagerContainer interface
    //TODO Using dependency injection pattern would be better than that.
    override fun onAttach(context: Context) {
        super.onAttach(context)
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

    private fun setUpToolbar() {
        binding.tbAddEventOverview.let { toolbar ->
            setUpToolbarNavigation(toolbar)
            setUpToolBarMenu(toolbar)
        }
    }
    
    private fun setUpToolbarNavigation(toolbar: Toolbar) {
        with(toolbar) {
            setNavigationIcon(R.drawable.ic_action_arrow_back)
            setNavigationOnClickListener {
                eventAddingPagerContainer.pageBack()
            }
        }
    }
    
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

    private fun createEventSelected() {
        //TODO data should be validated and verified
        eventAddingPagerContainer.eventCreated()
    }

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