package hr.ferit.brunozoric.taskie.database

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    fun getAllTasks():MutableList<Task>

    @Query("UPDATE Task SET title= :titleNew,description= :descriptionNew,priority= :priorityNew WHERE taskId= :taskIdDb")
    fun editTask(taskIdDb:Long?,titleNew: String,descriptionNew:String,priorityNew:Priority)

    @Insert(onConflict = IGNORE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE from Task")
    fun deleteAll()
}