package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.together.model.persistence.PersistedEventQuestion

@Dao
interface EventQuestionDao {

    @Query("SELECT * FROM persistedeventquestion WHERE eventId=:eventId")
    fun getEventQuestionsById(eventId: Long): List<PersistedEventQuestion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventQuestion(vararg question: PersistedEventQuestion)

}