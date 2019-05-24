package hr.ferit.brunozoric.taskie.persistence

import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.database.DaoProvider
import hr.ferit.brunozoric.taskie.database.TaskDao
import hr.ferit.brunozoric.taskie.model.Task

class TaskRoomRepository :RepositoryTask{

    private var db:DaoProvider = DaoProvider.getInstance(Taskie.getAppContext())
    private var taskDao:TaskDao = db.taskDao()

    override fun addTask(task: Task) {
        taskDao.insertTask(task)
    }

    override fun addTasks(mutableList: MutableList<Task>) {
        mutableList.forEach{ addTask(it)}
    }

    override fun editTask(taskId: Long, task: Task) {
        taskDao.editTask(taskId,task.title,task.description,task.priority)
    }

    override fun getTasks(): MutableList<Task> {
       return taskDao.getAllTasks()
    }

    override fun getTaskBy(id: Long): Task {
        val tasks = taskDao.getAllTasks()
        tasks.forEach { if (it.taskId == id) return it }
        return tasks[0]
    }

    override fun deleteTask(id: Long) {
       val tasks = taskDao.getAllTasks()
        tasks.forEach { if(it.taskId == id) taskDao.deleteTask(it) }
    }

    override fun clearAllTasks() {
        taskDao.deleteAll()
    }
}