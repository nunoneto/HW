package pt.nunoneto.hw.network.statistics

import android.util.Log
import pt.nunoneto.hw.HWApplication
import pt.nunoneto.hw.network.IHostelServices
import pt.nunoneto.hw.network.RequestValues
import javax.inject.Inject

class NetworkStatisticsReporter {

    companion object {
        private const val TAG: String = "NetworkStatsReporter"
    }

    @Inject
    lateinit var service: IHostelServices

    // maps requests we're interested in analysing to the respective action
    // (assumed a static identifier per request)
    private val interceptibleRequests: HashMap<String, String> = hashMapOf(
            RequestValues.GET_PROPERTIES to "load_properties"
    )

    init {
        HWApplication.instance.appComponent.inject(this)
    }

    fun sendNetworkStatistics(path: String, duration: Long) {
        val action = getActionForRequest(path)
        if (action == null) {
            Log.d(TAG, "sendNetworkStatistics. request not loggable: $path")
            return
        }

        service.sendNetworkStats(action, duration).execute()
    }

    private fun getActionForRequest(path: String) : String? {
        for (entry in interceptibleRequests) {
            if (path.contains(entry.key)) {
                return entry.value
            }
        }

        return null
    }
}