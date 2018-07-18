package pt.nunoneto.hw.network

import io.reactivex.Observable
import pt.nunoneto.hw.network.response.PropertyListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IHostelServices {

    @GET(RequestValues.GET_PROPERTIES)
    fun getProperties() : Observable<PropertyListResponse>

    @GET(RequestValues.NETWORK_STATS)
    fun sendNetworkStats(@Query("action") action: String, @Query("duration") duration: Long): Call<Number>
}