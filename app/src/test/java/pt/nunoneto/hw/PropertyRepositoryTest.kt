package pt.nunoneto.hw

import org.junit.Before
import org.junit.Test
import pt.nunoneto.hw.di.DaggerTestComponent
import pt.nunoneto.hw.di.RepositoryTestModule
import pt.nunoneto.hw.network.response.PropertyListResponse
import pt.nunoneto.hw.repository.PropertyRepository
import javax.inject.Inject

class PropertyRepositoryTest {

    @Inject
    lateinit var propertyRepository: PropertyRepository

    @Before
    fun setup() {
        val component = DaggerTestComponent
                .builder()
                .repositoryModule(RepositoryTestModule())
                .build()

        component.inject(this)
    }

    @Test
    fun mapPropertyResponseEmptyURL() {
        val property = PropertyListResponse.Property(
                123,
                "property x",
                true,
                PropertyListResponse.Property.Rating(0, 10),
                PropertyListResponse.Property.Price(100.0, "EUR"),
                listOf(PropertyListResponse.Property.Image("", ""))
        )

        val result = propertyRepository.mapProperty(property)
        assert(result.imageUrl == null)
    }

    @Test
    fun mapPropertyResponseURL() {
        val property = PropertyListResponse.Property(
                123,
                "property x",
                true,
                PropertyListResponse.Property.Rating(0, 10),
                PropertyListResponse.Property.Price(100.0, "EUR"),
                listOf(PropertyListResponse.Property.Image("somedomain.pt/someimage", ".png"))
        )

        val result = propertyRepository.mapProperty(property)
        assert(result.imageUrl == "http://somedomain.pt/someimage.png")
    }

    @Test
    fun mapPropertyResponseInvalidPrice() {
        val property = PropertyListResponse.Property(
                123,
                "property x",
                true,
                PropertyListResponse.Property.Rating(0, 10),
                PropertyListResponse.Property.Price(-100.0, "EUR"),
                listOf(PropertyListResponse.Property.Image("", ""))
        )

        val result = propertyRepository.mapProperty(property)
        assert(result.lowestPricePerNight == null)
    }

    @Test
    fun mapPropertyResponsePrice() {
        val property = PropertyListResponse.Property(
                123,
                "property x",
                true,
                PropertyListResponse.Property.Rating(0, 10),
                PropertyListResponse.Property.Price(755.0, "BOL"),
                listOf(PropertyListResponse.Property.Image("", ""))
        )

        val result = propertyRepository.mapProperty(property)
        assert(result.lowestPricePerNight == 100.0)
    }

    @Test
    fun mapPropertyResponseInvalidNegativeRating() {
        val property = PropertyListResponse.Property(
                123,
                "property x",
                true,
                PropertyListResponse.Property.Rating(-10, 10),
                PropertyListResponse.Property.Price(-100.0, "EUR"),
                listOf(PropertyListResponse.Property.Image("", ""))
        )

        val result = propertyRepository.mapProperty(property)
        assert(result.rating == null)
    }

    @Test
    fun mapPropertyResponseInvalidPositiveRating() {
        val property = PropertyListResponse.Property(
                123,
                "property x",
                true,
                PropertyListResponse.Property.Rating(120, 10),
                PropertyListResponse.Property.Price(-100.0, "EUR"),
                listOf(PropertyListResponse.Property.Image("", ""))
        )

        val result = propertyRepository.mapProperty(property)
        assert(result.rating == null)
    }

    @Test
    fun mapPropertyResponseRating() {
        val property = PropertyListResponse.Property(
                123,
                "property x",
                true,
                PropertyListResponse.Property.Rating(90, 10),
                PropertyListResponse.Property.Price(100.0, "EUR"),
                listOf(PropertyListResponse.Property.Image("", ""))
        )

        val result = propertyRepository.mapProperty(property)
        assert(result.rating == 9.0)
    }

}