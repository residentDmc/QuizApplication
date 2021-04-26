package com.vesam.quiz.interfaces

import java.io.File


interface OnCacheAvailable {
    fun onCacheAvailable(cacheFile: File,url:String,percentsAvailable:Int)
}