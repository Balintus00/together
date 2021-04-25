package hu.bme.aut.android.together.features.eventcontrol.communication.qa.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.ItemEventAnswerBinding
import hu.bme.aut.android.together.databinding.ItemEventQABinding
import hu.bme.aut.android.together.databinding.ItemEventQuestionBinding
import hu.bme.aut.android.together.model.presentation.EventAnswer
import hu.bme.aut.android.together.model.presentation.EventQuestion
import hu.bme.aut.android.together.model.presentation.EventQuestionAndAnswer
import hu.bme.aut.android.together.model.presentation.comparator.EventQuestionAndAnswerComparator

/**
 * This adapter can be used to handle the list of an event questions and answers.
 */
class EventQAAdapter(
    private val onQuestionClick: (EventQuestion) -> Unit,
    private val onAnswerClick: (EventAnswer) -> Unit
) :
    ListAdapter<EventQuestionAndAnswer, EventQAAdapter.EventQAViewHolder>(
        EventQuestionAndAnswerComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventQAViewHolder {
        return EventQAViewHolder(
            ItemEventQABinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventQAViewHolder, position: Int) {
        with(holder.binding) {
            val representedQA = getItem(position)
            setUpQuestionCardMessage(containerCardQuestion, representedQA.question, root.resources)
            setUpAnswerCardMessage(containerCardAnswer, representedQA.answer, root.resources)
        }
    }

    private fun setUpQuestionCardMessage(
        questionCard: ItemEventQuestionBinding,
        content: EventQuestion,
        resources: Resources
    ) {
        with(questionCard) {
            tvEventAnswer.text = content.question
            tvAuthor.text =  resources.getString(R.string.by_author, content.author)
            tvDetailedAnswer.text = content.detailedQuestion
            cardItemQuestion.setOnClickListener {
                onQuestionClick(content)
            }
        }
    }

    private fun setUpAnswerCardMessage(
        answerCard: ItemEventAnswerBinding,
        content: EventAnswer,
        resources: Resources
    ) {
        with(answerCard) {
            tvEventAnswer.text = content.answer
            tvAuthor.text = resources.getString(R.string.by_author, content.author)
            tvDetailedAnswer.text = content.detailedAnswer
            cardItemAnswer.setOnClickListener {
                onAnswerClick(content)
            }
        }
    }

    /**
     * This ViewHolder holds the view of an element in the list of questions and answers.
     */
    inner class EventQAViewHolder(val binding: ItemEventQABinding) :
        RecyclerView.ViewHolder(binding.root)
}