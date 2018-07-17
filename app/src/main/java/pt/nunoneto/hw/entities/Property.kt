package pt.nunoneto.hw.entities

data class Property (val id: Long,
                     val name: String,
                     val featured: Boolean,
                     val rating: Double?,
                     val lowestPricePerNight: Double?,
                     val imageUrl: String?)