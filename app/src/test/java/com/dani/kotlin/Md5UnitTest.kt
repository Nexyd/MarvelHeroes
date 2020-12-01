package com.dani.kotlin

import org.junit.Test

import org.junit.Assert.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Md5UnitTest {
    @Test
    fun testMD5() {
        val hash = "b6ee9dd2971bfb110e0664b0363b0453"
        val timestamp  = "1606771361"
        val publicKey  = "f50d565da33c7990abcd87ddc90a59a5"
        val privateKey = "b3d8ce70a5a89662714a6a0912f3bbeee9c541c4"

        assertEquals(hash, md5("$timestamp$privateKey$publicKey"))
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