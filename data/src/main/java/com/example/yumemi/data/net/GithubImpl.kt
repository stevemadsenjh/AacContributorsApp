package com.example.yumemi.data.net

import android.util.Log
import com.example.yumemi.domain.result.GithubRespBase
import com.example.yumemi.domain.result.GithubRespFail
import com.example.yumemi.domain.result.GithubRespOK
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class GithubImpl {
    private val TAG = GithubImpl::class.java.simpleName

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GithubConstants.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val iGithubSvc: IGithubSvc
            = retrofit.create(IGithubSvc::class.java)

    suspend fun contributors(): GithubRespBase {
        return try {
            val respObj = iGithubSvc.contributors()
            GithubRespOK(respObj)
        } catch (e: Exception) {
            val msg = "ERROR (${e.javaClass.simpleName}): ${e.localizedMessage}"
            Log.e(TAG, "")
            GithubRespFail(msg)
        }
    }
}
