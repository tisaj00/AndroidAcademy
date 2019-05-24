package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.view.View
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.persistence.Repository
import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment() {

    private val repository = TaskRoomRepository()
    private var taskID = NO_TASK


    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_task_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(EXTRA_TASK_ID)?.let { taskID = it }
        tryDisplayTask(taskID.toLong())
        editSaveTaskAction.setOnClickListener {
            tryEditTask(taskID.toLong())

        }
    }

    private fun tryDisplayTask(id: Long) {
        try {
            val task = repository.getTaskBy(id)
            displayTask(task)
        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    private fun displayTask(task: Task) {
        detailsTaskTitle.text = task.title
        detailsTaskDescription.text = task.description
        detailsPriorityView.setBackgroundResource(task.priority.getColor())
    }

    private fun tryEditTask(id: Long){
        val task = repository.getTaskBy(id)
        val title = editTaskTitleInput.text.toString()
        val description = editTaskDescriptionInput.text.toString()
        val priorityNew = editPrioritySelector.selectedItem
        when(priorityNew){
            "Low" -> {
                val editedTask = Task(title = title, description = description, priority = Priority.LOW)
                repository.editTask(id,editedTask)
            }
            "Medium" -> {
                val editedTask = Task(title = title, description = description, priority = Priority.MEDIUM)
                repository.editTask(id,editedTask)

            }
            "High" -> {
                val editedTask = Task(title = title, description = description, priority = Priority.HIGH)
                repository.editTask(id,editedTask)
            }
        }
        clearUi()


    }
    private fun clearUi() {
        editTaskTitleInput.text.clear()
        editTaskDescriptionInput.text.clear()
    }

    companion object {
        const val NO_TASK = -1

        fun newInstance(taskId: Int): TaskDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}
