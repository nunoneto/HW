package pt.nunoneto.hw.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pt.nunoneto.hw.entities.Property
import pt.nunoneto.hw.repository.PropertyRepository

class PropertyListViewModel : ViewModel(), SingleObserver<List<Property>> {

    var propertyList: MutableLiveData<List<Property>> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var disposable: Disposable

    init {
        getProperties()
    }

    private fun getProperties() {
        PropertyRepository.getProperties()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun onSuccess(properties: List<Property>) {
        propertyList.value = properties
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        error.value = true
    }

    override fun onCleared() {
        if (::disposable.isInitialized && !disposable.isDisposed) {
            disposable.dispose()
        }

        super.onCleared()
    }
}