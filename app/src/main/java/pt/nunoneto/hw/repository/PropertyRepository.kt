package pt.nunoneto.hw.repository

import io.reactivex.*
import pt.nunoneto.hw.HWApplication
import pt.nunoneto.hw.entities.Property
import pt.nunoneto.hw.network.IHostelServices
import pt.nunoneto.hw.network.response.PropertyListResponse
import pt.nunoneto.hw.utils.CurrencyUtils
import javax.inject.Inject

class PropertyRepository {

    @Inject
    lateinit var service: IHostelServices

    init {
        HWApplication.instance.appComponent.inject(this)
    }

    fun getProperties() : Single<List<Property>> {
        return service.getProperties()
                .flatMapIterable { response: PropertyListResponse ->  response.properties }
                .map { item -> mapProperty(item) }
                .toList()
    }

    private fun mapProperty(property: PropertyListResponse.Property) : Property {
        val lowestPriceNightEur: Double? = if (property.lowestPricePerNight != null) {
            CurrencyUtils.bolivianToEuro(property.lowestPricePerNight.value)
        } else {
            null
        }

        val rating: Double? = if (property.overallRating != null) {
            property.overallRating.overall.toDouble() / 10
        } else {
            null
        }

        val imageUrl = if (property.images != null && property.images.isNotEmpty()) {
            val image = property.images[0]
            "http://" + image.prefix + image.suffix
        } else {
            null
        }

        return Property(property.id, property.name, property.isFeatured, rating, lowestPriceNightEur, imageUrl)
    }
}