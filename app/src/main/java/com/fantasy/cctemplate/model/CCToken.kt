package com.fantasy.cctemplate.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CCToken(
    val access_token: String = "",
    val refresh_token: String = "",
    val expires_at: Long = 0L,
    val expires_in: Double = 60.0,
    val token_type: String = "bearer"
)  {

}
