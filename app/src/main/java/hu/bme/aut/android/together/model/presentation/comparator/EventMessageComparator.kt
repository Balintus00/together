package hu.bme.aut.android.together.model.presentation.comparator

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.android.together.model.presentation.EventMessage

object EventMessageComparator : DiffUtil.ItemCallback<EventMessage>() {
    override fun areItemsTheSame(oldItem: EventMessage, newItem: EventMessage): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EventMessage, newItem: EventMessage): Boolean {
        return oldItem == newItem
    }
}