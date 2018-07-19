package pt.nunoneto.hw.di

import dagger.Module
import dagger.Provides
import pt.nunoneto.hw.network.statistics.NetworkStatisticsReporter
import pt.nunoneto.hw.repository.PropertyRepository
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    open fun providesPropertyRepository() : PropertyRepository {
        return PropertyRepository()
    }

    @Provides
    @Singleton
    fun providesNetworkStatsReporter() : NetworkStatisticsReporter {
        return NetworkStatisticsReporter()
    }
}