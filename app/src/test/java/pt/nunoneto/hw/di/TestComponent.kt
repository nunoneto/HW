package pt.nunoneto.hw.di

import dagger.Component
import pt.nunoneto.hw.PropertyRepositoryTest
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetworkModule::class])
interface TestComponent : AppComponent {

    fun inject(projectRepositoryTest: PropertyRepositoryTest)

}