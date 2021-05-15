package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import hu.bme.aut.android.together.model.persistence.PersistedEventAnswer

@Dao
interface EventAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventAnswer(vararg answer: PersistedEventAnswer)

}