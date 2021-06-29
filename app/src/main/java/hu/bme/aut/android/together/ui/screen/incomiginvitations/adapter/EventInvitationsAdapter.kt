package hu.bme.aut.android.together.ui.screen.incomiginvitations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.ItemEventInvitationBinding
import hu.bme.aut.android.together.ui.screen.incomiginvitations.adapter.EventInvitationsAdapter.EventNewsViewHolder
import hu.bme.aut.android.together.ui.model.EventInvitation
import hu.bme.aut.android.together.ui.model.comparator.EventInvitationComparator

/**
 * This adapter can be used to represent a list of messages.
 * For the messages' layout, see [EventNewsViewHolder] class's documentation.
 * @param onItemClick this function will be fired when an element will be clicked of the
 * RecyclerView, that uses this adapter.
 */
class EventInvitationsAdapter(val onItemClick: (represented: EventInvitation) -> Unit) :
    ListAdapter<EventInvitation, EventInvitationsAdapter.EventNewsViewHolder>(
        EventInvitationComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventNewsViewHolder {
        return EventNewsViewHolder(
            ItemEventInvitationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventNewsViewHolder, position: Int) {
        with(holder.binding) {
            val representedNews = getItem(position)
            tvTitleEventInvitation.text = representedNews.title
            tvAuthorEventInvitation.text =
                holder.binding.root.resources.getString(R.string.by_author, representedNews.author)
            tvMessageEventInvitation.text = representedNews.message
            setCardOnClickBehaviour(cardItemEventInvitation, representedNews)
        }
    }

    /**
     * Sets the card's onclick behaviour. Fires the method reference, that was given
     * as parameter in the instance's constructor by passing [represented] parameter.
     * @param card the Card, which onclick behaviour is being set by this function.
     * @param represented the object which holds the card's data.
     */
    private fun setCardOnClickBehaviour(card: CardView, represented: EventInvitation) {
        card.setOnClickListener {
            onItemClick(represented)
        }
    }

    /**
     * The ViewHolder uses the [R.layout.item_event_news] layout.
     */
    inner class EventNewsViewHolder(val binding: ItemEventInvitationBinding) :
        RecyclerView.ViewHolder(binding.root)

}