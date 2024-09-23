package iz.housing.haofiti.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import iz.housing.haofiti.data.database.HouseDao
import iz.housing.haofiti.data.database.HouseDatabase
import iz.housing.haofiti.data.database.HouseRepositoryImpl
import iz.housing.haofiti.data.database.HouseTypeConvertors
import iz.housing.haofiti.data.repository.AuthRepository
import iz.housing.haofiti.data.repository.HouseRepository
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing the HouseRepository dependency.
 * This module is installed in the SingletonComponent, ensuring app-wide singleton instances.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    /**
     * Binds the HouseRepositoryImpl to the HouseRepository interface.
     * This allows Hilt to inject HouseRepository where needed, using the HouseRepositoryImpl implementation.
     *
     * @param houseRepositoryImpl The implementation of HouseRepository to be used.
     * @return An instance of HouseRepository, which is actually a HouseRepositoryImpl.
     */
    @Binds
    @Singleton
    abstract fun bindHouseRepository(
        houseRepositoryImpl: HouseRepositoryImpl
    ): HouseRepository
}

/**
 * Dagger Hilt module for providing Firebase-related dependencies.
 * This module is installed in the SingletonComponent, ensuring app-wide singleton instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    /**
     * Provides a singleton instance of FirebaseDatabase.
     *
     * @return The FirebaseDatabase instance.
     */
    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
}

/**
 * Dagger Hilt module for providing database-related dependencies.
 * This module is installed in the SingletonComponent, ensuring app-wide singleton instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of HouseDatabase.
     *
     * @param application The Application instance, used as context for the database.
     * @return A Room database instance of HouseDatabase.
     */
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

    /**
     * Provides a singleton instance of HouseDao.
     *
     * @param houseDatabase The HouseDatabase instance from which to get the DAO.
     * @return The HouseDao instance.
     */
    @Provides
    @Singleton
    fun providesDao(houseDatabase: HouseDatabase): HouseDao = houseDatabase.houseDao()
}

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    /**
     * Provides a singleton instance of AuthRepository.
     *
     * @return The AuthRepository instance.
     */
    @Provides
    @Singleton
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository {
        return AuthRepository(context)
    }
}