package hu.bme.aut.android.together.features.shared.eventmessage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.databinding.ItemEventNewsBinding
import hu.bme.aut.android.together.model.EventNewsMessage

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
            tvAuthorEventNews.text = "by " + representedNews.author
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

    inner class EventNewsViewHolder(val binding: ItemEventNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

}