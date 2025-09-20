import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import kotlinx.coroutines.flow.Flow

class FetchDollarUseCase(
    val repository: IDollarRepository
) {


    suspend fun invoke(): Flow<DollarModel> {
        return repository.getDollar()
    }
}
