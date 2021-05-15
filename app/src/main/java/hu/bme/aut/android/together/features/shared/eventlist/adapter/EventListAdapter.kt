package hu.bme.aut.android.together.features.shared.eventlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.ItemEventRowBinding
import hu.bme.aut.android.together.model.presentation.EventShortInfo
import hu.bme.aut.android.together.model.presentation.comparator.EventShortInfoComparator

/**
 * TODO it's no longer actual.
 * This [RecyclerView.Adapter] implementation can be used to set [RecyclerView] widget to represent
 * a list of events. The adapter stores the represented data in a list of [EventShortInfo] instances.
 */
class EventListAdapter(val onEventItemClick: (position: Int) -> Unit) :
    ListAdapter<EventShortInfo, EventListAdapter.EventListViewHolder>(EventShortInfoComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        val binding =
            ItemEventRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        with(holder.binding) {
            val representedEventShortInfo = getItem(position)
            holder.binding.tvEventName.text = representedEventShortInfo.name
            holder.binding.tvEventPlace.text = representedEventShortInfo.location
            holder.binding.tvEventTime.text = representedEventShortInfo.startDate
            Glide.with(ivEventItem).load(representedEventShortInfo.imageUrl).into(ivEventItem)
            holder.binding.clEventRowItem.setOnClickListener {
                onEventItemClick(position)
            }
        }
    }

    /**
     * The ViewHolder uses the [R.layout.item_event_row] layout.
     */
    inner class EventListViewHolder(val binding: ItemEventRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}