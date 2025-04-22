import com.ricky.repository.impl.UserRepositoryImpl
import com.ricky.service.UserService
import com.ricky.service.impl.UserServiceImpl

class DependencyContainer private constructor() {
    val userService: UserService

    init {
        val userRepository = UserRepositoryImpl()
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