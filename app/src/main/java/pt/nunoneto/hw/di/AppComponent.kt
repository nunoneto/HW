package pt.nunoneto.hw.di

import dagger.Component
import pt.nunoneto.hw.network.statistics.NetworkStatisticsReporter
import pt.nunoneto.hw.repository.PropertyRepository
import pt.nunoneto.hw.ui.PropertyListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(propertyListViewModel: PropertyListViewModel)

    fun inject(propertyRepository: PropertyRepository)

    fun inject(networkStatsReporter: NetworkStatisticsReporter)
}