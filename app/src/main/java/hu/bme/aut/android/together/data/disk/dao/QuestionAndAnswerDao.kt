package hu.bme.aut.android.together.data.disk.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import hu.bme.aut.android.together.data.disk.model.PersistedQuestionAndAnswer

@Dao
interface QuestionAndAnswerDao {

    @Transaction
    @Query("SELECT * FROM persistedeventquestion WHERE eventId=:eventId")
    fun getEventsQuestionsAndAnswers(eventId: Long): List<PersistedQuestionAndAnswer>

}