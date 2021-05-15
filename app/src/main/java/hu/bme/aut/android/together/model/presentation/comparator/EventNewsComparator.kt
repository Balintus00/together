package hu.bme.aut.android.together.model.presentation.comparator

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.android.together.model.presentation.EventNews

object EventNewsComparator: DiffUtil.ItemCallback<EventNews>() {
    override fun areItemsTheSame(oldItem: EventNews, newItem: EventNews): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EventNews, newItem: EventNews): Boolean {
        return oldItem == newItem
    }
}