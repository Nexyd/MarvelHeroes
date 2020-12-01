package com.dani.kotlin

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dani.kotlin.framework.listener.RetrofitAPI

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EnpointInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dani.kotlin", appContext.packageName)
    }

    @Test
    fun callEndpoint() {
        val service = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitAPI::class.java)

        val publicKey  = "f50d565da33c7990abcd87ddc90a59a5"
        val privateKey = "b3d8ce70a5a89662714a6a0912f3bbeee9c541c4"
        val timeLong   = System.currentTimeMillis() / 1000
        val timestamp  = timeLong.toString()
        val hash = md5("$timestamp$privateKey$publicKey")

        val result = service.loadHeroes(100,
            publicKey, hash, timestamp)

        assertNotNull(result)
    }

    private fun md5(message: String): String {
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest
                .getInstance("MD5")

            digest.update(message.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(
                    0xFF and aMessageDigest.toInt())

                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }

            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }
}