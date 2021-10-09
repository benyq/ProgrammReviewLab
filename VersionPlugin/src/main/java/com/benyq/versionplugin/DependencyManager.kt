package com.benyq.versionplugin

/**
 * 配置和 build相关的
 */
object BuildVersion {
    const val compileSdk = 30
    const val buildTools = "30.0.2"
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val kotlin = "1.4.30"
    const val core_ktx = "1.6.0"
    const val appcompat = "1.3.0"
    const val material = "1.3.0"
    const val retrofit = "2.9.0"
    const val constraintLayout = "2.0.4"
    const val fragment = "1.3.4"
    const val BRV = "1.3.33"
    const val gson = "2.8.8"
    const val glide = "4.12.0"
}

object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val BRV = "com.github.liangjingkanji:BRV:${Versions.BRV}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}
