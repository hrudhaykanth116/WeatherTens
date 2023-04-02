package com.hrudhaykanth116.weathertens.common.data.remote

import android.util.Log
import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.common.data.models.UIText
import com.hrudhaykanth116.weathertens.common.utils.network.OnlineTracker
import retrofit2.Response

abstract class NetworkDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): DataResult<T> {

        if (OnlineTracker.isOnline) {
            try {
                val response = call()
                return if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null)
                        DataResult.Success(body)
                    else DataResult.Error(
                        UIText.Text("No data found.")
                    )
                }else{
                    // TODO: Parse error
                    DataResult.Error(UIText.Text("Something went wrong"))
                }

            } catch (e: Exception) {
                Log.e(TAG, "getResult: ", e)
                return DataResult.Error(
                    uiMessage = e.message?.let { UIText.Text(it) }
                )
            }
        } else {
            Log.e(TAG, "getResult: No internet")
            return DataResult.Error(
                uiMessage = UIText.Text("No internet"),
                uiDescription = UIText.Text("Internet is not available."),
            )
        }
    }

    companion object {
        private const val TAG = "NetworkDataSource"
    }


}