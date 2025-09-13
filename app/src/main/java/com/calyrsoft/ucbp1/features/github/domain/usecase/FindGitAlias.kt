import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
import kotlinx.coroutines.delay

class FindByNickNameUseCase(
    val repository: IGithubRepository
) {
    suspend fun invoke(nickname: String) : Result<UserModel>  {
        delay(2000)
        val response = repository.findByNick(nickname)
        return response
    }
}
