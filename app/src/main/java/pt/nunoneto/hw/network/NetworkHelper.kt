package pt.nunoneto.hw.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {

    companion object {

        fun getRetrofit() : Retrofit {
            return Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/ruimendesM/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}