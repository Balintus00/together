package hu.bme.aut.android.together.ui.model.comparator

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.android.together.ui.model.EventShortInfo

object EventShortInfoComparator: DiffUtil.ItemCallback<EventShortInfo>() {
    override fun areItemsTheSame(oldItem: EventShortInfo, newItem: EventShortInfo): Boolean {
        return oldItem.eventId == newItem.eventId
    }

    override fun areContentsTheSame(oldItem: EventShortInfo, newItem: EventShortInfo): Boolean {
        return oldItem == newItem
    }
}