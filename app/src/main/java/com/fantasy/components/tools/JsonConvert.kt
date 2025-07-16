package com.fantasy.components.tools

import androidx.annotation.Keep
import com.fantasy.components.network.ccJson
import com.fantasy.components.network.ccJsonBuild
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * fromJson 解析的类不能被混淆 需要被  @Keep 注解修饰
 */
inline fun <reified T> fromJson(json: Any?): T? {
    try {
        return when (json) {
            is String -> ccJson.decodeFromString(json)
            else -> ccJson.decodeFromString(ccJson.encodeToString(json))
        }
    } catch (e: Exception) {
        cclog("数据解析失败 fromJson：${T::class.java}  json:$json")
        e.printStackTrace()
        return null
    }
}

inline fun <reified T> toJsonString(model: T?): String {
    if (model == null) return ""

    try {
        val result = ccJson.encodeToString(model)
        return result
    } catch (e: Exception) {
        cclog("数据解析失败 toJsonString：${model?.javaClass}  model:$model")
        return ""
    }
}

@Keep
@Serializable
private data class JsonConvertTestModel(
    val string1: String? = "",
    val string2: String? = "",
    val string3: String? = "",
    val double: Double = 0.0,
    val double2: Double? = null,
    val float: Float = 0f,
    val long: Long = 0,
    val long2: Long? = null,
    val int: Int = 0,
    val int2: Int? = null,
    val boolean: Boolean = false,
    val boolean2: Boolean?,
    val message: String = "",
    val list: List<String> = listOf("1", "@")
)

private fun main() {
    val json = Json { ccJsonBuild(true) }
    val jsonString = """
        {
            "string1": null,
            "string2": 1.2,
            "string3": 2,
            "list": null
        }
    """.trimIndent()
    val obj = json.decodeFromString<JsonConvertTestModel>(jsonString)
    cclog(obj)
    val model = JsonConvertTestModel(
        string1 = "",
        double2 = null,
        float = 0f,
        long2 = null,
        int2 = null,
        boolean2 = null,
        list = listOf("1", "2", "3")
    )
    val js = json.encodeToString(model)
    cclog(js)
}