package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_VIDEO


data class Question(
    @SerializedName("answers")
    @ColumnInfo(name = "answers")
    val answers: ArrayList<Answer>,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val quizDescription: QuizDescription?,
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("period_time")
    @ColumnInfo(name = "period_time")
    val periodTime: Int,
    @SerializedName("sort")
    @ColumnInfo(name = "sort")
    val sort: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String
){
    var uriPath = ""
        get() {
            return when (quizDescription!!.format) {
                FORMAT_VIDEO ->"${nameFileEncrypt(quizDescription.urlContent)}${MIM_TYPE_VIDEO}"
                else -> "${nameFileEncrypt(quizDescription.urlContent)}${MIM_TYPE_AUDIO}"
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