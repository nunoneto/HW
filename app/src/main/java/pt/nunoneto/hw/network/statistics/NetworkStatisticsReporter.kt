package pt.nunoneto.hw.network.statistics

import pt.nunoneto.hw.HWApplication
import pt.nunoneto.hw.network.IHostelServices
import javax.inject.Inject

class NetworkStatisticsReporter {

    @Inject
    lateinit var service: IHostelServices

    fun sendNetworkStatistics(action: String, duration: Long) {
        // not sure on the best way to accomplish this, given that
        // this class is instantiated from the injection of the service itself
        if (!::service.isInitialized) {
            HWApplication.instance.appComponent.inject(this)
        }

        service.sendNetworkStats(action, duration).execute()
    }
}