package hu.bme.aut.android.together.features.eventcontrol.incomingquestions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import hu.bme.aut.android.together.databinding.ItemEventQuestionBinding
import hu.bme.aut.android.together.model.presentation.EventQuestion
import hu.bme.aut.android.together.model.presentation.comparator.EventQuestionComparator

class EventQuestionsAdapter(val onQuestionItemClick: (EventQuestion) -> Unit) :
    ListAdapter<EventQuestion, EventQuestionsAdapter.EventQuestionViewHolder>(
        EventQuestionComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventQuestionViewHolder {
        return EventQuestionViewHolder(
            ItemEventQuestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventQuestionViewHolder, position: Int) {
        with(holder.binding) {
            val representedQuestion = getItem(position)
            tvEventAnswer.text = representedQuestion.question
            tvAuthor.text = representedQuestion.author
            tvDetailedAnswer.text = representedQuestion.detailedQuestion
            setCardOnClickBehaviour(cardItemQuestion, representedQuestion)
        }
    }

    private fun setCardOnClickBehaviour(card: MaterialCardView, containedQuestion: EventQuestion) {
        card.setOnClickListener {
            onQuestionItemClick(containedQuestion)
        }
    }
    
    inner class EventQuestionViewHolder(val binding: ItemEventQuestionBinding) :
        RecyclerView.ViewHolder(binding.root)
}