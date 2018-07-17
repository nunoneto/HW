package pt.nunoneto.hw.network

import pt.nunoneto.hw.network.response.PropertyListResponse
import retrofit2.Call
import retrofit2.http.GET

interface IHostelServices {

    @GET("bf8d095f2e92da94938810b8a8187c21/raw/70b112f88e803bf0f101f2c823a186f3d076d9e6/properties.json")
    fun getProperties() : Call<PropertyListResponse>

}