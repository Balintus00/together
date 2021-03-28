package hu.bme.aut.android.together.features.eventdetails.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.ItemEventNewsBinding
import hu.bme.aut.android.together.databinding.ItemEventQABinding
import hu.bme.aut.android.together.model.EventNewsMessage
import hu.bme.aut.android.together.model.EventQuestionAndAnswer

/**
 * This adapter can be used to handle the list of an event questions and answers.
 * @param context context of the Fragment that contains the RecyclerView widget, which adapter's
 * reference set to this class's instance.
 */
class EventQAAdapter(val context: Context) :
    RecyclerView.Adapter<EventQAAdapter.EventQAViewHolder>() {

    // TODO Actual data will be used later
    private val questionAndAnswerList = listOf(
        EventQuestionAndAnswer(
            EventNewsMessage(
                "Where can I park with my" +
                        " car nearby?", "B4RN1", "Lorem ipsum dolor sit amet?"
            ), EventNewsMessage(
                "There is a huge parking" +
                        " lot around the house!", "KR1ST0F", "Lorem ipsum dolor sit amet!"
            )
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventQAViewHolder {
        return EventQAViewHolder(
            ItemEventQABinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventQAViewHolder, position: Int) {
        with(holder.binding) {
            questionAndAnswerList[position].let { content ->
                setUpCardMessage(containerCardQuestion, content.question)
                setUpCardMessage(containerCardAnswer, content.answer)
            }
        }
    }

    /**
     * Sets the content and the onclick behaviour of the card.
     * The onclick behaviour is set using [setCardOnClickBehaviour] function,
     * and the content of the card is set by using [setEventMessageCardContent].
     * @param eventMessageCard the card of which data will be set.
     * @param content the data holding objects, which contains the card's data.
     */
    private fun setUpCardMessage(
        eventMessageCard: ItemEventNewsBinding,
        content: EventNewsMessage
    ) {
        setEventMessageCardContent(eventMessageCard, content)
        setCardOnClickBehaviour(eventMessageCard.cardItemEventNews, content)
    }

    private fun setEventMessageCardContent(
        eventMessageCard: ItemEventNewsBinding,
        content: EventNewsMessage
    ) {
        with(eventMessageCard) {
            tvTitleEventNews.text = content.title
            tvAuthorEventNews.text = context.resources.getString(R.string.by_author, content.author)
            tvMessageEventNews.text = content.message
        }
    }

    /**
     * Sets the card's onclick behaviour, which was given as parameter.
     * After the card was clicked, an [AlertDialog] will be displayed, that displays the
     * content of the card.
     * @param card the card which onclick behaviour will be set.
     * @param representedNews the instance, that holds the data of the card.
     */
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
        return questionAndAnswerList.size
    }

    /**
     * This ViewHolder holds the view of an element in the list of questions and answers.
     */
    inner class EventQAViewHolder(val binding: ItemEventQABinding) :
        RecyclerView.ViewHolder(binding.root)
}