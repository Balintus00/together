package hu.bme.aut.android.together.mock

import androidx.fragment.app.Fragment
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.settercontainer.modificationcallback.ModificationCallback

class FakeModificationCallbackFragment : Fragment(), ModificationCallback {

    private var currentEventTitle = ""
    private var isInPrivateMode = false
    private var maxParticipantRuleSet = false
    private var participantCount = 0
    private var isAutoJoinAllowed = false
    private var category = ""
    private var location = ""
    private var description = ""
    private var startDate = "Lorem"
    private var startTime = "Lorem"
    private var endDate = "Lorem"
    private var endTime = "Lorem"

    override fun getCurrentEventTitle(): String {
        return currentEventTitle
    }

    override fun modifyEventTitle(newTitle: String) {
        currentEventTitle = newTitle
    }

    override fun isEventInCurrentlyPrivateMode(): Boolean {
        return isInPrivateMode
    }

    override fun changeEventPrivateMode(isPrivate: Boolean) {
        isInPrivateMode = isPrivate
    }

    override fun isMaxParticipantCountRuleSet(): Boolean {
        return maxParticipantRuleSet
    }

    override fun changeMaxParticipantCountRule(isMaxParticipantCountRuleSet: Boolean) {
        maxParticipantRuleSet = isMaxParticipantCountRuleSet
    }

    override fun getMaxParticipantCount(): Int {
        return participantCount
    }

    override fun setMaxParticipantCount(maxParticipantCount: Int) {
        participantCount = maxParticipantCount
    }

    override fun changeJoinRequestAutoAcceptRule(isJoinRequestAutoAcceptAllowed: Boolean) {
        isAutoJoinAllowed = isJoinRequestAutoAcceptAllowed
    }

    override fun isJoinRequestAutoAcceptAllowed(): Boolean {
        return isAutoJoinAllowed
    }

    override fun getCategory(): String {
        return category
    }

    override fun changeCategory(newCategory: String) {
        category = newCategory
    }

    override fun getLocation(): String {
        return location
    }

    override fun setLocation(newLocation: String) {
        location = newLocation
    }

    override fun getDescription(): String {
        return description
    }

    override fun setDescription(newDescription: String) {
        description = newDescription
    }

    override fun getStartDateString(): String {
        return startDate
    }

    override fun setStartDateString(dateString: String) {
        startDate = dateString
    }

    override fun getEndDateString(): String {
        return endDate
    }

    override fun setEndDateString(dateString: String) {
        endDate = dateString
    }

    override fun getStartTimeString(): String {
        return startTime
    }

    override fun setStartTimeString(dateString: String) {
       startTime = dateString
    }

    override fun getEndTimeString(): String {
        return endTime
    }

    override fun setEndTimeString(dateString: String) {
        endTime = dateString
    }
}