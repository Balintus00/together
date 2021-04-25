package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import hu.bme.aut.android.together.model.persistence.PersistedQuestionAndAnswer

@Dao
interface QuestionAndAnswerDao {

    @Transaction
    @Query("SELECT * FROM persistedeventquestion WHERE eventId=:eventId")
    fun getEventsQuestionsAndAnswers(eventId: Long): List<PersistedQuestionAndAnswer>

}