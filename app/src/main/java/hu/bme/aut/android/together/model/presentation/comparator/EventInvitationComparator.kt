package hu.bme.aut.android.together.model.presentation.comparator

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.android.together.model.presentation.EventInvitation

object EventInvitationComparator : DiffUtil.ItemCallback<EventInvitation>() {
    override fun areItemsTheSame(oldItem: EventInvitation, newItem: EventInvitation): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EventInvitation, newItem: EventInvitation): Boolean {
        return oldItem == newItem
    }
}