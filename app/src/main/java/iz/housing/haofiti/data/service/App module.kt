package iz.housing.haofiti.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iz.housing.haofiti.data.repository.HouseRepository
import iz.housing.haofiti.data.repository.HouseRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHouseRepository(): HouseRepository {
        return HouseRepositoryImpl()
    }
}
