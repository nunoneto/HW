package pt.nunoneto.hw.di

import dagger.Module
import dagger.Provides
import pt.nunoneto.hw.repository.PropertyRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesPropertyRepository() : PropertyRepository {
        return PropertyRepository()
    }
}