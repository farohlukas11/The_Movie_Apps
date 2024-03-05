// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.android.build.gradle)
        classpath(libs.jetbrainsKotlinGradle)
        classpath(libs.com.dagger.google.plugin)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidKsp) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.daggerHiltAndroid) apply false
}
