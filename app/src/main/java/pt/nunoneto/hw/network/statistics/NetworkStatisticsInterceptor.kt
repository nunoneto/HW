package pt.nunoneto.hw.network.statistics

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import pt.nunoneto.hw.HWApplication
import pt.nunoneto.hw.network.RequestValues
import javax.inject.Inject

// Responsible for making network stats of "imperceptible" requests
class NetworkStatisticsInterceptor : Interceptor {

    // maps requests we're interested in analysing to the respective action
    // (assumed a static identifier per request)
    private val imperceptibleRequests: HashMap<String, String> = hashMapOf(
            RequestValues.GET_PROPERTIES to "load_properties"
    )

    @Inject
    lateinit var statsReporter: NetworkStatisticsReporter

    init {
        HWApplication.instance.appComponent.inject(this)
    }

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request: Request? = chain!!.request()

        val encodedPath: String = request?.url()?.encodedPath() ?: ""
        if (encodedPath.isNullOrEmpty()) {
            return chain.proceed(request)
        }

        for (req in imperceptibleRequests) {
            if (encodedPath.indexOf(req.key) > 0) {

                val response = chain.proceed(request)

                statsReporter.sendNetworkStatistics(req.value,
                        response.receivedResponseAtMillis() - response.sentRequestAtMillis())

                return response
            }
        }

        return chain.proceed(request)
    }

}