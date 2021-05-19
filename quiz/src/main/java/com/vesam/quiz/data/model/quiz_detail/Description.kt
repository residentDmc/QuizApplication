package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig

data class Description(
    @SerializedName("content")
    var content: String?,
    @SerializedName("format")
    var format: String,
) {
    val urlContent
        get() = BuildConfig.BASE_URL_IMAGE_AND_VIDEO_VALUE + content

    var uriPath = ""
        get() {
            return when (format) {
                BuildConfig.FORMAT_VIDEO -> "${nameFileEncrypt(urlContent)}${BuildConfig.MIM_TYPE_VIDEO}"
                else -> "${nameFileEncrypt(urlContent)}${BuildConfig.MIM_TYPE_AUDIO}"
            }
        }

    private fun nameFileEncrypt(filename: String): String {
        val lastSlashChar = filename.lastIndexOf("/")
        val lastSlashChars = filename.lastIndexOf(".mp4")
        val lastSlashChara = filename.lastIndexOf(".mp3")
        return when {
            lastSlashChars > -1 -> filename.substring(lastSlashChar + 1, lastSlashChars - 1)
            lastSlashChara > -1 -> filename.substring(lastSlashChar + 1, lastSlashChara - 1)
            else -> filename.substring(lastSlashChar + 1)
        }
    }
}