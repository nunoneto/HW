package pt.nunoneto.hw.utils

import kotlin.math.abs

class CurrencyUtils {

    companion object {

        fun bolivianToEuro(bolivianValue: Double) : Double {
            return abs(bolivianValue) / 7.55
        }
    }
}