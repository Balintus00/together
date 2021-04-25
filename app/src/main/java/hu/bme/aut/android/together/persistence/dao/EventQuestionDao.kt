package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import hu.bme.aut.android.together.model.persistence.PersistedEventQuestion

@Dao
interface EventQuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventQuestion(vararg question: PersistedEventQuestion)

}