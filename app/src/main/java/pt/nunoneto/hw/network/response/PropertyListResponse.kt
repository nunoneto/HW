package pt.nunoneto.hw.network.response

data class PropertyListResponse (val properties: List<Property>) {

    data class Property(val id: Long,
                        val name: String,
                        val isFeatured: Boolean,
                        val overallRating: Rating?,
                        val lowestPricePerNight: Price?) {

        data class Rating(val overall: Int,
                          val numberOfRatings: Int)

        data class Price(val value: Double,
                         val currency: String)
    }
}