package hu.bme.aut.android.together.features.shared.eventmessage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.ItemEventNewsBinding
import hu.bme.aut.android.together.model.EventNewsMessage

/**
 * This adapter can be used to represent a list of messages.
 * For the messages' layout, see [EventNewsViewHolder] class's documentation.
 */
class EventMessagesAdapter(val onItemClick: (representedNews: EventNewsMessage) -> Unit) :
    RecyclerView.Adapter<EventMessagesAdapter.EventNewsViewHolder>() {

    // TODO Actual data will be used later
    private val eventNewsList = listOf(
        EventNewsMessage(
            "What kind of gift should you bring?",
            "KR1ST0F",
            "Lorem ipsum dolor sit amet."
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventNewsViewHolder {
        return EventNewsViewHolder(
            ItemEventNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventNewsViewHolder, position: Int) {
        with(holder.binding) {
            val representedNews = eventNewsList[position]
            tvTitleEventNews.text = representedNews.title
            tvAuthorEventNews.text =
                holder.binding.root.resources.getString(R.string.by_author, representedNews.author)
            tvMessageEventNews.text = representedNews.message
            setCardOnClickBehaviour(cardItemEventNews, representedNews)
        }
    }

    private fun setCardOnClickBehaviour(card: CardView, representedNews: EventNewsMessage) {
        card.setOnClickListener {
            onItemClick(representedNews)
        }
    }

    override fun getItemCount(): Int {
        return eventNewsList.size
    }

    /**
     * The ViewHolder uses the [R.layout.item_event_news] layout.
     */
    inner class EventNewsViewHolder(val binding: ItemEventNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

}