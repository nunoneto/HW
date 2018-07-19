package pt.nunoneto.hw

import android.app.Application
import pt.nunoneto.hw.di.AppComponent
import pt.nunoneto.hw.di.DaggerAppComponent

class HWApplication : Application() {

    companion object {
        lateinit var instance: HWApplication
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}