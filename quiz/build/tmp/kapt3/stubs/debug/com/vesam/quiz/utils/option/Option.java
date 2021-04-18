package com.vesam.quiz.utils.option;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/vesam/quiz/utils/option/Option;", "", "()V", "Companion", "quiz_debug"})
public final class Option {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_BASE_URL_VALUE = "base_url";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE = "base_url_image_and_video";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_USER_UUID_VALUE = "user_uuid";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_USER_API_TOKEN_VALUE = "user_api_token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_QUIZ_ID_VALUE = "quiz_api";
    @org.jetbrains.annotations.NotNull()
    public static final com.vesam.quiz.utils.option.Option.Companion Companion = null;
    
    public Option() {
        super();
    }
    
    public static final void start(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String baseUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String baseUrlImageAndVideo, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, int quizId) {
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J8\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/vesam/quiz/utils/option/Option$Companion;", "", "()V", "BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE", "", "BUNDLE_BASE_URL_VALUE", "BUNDLE_QUIZ_ID_VALUE", "BUNDLE_USER_API_TOKEN_VALUE", "BUNDLE_USER_UUID_VALUE", "start", "", "activity", "Landroid/app/Activity;", "baseUrl", "baseUrlImageAndVideo", "token", "userId", "quizId", "", "quiz_debug"})
    public static final class Companion {
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity, @org.jetbrains.annotations.NotNull()
        java.lang.String baseUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String baseUrlImageAndVideo, @org.jetbrains.annotations.NotNull()
        java.lang.String token, @org.jetbrains.annotations.NotNull()
        java.lang.String userId, int quizId) {
        }
        
        private Companion() {
            super();
        }
    }
}