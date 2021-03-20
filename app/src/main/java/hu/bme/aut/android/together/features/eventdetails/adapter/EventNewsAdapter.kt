package hu.bme.aut.android.together.features.eventdetails.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.databinding.ItemEventNewsBinding
import hu.bme.aut.android.together.model.EventNewsMessage

class EventNewsAdapter(val context: Context) : RecyclerView.Adapter<EventNewsAdapter.EventNewsViewHolder>() {

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
            AlertDialog.Builder(context).apply {
                setTitle(representedNews.title)
                setMessage(representedNews.message)
                setPositiveButton("BACK") { dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.dismiss()
                }
            }.show()
        }
    }

    override fun getItemCount(): Int {
        return eventNewsList.size
    }

    inner class EventNewsViewHolder(val binding: ItemEventNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

}