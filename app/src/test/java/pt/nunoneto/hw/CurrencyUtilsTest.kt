package pt.nunoneto.hw

import org.junit.Test
import pt.nunoneto.hw.utils.CurrencyUtils

class CurrencyUtilsTest {

    @Test
    fun convertBolivarToEuroValueBelowZero() {
        val result = CurrencyUtils.bolivianToEuro(-1.0)
        assert(result > 0)
    }

    @Test
    fun convertBolivarToEuroValueConversionRate() {
        val result = CurrencyUtils.bolivianToEuro(7.55)
        assert(result == 1.0)
    }
    @Test
    fun convertBolivarToEuroValueZero() {
        val result = CurrencyUtils.bolivianToEuro(0.0)
        assert(result == 0.0)
    }
}