package hu.bme.aut.android.together.ui.model.comparator

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.android.together.ui.model.EventInvitation

object EventInvitationComparator : DiffUtil.ItemCallback<EventInvitation>() {
    override fun areItemsTheSame(oldItem: EventInvitation, newItem: EventInvitation): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EventInvitation, newItem: EventInvitation): Boolean {
        return oldItem == newItem
    }
}