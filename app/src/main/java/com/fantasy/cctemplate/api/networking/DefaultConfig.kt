package com.fantasy.fakelive.api.networking

import android.icu.util.TimeZone
import android.os.Build
import android.provider.Settings
import com.blankj.utilcode.util.AppUtils
import com.fantasy.cctemplate.AppConfig
import com.fantasy.cctemplate.manager.kIsAgreePrivacy
import com.fantasy.components.extension.appLanguageManager
import com.fantasy.components.tools.cckv
import com.fantasy.components.tools.getContext
import com.fantasy.components.tools.isDebugBuilder
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun DefaultRequest.DefaultRequestBuilder.buildDefaultRequestConfig() {
    url(AppConfig.baseUrl)
    headers {
//        append("Client-Info", ccJson.encodeToString(standardHeader))
        append("traceId", randomLowercaseString())
        append("channel", "android")
        append("imei", imei())
        append("x-language", appLanguageManager.currentLanguage.code)
        append("timezone", TimeZone.getDefault().rawOffset.toString())
        append("verbose", (if (isDebugBuilder) "Test " else "") + "${AppConfig.channel.title} V${AppConfig.appVersion}(${AppConfig.appBuildCode})")
        append("version", AppConfig.appVersion)
//        if (userManager.access_token.isNotEmpty()) {
//            append(
//                HttpHeaders.Authorization,
//                "Bearer ${userManager.access_token}"
//            )
//        }
    }
    contentType(ContentType.Application.Json)
}



private fun randomLowercaseString(): String {
    val letters = "abcdefghijklmnopqrstuvwxyz"
    var randomString = ""
    repeat(16) {
        val randomIndex = letters.indices.random()
        val randomLetter = letters[randomIndex]
        randomString += randomLetter
    }
    return randomString
}




fun imei(): String {
    if (cckv.decodeBool(kIsAgreePrivacy, false).not()) {
        return "000000000000000"
    }
    return  Settings.Secure.getString(
        getContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}

@Serializable
internal data class CCRequestHeaders @OptIn(ExperimentalTime::class) constructor(
    val version: String = AppUtils.getAppVersionName(), // 应用版本号
    val timestamp: Long = Clock.System.now().toEpochMilliseconds(), // 用户访问时间，毫秒级时间戳, // 用户访问时间，毫秒级时间戳
    val os: String = "Android ${Build.VERSION.RELEASE}", //	操作系统，windows、macOs、Ios、Android 等等 例如 Android 10
    val model: String = Build.MODEL, //	操作系统，windows、macOs、Ios、Android 等等 例如 Android 10
    val device: String, //	设备信息，包括不仅限于设备编号、型号等
    val device_token: String, //IMEI
)
