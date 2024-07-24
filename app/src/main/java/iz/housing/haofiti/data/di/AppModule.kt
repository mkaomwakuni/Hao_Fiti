package iz.housing.haofiti.data.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iz.housing.haofiti.data.database.HouseDao
import iz.housing.haofiti.data.database.HouseDatabase
import iz.housing.haofiti.data.database.HouseRepositoryImpl
import iz.housing.haofiti.data.database.HouseTypeConvertors
import iz.housing.haofiti.data.repository.HouseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindHouseRepository(
        houseRepositoryImpl: HouseRepositoryImpl
    ): HouseRepository
}

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
}
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesHouseDatabase(application: Application): HouseDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = HouseDatabase::class.java,
            name = "HousesDatabase"
        ).addTypeConverter(HouseTypeConvertors())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesDao(houseDatabase: HouseDatabase): HouseDao = houseDatabase.houseDao()
}
