package hu.bme.aut.android.together.model.persistence

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

class PersistedQuestionAndAnswer(
    @Embedded val question: PersistedEventQuestion,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "answersQuestionId"
    )
    val answer: PersistedEventAnswer
)

@Entity
class PersistedEventQuestion(
    @PrimaryKey val questionId: Long,
    val question: String,
    val author: String,
    val detailedQuestion: String,
    val eventId: Long
)

@Entity
class PersistedEventAnswer(
    @PrimaryKey val answerId: Long,
    val answer: String,
    val author: String,
    val detailedAnswer: String,
    val eventId: Long,
    val answersQuestionId: Long
)