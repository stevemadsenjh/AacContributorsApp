package com.example.yumemi.data

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.yumemi.data.net.GithubImpl
import com.example.yumemi.domain.result.GithubRespOK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GithubContributorsTest {
    private val TAG = GithubContributorsTest::class.java.simpleName

    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    val githubImpl = GithubImpl()

    @Test
    fun githubContributorsSignOLife() {
        runBlocking {
            val respObj = githubImpl.contributors()
            Log.d(TAG, "githubContributorsSignOLife(): ${(respObj as GithubRespOK).contributors.size} contributors:")
            for (contributor in (respObj as GithubRespOK).contributors) {
                Log.d(TAG, "    ${contributor.login}")
            }
            assertTrue(respObj is GithubRespOK)
            assertTrue(respObj.contributors.isNotEmpty())
        }
    }
}
