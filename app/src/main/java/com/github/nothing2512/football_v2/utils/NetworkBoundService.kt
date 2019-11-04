package com.github.nothing2512.football_v2.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.github.nothing2512.football_v2.data.source.remote.ApiEmptyResponse
import com.github.nothing2512.football_v2.data.source.remote.ApiErrorResponse
import com.github.nothing2512.football_v2.data.source.remote.ApiResponse
import com.github.nothing2512.football_v2.data.source.remote.ApiSuccessResponse
import com.github.nothing2512.football_v2.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("LeakingThis")
abstract class NetworkBoundService<RESULT, REQUEST>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<RESULT>>()

    private fun fetchFromNetwork(dbSource: LiveData<RESULT>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { setValue(Resource.loading(it)) }
        result.addSource(apiResponse) { response ->

            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response) {

                is ApiSuccessResponse -> {
                    appExecutors.diskIO.execute {
                        saveCallResult(processResponse(response))
                        appExecutors.mainThread.execute {
                            result.addSource(loadFromDb()) {
                                setValue(Resource.success(it))
                            }
                        }
                    }

                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread.execute {
                        result.addSource(loadFromDb()) {
                            setValue(Resource.success(it))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        setValue(Resource.error(response.errorMessage, it))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<RESULT>) {
        if (result.value != newValue) result.value = newValue
    }

    protected open fun onFetchFailed() {}

    suspend fun asLiveData() = withContext(Dispatchers.IO) {
        appExecutors.mainThread.execute {
            result.postValue(Resource.loading(null))
            val dbSource = loadFromDb()
            result.addSource(dbSource) { data ->
                result.removeSource(dbSource)
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource)
                } else {
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
            }
        }
        result as LiveData<Resource<RESULT>>
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<REQUEST>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: REQUEST)

    @MainThread
    protected abstract fun shouldFetch(data: RESULT?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<RESULT>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<REQUEST>>

}