package com.fantasy.components.extension

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.tools.cckv
import com.fantasy.components.tools.cclog
import com.fantasy.components.tools.getContext
import com.fantasy.components.tools.inPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

enum class AppLanguage(
    val code: String,
    val displayName: String,
    val flag: String
) {
    system("system", "跟随系统", "🌐"),
    zh("zh-CN", "简体中文", "🇨🇳"),
    zh_tw("zh-TW", "繁体中文", "🇨🇳"),
    en("en", "English", "🇺🇸"),
    ja("ja", "日本語", "🇯🇵"),
    ko("ko", "한국어", "🇰🇷");

    val local: Locale
        get() = when (this) {
            system -> Locale.getDefault()
            zh -> Locale.CHINESE
            zh_tw -> Locale.TAIWAN
            en -> Locale.ENGLISH
            ja -> Locale.JAPANESE
            ko -> Locale.KOREAN
        }

    companion object {
        fun fromCode(code: String): AppLanguage {
            return entries.firstOrNull { it.code == code } ?: system
        }
    }
}

var appLanguageManager = AppLanguageManager()
class AppLanguageManager: BaseViewModel() {
    var currentLanguage by mutableStateOf(AppLanguage.system)
    val currentLocale: Locale get() = currentLanguage.local
    val isZh get() = currentLanguage == AppLanguage.zh

    init {
        viewModelScope.launch {
            delay(100)
            currentLanguage = AppLanguage.fromCode(cckv.decodeString("app_language", "system"))

            snapshotFlow { currentLanguage }
                .collect {
                    cclog("当前语言变更: ${it.code} ${it.displayName}")
                    cckv.encode("app_language", it.code)
                }
        }
    }

}


/**
 * 可以在 composable 外用, 通过 koin 提供的依赖注入函数解决了预览的问题
 */
fun getLocalizedContext(locale: Locale): Context {
    if (inPreview) {
        return getContext
    }
    val context = getContext
    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    return context.createConfigurationContext(config)
}

//This function takes an integer as an argument and returns a localized string based on the current locale
fun Int.local() = try {
    getLocalizedContext(appLanguageManager.currentLocale).getString(this)
} catch (e: Exception) {
    cclog("i18n error: $this  $e")
    e.printStackTrace()
    "$this"
}
fun Int.local(vararg formatArgs: Any) = try {
    getLocalizedContext(appLanguageManager.currentLocale).getString(this, *formatArgs)
} catch (e: Exception) {
    cclog("i18n error: $this  $e")
    e.printStackTrace()
    "$this"
}