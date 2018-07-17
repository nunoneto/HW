package pt.nunoneto.hw.repository

import io.reactivex.*
import pt.nunoneto.hw.entities.Property
import pt.nunoneto.hw.network.NetworkHelper
import pt.nunoneto.hw.network.response.PropertyListResponse
import pt.nunoneto.hw.utils.CurrencyUtils
import kotlin.math.round

object PropertyRepository {

    fun getProperties() : Single<List<Property>> {
        return Single.create {
            emitter ->

            val response = NetworkHelper.service
                    .getProperties()
                    .execute()

            if (response == null) {
                emitter.onError(Throwable("Request Error"))
                return@create
            }

            val propertyList = Observable.just(response)
                    .flatMapIterable { response -> response.body()?.properties }
                    .map { item -> mapProperty(item) }
                    .toList()
                    .blockingGet()

            emitter.onSuccess(propertyList)

            //TODO network metadata request
        }
    }

    private fun mapProperty(property: PropertyListResponse.Property) : Property {
        val lowestPriceNightEur: Double? = if (property.lowestPricePerNight != null) {
            CurrencyUtils.bolivianToEuro(property.lowestPricePerNight.value)
        } else {
            null
        }

        val rating: Double? = if (property.overallRating != null) {
            (property.overallRating.overall / 100).toDouble()
        } else {
            null
        }

        return Property(property.id, property.isFeatured, rating, lowestPriceNightEur)
    }
}