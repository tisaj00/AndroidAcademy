package hr.ferit.brunozoric.taskie.persistence

import hr.ferit.brunozoric.taskie.model.Task

object Repository{

    private val tasks = mutableListOf<Task>()

    fun get(id: Int): Task {
        return tasks.first { it.id == id }
    }


}