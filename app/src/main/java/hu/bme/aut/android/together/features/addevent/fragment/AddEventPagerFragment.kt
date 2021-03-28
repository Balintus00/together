package hu.bme.aut.android.together.features.addevent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import hu.bme.aut.android.together.databinding.FragmentAddEventPagerBinding
import hu.bme.aut.android.together.features.addevent.adapter.AddEventPagerAdapter
import hu.bme.aut.android.together.features.addevent.interfaces.EventAddingPagerContainer
import kotlin.math.roundToInt

/**
 * This Fragment contains the fragments, that can be used to create new events. These fragments
 * are connected by the [ViewPager2] widget of this Fragment instance, which connects those fragments.
 * This Fragment also contains a [com.google.android.material.progressindicator.LinearProgressIndicator]
 * instance, which displays the progress of event creation process.
 */
class AddEventPagerFragment : Fragment(), EventAddingPagerContainer {

    companion object {
        //TODO this mocked data will be removed later, and actual data will be used
        //organiser, private, limitedParticipantCount, isParticipant
        private val mockedCreatedEventSettingsArray = arrayOf(
            true, false, true, true
        )
    }

    private lateinit var pagerAdapter: AddEventPagerAdapter

    private lateinit var binding: FragmentAddEventPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEventPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPagerAdapter()
    }

    /**
     * Initializes the contained [ViewPager2] widget's [androidx.viewpager2.adapter.FragmentStateAdapter],
     * and also sets every behaviour related to the paging.
     */
    private fun setUpPagerAdapter() {
        pagerAdapter = AddEventPagerAdapter(this)
        binding.vp2AddEventPager.adapter = pagerAdapter
        setPageChangingBehaviour()
    }

    /**
     * This function sets the contained [ViewPager2] widget's page changing behaviour.
     * When the page is changed, the contained [com.google.android.material.progressindicator.LinearProgressIndicator]'s
     * state will also be changed. On the last page, the progress indicator is not displayed.
     */
    private fun setPageChangingBehaviour() {
        binding.vp2AddEventPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == pagerAdapter.itemCount - 1)
                    binding.lpiAddEventProgress.visibility = View.GONE
                else {
                    binding.lpiAddEventProgress.visibility = View.VISIBLE
                    binding.lpiAddEventProgress.progress = calculateCurrentPagingProgress(position)
                }
            }
        })
    }

    /**
     * This function calculates the rounded percentage value of the current progress of the event
     * creation process.
     * @param position the current position of the [ViewPager2] widget.
     * @return the calculated rounded percentage of the paging's current progress.
     */
    private fun calculateCurrentPagingProgress(position: Int): Int {
        return ((position + 1).toFloat() / pagerAdapter.itemCount.toFloat() * 100).roundToInt()
    }

    /**
     * Sets the contained [ViewPager2] widget's position to the given parameter.
     * @param position the position that will be set as the position of the contained [ViewPager2]
     * widget.
     */
    override fun pageTo(position: Int) {
        with(binding.vp2AddEventPager) {
            require(position >= 0 && position < adapter!!.itemCount)
            currentItem = position
        }
    }

    /**
     * Decrements the [ViewPager2] instance's position.
     */
    override fun pageBack() {
        with(binding.vp2AddEventPager) {
            currentItem -= 1
        }
    }

    override fun eventCreated() {
        //TODO uploading the data
        //TODO this function will be called if the uploading was successful
        navigateToCreatedEvent()
    }

    /**
     * This function navigates to a
     * [hu.bme.aut.android.together.features.eventdetails.fragment.details.EventDetailsFragment]
     * instance, which displays the created event's details.
     */
    private fun navigateToCreatedEvent() {
        AddEventPagerFragmentDirections.actionGlobalEventDetailsGraph(
            isOrganiser = mockedCreatedEventSettingsArray[0],
            isPrivate = mockedCreatedEventSettingsArray[1],
            isParticipantCountLimited = mockedCreatedEventSettingsArray[2],
            isParticipant = mockedCreatedEventSettingsArray[3]
        ).let { action ->
            findNavController().navigate(action)
        }
    }

    /**
     * Pops the BackStack, if the event should be discarded.
     */
    override fun eventDiscarded() {
        findNavController().popBackStack()
    }
}