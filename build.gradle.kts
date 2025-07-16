// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        // 增加 AGC 插件配置
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
//    alias(libs.plugins.google.services) apply false
}