package hu.bme.aut.android.together.features.shared.eventlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.ItemEventRowBinding
import hu.bme.aut.android.together.model.EventShortInfo

class EventListAdapter(val onEventItemClick: () -> Unit) : RecyclerView.Adapter<EventListAdapter.EventListViewHolder>() {

    // TODO mocked data, should be removed later
    private val eventShortInfoList = listOf(
        EventShortInfo("Kristóf's birthday party", "Budapest", "Friday, febr 13 - 18:00", ""),
        EventShortInfo("Budapest sightseeing tour", "Budapest", "Saturday, febr 14 - 18:00", "")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        val binding =
            ItemEventRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        with(eventShortInfoList[position]){
            holder.binding.tvEventName.text = name
            holder.binding.tvEventPlace.text = location
            holder.binding.tvEventTime.text = time
            // TODO this should be later replaced with actual image resource using
            holder.binding.ivEventItem.setImageResource(R.mipmap.ic_launcher)
            holder.binding.clEventRowItem.setOnClickListener {
                onEventItemClick()
            }
        }
    }

    override fun getItemCount(): Int {
        return eventShortInfoList.size
    }

    inner class EventListViewHolder(val binding: ItemEventRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}