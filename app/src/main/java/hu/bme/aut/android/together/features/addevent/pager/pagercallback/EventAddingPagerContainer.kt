package hu.bme.aut.android.together.features.addevent.pager.pagercallback

/**
 * The provided interface can be used to signal events about the event creation.
 */
interface EventAddingPagerContainer {

    /**
     * Notifies the implementing instance, that the displayed page should be changed
     * to the page, which is represented by the given parameter.
     * @param position the position of the page which should be displayed.
     */
    fun pageTo(position: Int)

    /**
     * Notifies the implementing instance, that the previous page to the currently displayed
     * should be displayed.
     */
    fun pageBack()

    /**
     * Notifies the implementing instance, that the event, that is being created, is ready to
     * be added.
     */
    fun eventCreated()

    /**
     * Notifies the implementing instance, that the event, that is being created, should be discarded.
     */
    fun eventDiscarded()

    fun getCurrentEventTitle(): String

    fun modifyEventTitle(newTitle: String)

    fun isEventInCurrentlyPrivateMode(): Boolean

    fun changeEventPrivateMode(isPrivate: Boolean)

    fun isMaxParticipantCountRuleSet(): Boolean

    fun changeMaxParticipantCountRule(isMaxParticipantCountRuleSet: Boolean)

    fun getMaxParticipantCount(): Int

    fun setMaxParticipantCount(maxParticipantCount: Int)

    fun changeJoinRequestAutoAcceptRule(isJoinRequestAutoAcceptAllowed: Boolean)

    fun isJoinRequestAutoAcceptAllowed(): Boolean

    fun getCategory(): String

    fun changeCategory(newCategory: String)

    fun getLocation(): String

    fun setLocation(newLocation: String)

    fun getDescription(): String

    fun setDescription(newDescription: String)

    fun getStartDateString(): String
    fun setStartDateString(dateString: String)
    fun getEndDateString(): String
    fun setEndDateString(dateString: String)
    fun getStartTimeString(): String
    fun setStartTimeString(dateString: String)
    fun getEndTimeString(): String
    fun setEndTimeString(dateString: String)
}