package hu.bme.aut.android.together.features.addevent.pager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.databinding.FragmentAddEventPagerBinding
import hu.bme.aut.android.together.features.addevent.pager.adapter.AddEventPagerAdapter
import hu.bme.aut.android.together.features.addevent.pager.viewmodel.AddEventPagerState
import hu.bme.aut.android.together.features.addevent.pager.viewmodel.AddEventPagerViewModel
import hu.bme.aut.android.together.features.addevent.pager.viewmodel.Loaded
import hu.bme.aut.android.together.features.addevent.pager.pagercallback.EventAddingPagerContainer
import kotlin.math.roundToInt

/**
 * This Fragment contains the fragments, that can be used to create new events. These fragments
 * are connected by the [ViewPager2] widget of this Fragment instance, which connects those fragments.
 * This Fragment also contains a [com.google.android.material.progressindicator.LinearProgressIndicator]
 * instance, which displays the progress of event creation process.
 */
@AndroidEntryPoint
class AddEventPagerFragment : RainbowCakeFragment<AddEventPagerState, AddEventPagerViewModel>(),
    EventAddingPagerContainer {

    companion object {
        //TODO this will be changed
        private const val mockedEventId = 1L
    }

    private val addEventPagerFragmentViewModel: AddEventPagerViewModel by viewModels()

    private lateinit var pagerAdapter: AddEventPagerAdapter

    private lateinit var binding: FragmentAddEventPagerBinding

    override fun provideViewModel(): AddEventPagerViewModel = addEventPagerFragmentViewModel

    override fun render(viewState: AddEventPagerState) {
        when(viewState){
            is Loaded -> { }
        }.exhaustive
    }

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
     * [hu.bme.aut.android.together.features.eventcontrol.details.fragment.EventDetailsFragment]
     * instance, which displays the created event's details.
     */
    private fun navigateToCreatedEvent() {
        AddEventPagerFragmentDirections.actionGlobalEventDetailsGraph(
            mockedEventId
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

    override fun getCurrentEventTitle(): String {
        return viewModel.addableEvent.value!!.title
    }

    override fun modifyEventTitle(newTitle: String) {
        viewModel.addableEvent.value!!.title = newTitle
    }

    override fun isEventInCurrentlyPrivateMode(): Boolean {
        return viewModel.addableEvent.value!!.isPrivate
    }

    override fun changeEventPrivateMode(isPrivate: Boolean) {
        viewModel.addableEvent.value!!.isPrivate = isPrivate
    }

    override fun isMaxParticipantCountRuleSet(): Boolean {
        return viewModel.addableEvent.value!!.isMaximumParticipantCountRuleSet
    }

    override fun changeMaxParticipantCountRule(isMaxParticipantCountRuleSet: Boolean) {
        viewModel.addableEvent.value!!.isMaximumParticipantCountRuleSet = isMaxParticipantCountRuleSet
    }

    override fun getMaxParticipantCount(): Int {
        return viewModel.addableEvent.value!!.maximumParticipantCount
    }

    override fun setMaxParticipantCount(maxParticipantCount: Int) {
        viewModel.addableEvent.value!!.maximumParticipantCount = maxParticipantCount
    }

    override fun changeJoinRequestAutoAcceptRule(isJoinRequestAutoAcceptAllowed: Boolean) {
        viewModel.addableEvent.value!!.isJoinRequestAutoAcceptAllowed = isJoinRequestAutoAcceptAllowed
    }

    override fun isJoinRequestAutoAcceptAllowed(): Boolean {
        return viewModel.addableEvent.value!!.isJoinRequestAutoAcceptAllowed
    }

    override fun getCategory(): String {
        return viewModel.addableEvent.value!!.category
    }

    override fun changeCategory(newCategory: String) {
        viewModel.addableEvent.value!!.category = newCategory
    }
}