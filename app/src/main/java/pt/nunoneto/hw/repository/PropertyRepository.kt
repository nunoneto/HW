package pt.nunoneto.hw.repository

import io.reactivex.*
import pt.nunoneto.hw.HWApplication
import pt.nunoneto.hw.entities.Property
import pt.nunoneto.hw.network.IHostelServices
import pt.nunoneto.hw.network.response.PropertyListResponse
import pt.nunoneto.hw.network.statistics.NetworkStatisticsReporter
import pt.nunoneto.hw.utils.CurrencyUtils
import javax.inject.Inject

open class PropertyRepository {

    @Inject
    lateinit var service: IHostelServices

    @Inject
    lateinit var networkStatisticsReporter: NetworkStatisticsReporter

    init {
        // class has to be open for mockito to mock the object
        HWApplication.instance.appComponent.inject(this)
    }

    fun getProperties() : Single<List<Property>> {
        return Single.create {
            emitter ->

            val response = service
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

            // log network request after the result has already been delivered to the UI
            val rawResponse = response.raw()

            networkStatisticsReporter.sendNetworkStatistics(
                    rawResponse.request().url().encodedPath(),
                    rawResponse.receivedResponseAtMillis() - rawResponse.sentRequestAtMillis()
            )
        }
    }

    fun mapProperty(property: PropertyListResponse.Property) : Property {
        val lowestPriceNightEur: Double? = if (property.lowestPricePerNight != null
                && property.lowestPricePerNight.value > 0.0) {
            CurrencyUtils.bolivianToEuro(property.lowestPricePerNight.value)
        } else {
            null
        }

        val rating: Double? = if (property.overallRating != null && property.overallRating.overall in 0 .. 100) {
            property.overallRating.overall.toDouble() / 10
        } else {
            null
        }

        val imageUrl = if (property.images != null && property.images.isNotEmpty()) {

            val image = property.images[0]
            if (image.prefix.isEmpty() && image.suffix.isEmpty()) {
                null
            } else {
                "http://" + image.prefix + image.suffix
            }

        } else {
            null
        }

        return Property(property.id, property.name, property.isFeatured, rating, lowestPriceNightEur, imageUrl)
    }
}