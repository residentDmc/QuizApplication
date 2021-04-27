package com.vesam.quiz.data.model.file_download

import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_VIDEO

data class FileDownload(val title: String, val format: String, val url: String)