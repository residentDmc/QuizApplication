package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_VIDEO

data class Answer(
    @SerializedName("description")
    val description: Description?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_correct")
    val isCorrect: Int,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("title")
    var title: String
) {
    var isSuccess = 0
    var isCheckLevel = 0
    var isSelected = false
    var isEnable = false
    var isCorrectItem = false
    var uriPath = ""
        get() {
            return when (description!!.format) {
                FORMAT_VIDEO ->"${nameFileEncrypt(description.urlContent)}${MIM_TYPE_VIDEO}"
                else -> "${nameFileEncrypt(description.urlContent)}${MIM_TYPE_AUDIO}"
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