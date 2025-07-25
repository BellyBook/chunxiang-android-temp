package com.fantasy.cctemplate.manager

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fantasy.cctemplate.model.APPConfig
import kotlinx.coroutines.launch


/**
 * 有的接口在启动app后只需要掉用成功一次，放在这里
 */
val configStore = ConfigStore.shared
class ConfigStore private constructor() : ViewModel() {
    companion object {
        val shared = ConfigStore()
    }

    fun commonFetch() {
        viewModelScope.launch {
            launch { fetchAppConfigIfNeed() }
        }
    }

    var appConfig by mutableStateOf(APPConfig())
        private set

    suspend fun fetchAppConfigIfNeed(forces: Boolean = false): APPConfig {
        return appConfig
    }
}

