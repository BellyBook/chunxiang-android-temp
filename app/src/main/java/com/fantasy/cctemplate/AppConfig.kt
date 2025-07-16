package com.fantasy.cctemplate

import com.blankj.utilcode.util.RomUtils
import com.fantasy.components.tools.getMetaData
import com.fantasy.components.tools.inPreview
import com.fantasy.components.tools.isDebugBuilder


object AppConfig {
    enum class Env {
        dev,
        prod,
    }

    enum class Channel {
        universal,
        google,
        china,
        ;

        val title
            get() = when (this) {
                china -> {
                    if (RomUtils.isHuawei()) {
                        "Huawei"
                    } else if (RomUtils.isXiaomi()) {
                        "Xiaomi"
                    } else if (RomUtils.isMeizu()) {
                        "Meizu"
                    } else if (RomUtils.isOppo()) {
                        "Oppo"
                    } else if (RomUtils.isVivo()) {
                        "Vivo"
                    } else if (RomUtils.isSamsung()) {
                        "Samsung"
                    } else {
                        name
                    }
                }
                else -> name
            }
    }

    val channel
        get() = if (inPreview) Channel.universal else when (getMetaData("channel")) {
            Channel.google.name -> Channel.google
            Channel.china.name -> Channel.china
            else -> Channel.universal
        }
    val isGooglePlay: Boolean get() = channel == Channel.google
    val isChina: Boolean get() = channel == Channel.china
    val isUniversal: Boolean get() = channel == Channel.universal

    /**
     * 环境
     */
    val evn = if (isDebugBuilder) Env.dev else Env.prod
    val isDev = evn == Env.dev // 是否是开发环境
    val isProd: Boolean get() = evn == Env.prod

    val baseUrl: String
        get() = when (evn) {
            Env.dev -> "https://*****.com"
            Env.prod -> "https://*****.com"
        }


    /**
     *  H5 domain
     */
    const val h5BaseUrl: String = "*****"
    /// 用户协议
    const val userAgreement: String = h5BaseUrl + "*****"
    /// 隐私政策
    const val userPrivacyPolicy: String = h5BaseUrl + "*****"
    /// 教程链接
    const val tutorialUrl: String = h5BaseUrl + "*****"
    // 声音贡献教程链接
    const val soundGuide: String = h5BaseUrl + "*****"


    val versionUpdateUrl: String
        get() = if (isDev) {
             "*****.apk"
        } else "*****.apk"

    val wechatAppID: String get() = "wx..."
    val wechatAppSecret: String get() = "*****"
    const val uMAppId = "*****"

    const val appVersion = BuildConfig.VERSION_NAME
    const val appBuildCode = BuildConfig.VERSION_CODE // 废弃之
    val appVersionCode = versionToCode(appVersion)
    const val appVersionAndBuild = "Version $appVersion ($appBuildCode)"

    fun versionToCode(version: String): Int {
        val components = version.split(".")
        if (components.size < 3) return 0

        val major = components[0].toIntOrNull() ?: return 0
        val minor = components[1].toIntOrNull() ?: return 0
        val patch = components[2].toIntOrNull() ?: return 0

        return major * 10000 + minor * 100 + patch
    }
}

/**
 *
 */