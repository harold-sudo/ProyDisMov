import com.calyrsoft.ucbp1.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import kotlinx.coroutines.flow.Flow

class DollarRepository: IDollarRepository {


    override suspend fun getDollar(): Flow<DollarModel> {
//        return flow {
//            emit(DollarModel("6.96", "12.6"))
//        }
        return RealTimeRemoteDataSource().getDollarUpdates()
    }
}
