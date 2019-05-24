package hr.ferit.brunozoric.taskie.persistence

import hr.ferit.brunozoric.taskie.model.Task

interface RepositoryTask {

    fun addTask(task:Task)
    fun addTasks(mutableList: MutableList<Task>)
    fun editTask(taskId:Long,task:Task)
    fun getTasks():List<Task>
    fun getTaskBy(id:Long): Task
    fun deleteTask(id:Long)
    fun clearAllTasks()
}