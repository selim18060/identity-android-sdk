package com.reach5.identity.sdk.core.utils

import android.util.Base64
import com.google.gson.Gson
import com.reach5.identity.sdk.core.models.ReachFiveError

object Jwt {
    fun <T : Any> decode(token: String, type: Class<T>): T {
        val parts = token.split("\\.".toRegex())
        return if (parts.size == 3) {
            val (_, payload, _) = parts
            val decoded = String(Base64.decode(payload, Base64.URL_SAFE), Charsets.UTF_8)
            Gson().fromJson(decoded, type)
        } else {
            throw ReachFiveError.from("Invalid format of the token: should contain 3 parts")
        }
    }
}
