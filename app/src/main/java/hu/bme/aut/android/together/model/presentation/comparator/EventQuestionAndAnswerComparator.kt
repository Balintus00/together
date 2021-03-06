package hu.bme.aut.android.together.model.presentation.comparator

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.android.together.model.presentation.EventQuestionAndAnswer

object EventQuestionAndAnswerComparator: DiffUtil.ItemCallback<EventQuestionAndAnswer>() {
    override fun areItemsTheSame(
        oldItem: EventQuestionAndAnswer,
        newItem: EventQuestionAndAnswer
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: EventQuestionAndAnswer,
        newItem: EventQuestionAndAnswer
    ): Boolean {
        return oldItem == newItem
    }

}