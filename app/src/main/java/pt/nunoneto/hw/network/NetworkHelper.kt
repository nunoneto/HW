package pt.nunoneto.hw.network

import okhttp3.OkHttpClient
import pt.nunoneto.hw.network.statistics.NetworkStatisticsInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {

    companion object {

        fun getRetrofit() : Retrofit {
            return Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/ruimendesM/")
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        private fun getOkHttpClient() : OkHttpClient {
            return OkHttpClient.Builder()
                    .addNetworkInterceptor(NetworkStatisticsInterceptor())
                    .build()
        }

    }
}