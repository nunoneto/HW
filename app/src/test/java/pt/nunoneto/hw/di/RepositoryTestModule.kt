package pt.nunoneto.hw.di

import org.mockito.Mockito
import pt.nunoneto.hw.repository.PropertyRepository

class RepositoryTestModule : RepositoryModule() {

    override fun providesPropertyRepository() : PropertyRepository {
        return Mockito.mock(PropertyRepository::class.java)
    }

}