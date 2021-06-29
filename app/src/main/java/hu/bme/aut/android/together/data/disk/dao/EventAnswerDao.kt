package hu.bme.aut.android.together.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import hu.bme.aut.android.together.data.disk.model.PersistedEventAnswer

@Dao
interface EventAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventAnswer(vararg answer: PersistedEventAnswer)

}