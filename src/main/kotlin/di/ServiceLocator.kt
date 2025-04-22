import com.ricky.repository.impl.TaskRepositoryImpl
import com.ricky.repository.impl.UserRepositoryImpl
import com.ricky.service.TaskService
import com.ricky.service.UserService
import com.ricky.service.impl.TaskServiceImpl
import com.ricky.service.impl.UserServiceImpl

class DependencyContainer private constructor() {
    val userService: UserService
    val taskService: TaskService

    init {
        val userRepository = UserRepositoryImpl()
        val taskRepository = TaskRepositoryImpl()

        this.taskService = TaskServiceImpl(taskRepository)
        this.userService = UserServiceImpl(userRepository)
    }

    companion object {
        private var instance: DependencyContainer? = null

        fun getInstance(): DependencyContainer {
            return instance ?: synchronized(this) {
                instance ?: DependencyContainer().also { instance = it }
            }
        }

        // Para testes
        fun setForTesting(testInstance: DependencyContainer) {
            instance = testInstance
        }
    }
}