package com.dani.kotlin.framework

import android.content.Context
import android.util.Log
import com.dani.data.listener.HeroListener
import com.dani.domain.entities.HeroInfo
import com.dani.domain.entities.MarvelData
import com.dani.kotlin.R
import com.dani.kotlin.framework.listener.RetrofitAPI
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject

class HeroSource @Inject constructor(
    private val context: Context) : HeroListener {

    private lateinit var publicKey: String
    private lateinit var timestamp: String

    override fun getHeroes(): Observable<MarvelData> {
        val service = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitAPI::class.java)

        val hash = getHash()
        return service.loadHeroes(100,
            publicKey, hash, timestamp)
    }

    private fun getHash(): String {
        val timeLong = System.currentTimeMillis() / 1000
        timestamp = timeLong.toString()
        publicKey = context.getString(R.string.marvel_api_public_key)
        val private = context.getString(R.string.marvel_api_private_key)

        return md5("$timestamp$private$publicKey")
    }

    private fun md5(message: String): String {
        val md5 = "MD5"
        try {
            // Create MD5 Hash
            val digest: MessageDigest =
                MessageDigest.getInstance(md5)

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