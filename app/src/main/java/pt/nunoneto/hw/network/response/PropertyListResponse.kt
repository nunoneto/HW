package pt.nunoneto.hw.network.response

data class PropertyListResponse (val properties: List<Property>) {

    data class Property(val id: Long,
                        val name: String,
                        val isFeatured: Boolean,
                        val overallRating: Rating?,
                        val lowestPricePerNight: Price?,
                        val images: List<Image>?) {

        data class Rating(val overall: Int,
                          val numberOfRatings: Int)

        data class Price(val value: Double,
                         val currency: String)

        data class Image(val prefix: String,
                         val suffix: String)
    }
}