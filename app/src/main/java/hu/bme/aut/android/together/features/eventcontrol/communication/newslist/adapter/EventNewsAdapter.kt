package hu.bme.aut.android.together.features.eventcontrol.communication.newslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.ItemEventNewsBinding
import hu.bme.aut.android.together.model.presentation.EventNews
import hu.bme.aut.android.together.model.presentation.comparator.EventNewsComparator

class EventNewsAdapter(val onNewsItemClick: (EventNews) -> Unit) :
    ListAdapter<EventNews, EventNewsAdapter.EventNewsViewHolder>(EventNewsComparator) {

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
            val representedNews = getItem(position)
            tvTitleEventNews.text = representedNews.title
            tvAuthorEventNews.text =
                holder.binding.root.resources.getString(R.string.by_author, representedNews.author)
            tvMessageEventNews.text = representedNews.message
            setCardOnClickBehaviour(cardItemEventNews, representedNews)
        }
    }

    /**
     * Sets the card's onclick behaviour. Fires the method reference, that was given
     * as parameter in the instance's constructor by passing [represented] parameter.
     * @param card the Card, which onclick behaviour is being set by this function.
     * @param represented the object which holds the card's data.
     */
    private fun setCardOnClickBehaviour(card: CardView, represented: EventNews) {
        card.setOnClickListener {
            onNewsItemClick(represented)
        }
    }

    inner class EventNewsViewHolder(val binding: ItemEventNewsBinding) :
        RecyclerView.ViewHolder(binding.root)
}