package hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.databinding.FragmentPageableDetailSetterContainerBinding
import hu.bme.aut.android.together.features.addevent.pager.pagercallback.EventAddingPagerContainer
import hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.factory.PageableDetailSetterFragmentFactory
import hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.modificationcallback.ModificationCallback
import kotlin.properties.Delegates

/**
 * This [Fragment]'s main responsibility to provide an user interface for swiping the pages
 * of the event creation process. Use [newInstance] factory method to create this class's instances.
 * This fragment's parent should implement the
 * [hu.bme.aut.android.together.features.addevent.interfaces.EventAddingPagerContainer] interface.
 */
class PageableDetailSetterContainerFragment : Fragment(), ModificationCallback {

    companion object {
        private const val CONTAINED_FRAGMENT_ID_KEY = "CONTAINED_FRAGMENT_ID_KEY"
        private const val SHOW_BACK_KEY = "SHOW_BACK_KEY"
        private const val SHOW_FORWARD_KEY = "SHOW_FORWARD_KEY"

        /**
         * Factory method for this class.
         * @param containedFragmentFactoryOrdinal this implementation uses
         * [hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory.PageableDetailSetterFragmentFactory]
         * enum class to create the contained fragments. The contained fragment's factory's ordinal
         * value should be passed as this parameter.
         * @param showBackKey with this option it can be set whether a back button should be
         * available in the created fragment. It's default value is true.
         * @param showForwardKey with this option it can be set whether a forward button should be
         * available in the created fragment. It's default value is true.
         * @return the created [PageableDetailSetterContainerFragment] instance.
         */
        @JvmStatic
        fun newInstance(
            containedFragmentFactoryOrdinal: Int,
            showBackKey: Boolean = true,
            showForwardKey: Boolean = true
        ): PageableDetailSetterContainerFragment {
            return PageableDetailSetterContainerFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTAINED_FRAGMENT_ID_KEY, containedFragmentFactoryOrdinal)
                    putBoolean(SHOW_BACK_KEY, showBackKey)
                    putBoolean(SHOW_FORWARD_KEY, showForwardKey)
                }
            }
        }
    }

    //TODO Using dependency injection pattern would be better than this solution.
    private lateinit var eventAddingPagerContainer: EventAddingPagerContainer

    private lateinit var pageableDetailSetterFragmentFactory: PageableDetailSetterFragmentFactory

    private var showBackNavigationButton by Delegates.notNull<Boolean>()

    private var showForwardNavigationButton by Delegates.notNull<Boolean>()

    private lateinit var binding: FragmentPageableDetailSetterContainerBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setUpPagerContainer()
    }

    /**
     * Sets [eventAddingPagerContainer] to hold the parentFragment's reference.
     */
    private fun setUpPagerContainer() {
        eventAddingPagerContainer = parentFragment as EventAddingPagerContainer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveArguments()
    }

    /**
     * Retrieves every argument from arguments. These arguments are the following:
     * - Display options of the navigation buttons
     * - The ordinal value of the used [hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory.PageableDetailSetterFragmentFactory]
     * instance.
     */
    private fun retrieveArguments() {
        retrieveNavigationButtonArgs()
        retrieveContainedFragmentFactory()
    }

    private fun retrieveNavigationButtonArgs() {
        retrieveBackNavigationButtonArgs()
        retrieveForwardNavigationButtonArgs()
    }

    private fun retrieveBackNavigationButtonArgs() {
        showBackNavigationButton = arguments?.getBoolean(SHOW_BACK_KEY) ?: true
    }

    private fun retrieveForwardNavigationButtonArgs() {
        showForwardNavigationButton = arguments?.getBoolean(SHOW_FORWARD_KEY) ?: true
    }

    /**
     * Retrieves from the arguments the ordinal value of the passed
     * [hu.bme.aut.android.together.features.addevent.fragment.pagerelement.factory.PageableDetailSetterFragmentFactory]
     * instance. Using this value the function initializes [pageableDetailSetterFragmentFactory].
     */
    private fun retrieveContainedFragmentFactory() {
        val ordinal = arguments?.getInt(CONTAINED_FRAGMENT_ID_KEY)
            ?: throw IllegalArgumentException(
                "EventDetailSetterContainerFragment didn't receive" +
                        " ContainedFragmentFactory ordinal value in arguments Bundle! Use static" +
                        " newInstance() factory method to instantiate this fragment!"
            )
        require(ordinal >= 0 && ordinal < PageableDetailSetterFragmentFactory.values().size)
        pageableDetailSetterFragmentFactory = PageableDetailSetterFragmentFactory.values()[ordinal]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageableDetailSetterContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addContainedFragment()
        setNavigationButtonsBehaviour()
    }

    /**
     * Adds the contained fragment to the Fragment's FragmentContainerView, using [pageableDetailSetterFragmentFactory].
     */
    private fun addContainedFragment() {
        childFragmentManager.beginTransaction()
            .replace(
                binding.fcvEventDetailAdder.id,
                pageableDetailSetterFragmentFactory.getFragment()
            )
            .commit()
    }

    private fun setNavigationButtonsBehaviour() {
        setBackNavigatingButtonBehaviour()
        setForwardNavigatingButtonBehaviour()
    }

    /**
     * Sets the back navigation button's behaviour.
     * If [showBackNavigationButton] is true, the button will be displayed; in the other case
     * it won't be displayed. If the button should be displayed, its on click behaviour will
     * be the following: using [eventAddingPagerContainer] it signals, that the previous
     * page should be shown.
     */
    private fun setBackNavigatingButtonBehaviour() {
        pageableDetailSetterFragmentFactory.ordinal.let { position ->
            if (showBackNavigationButton)
                binding.swipeButtonBar.ibtnLeft.setOnClickListener {
                    eventAddingPagerContainer.pageTo(position - 1)
                }
            else
                binding.swipeButtonBar.ibtnLeft.visibility = View.GONE
        }
    }

    /**
     * Sets the forward navigation button's behaviour.
     * If [showForwardNavigationButton] is true, the button will be displayed; in the other case
     * it won't be displayed. If the button should be displayed, its on click behaviour will
     * be the following: using [eventAddingPagerContainer] it signals, that the next
     * page should be shown.
     */
    private fun setForwardNavigatingButtonBehaviour() {
        pageableDetailSetterFragmentFactory.ordinal.let { position ->
            if (showForwardNavigationButton)
                binding.swipeButtonBar.ibtnRight.setOnClickListener {
                    eventAddingPagerContainer.pageTo(position + 1)
                }
            else
                binding.swipeButtonBar.ibtnRight.visibility = View.GONE
        }
    }

    override fun getCurrentEventTitle(): String {
        return eventAddingPagerContainer.getCurrentEventTitle()
    }

    override fun modifyEventTitle(newTitle: String) {
        eventAddingPagerContainer.modifyEventTitle(newTitle)
    }

    override fun isEventInCurrentlyPrivateMode(): Boolean {
        return eventAddingPagerContainer.isEventInCurrentlyPrivateMode()
    }

    override fun changeEventPrivateMode(isPrivate: Boolean) {
        eventAddingPagerContainer.changeEventPrivateMode(isPrivate)
    }

    override fun isMaxParticipantCountRuleSet(): Boolean {
        return eventAddingPagerContainer.isMaxParticipantCountRuleSet()
    }

    override fun changeMaxParticipantCountRule(isMaxParticipantCountRuleSet: Boolean) {
        eventAddingPagerContainer.changeMaxParticipantCountRule(isMaxParticipantCountRuleSet)
    }

    override fun getMaxParticipantCount(): Int {
        return eventAddingPagerContainer.getMaxParticipantCount()
    }

    override fun setMaxParticipantCount(maxParticipantCount: Int) {
        eventAddingPagerContainer.setMaxParticipantCount(maxParticipantCount)
    }

    override fun changeJoinRequestAutoAcceptRule(isJoinRequestAutoAcceptAllowed: Boolean) {
        eventAddingPagerContainer.changeJoinRequestAutoAcceptRule(isJoinRequestAutoAcceptAllowed)
    }

    override fun isJoinRequestAutoAcceptAllowed(): Boolean {
        return eventAddingPagerContainer.isJoinRequestAutoAcceptAllowed()
    }

    override fun getCategory(): String {
        return eventAddingPagerContainer.getCategory()
    }

    override fun changeCategory(newCategory: String) {
        eventAddingPagerContainer.changeCategory(newCategory)
    }

    override fun getLocation(): String {
        return eventAddingPagerContainer.getLocation()
    }

    override fun setLocation(newLocation: String) {
        eventAddingPagerContainer.setLocation(newLocation)
    }

    override fun getDescription(): String {
        return eventAddingPagerContainer.getDescription()
    }

    override fun setDescription(newDescription: String) {
        eventAddingPagerContainer.setDescription(newDescription)
    }

    override fun getStartDateString(): String {
        return eventAddingPagerContainer.getStartDateString()
    }

    override fun setStartDateString(dateString: String) {
        eventAddingPagerContainer.setStartDateString(dateString)
    }

    override fun getEndDateString(): String {
        return eventAddingPagerContainer.getEndDateString()
    }

    override fun setEndDateString(dateString: String) {
        eventAddingPagerContainer.setEndDateString(dateString)
    }

    override fun getStartTimeString(): String {
        return eventAddingPagerContainer.getStartTimeString()
    }

    override fun setStartTimeString(dateString: String) {
        eventAddingPagerContainer.setStartTimeString(dateString)
    }

    override fun getEndTimeString(): String {
        return eventAddingPagerContainer.getEndTimeString()
    }

    override fun setEndTimeString(dateString: String) {
        eventAddingPagerContainer.setEndTimeString(dateString)
    }
}