package pt.nunoneto.hw.di

import dagger.Module
import dagger.Provides
import pt.nunoneto.hw.network.IHostelServices
import pt.nunoneto.hw.network.NetworkHelper

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitService(): IHostelServices {
        return NetworkHelper.getRetrofit().create(IHostelServices::class.java)
    }

}