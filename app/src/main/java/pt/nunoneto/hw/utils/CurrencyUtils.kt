package pt.nunoneto.hw.utils

class CurrencyUtils {

    companion object {

        fun bolivianToEuro(bolivianValue: Double) : Double {
            return bolivianValue / 7.55
        }
    }
}