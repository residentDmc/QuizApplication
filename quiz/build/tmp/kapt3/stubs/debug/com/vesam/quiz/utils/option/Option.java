package com.vesam.quiz.utils.option;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/vesam/quiz/utils/option/Option;", "", "()V", "Companion", "quiz_debug"})
public final class Option {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_BASE_URL_VALUE = "base_url";
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
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/vesam/quiz/utils/option/Option$Companion;", "", "()V", "BUNDLE_BASE_URL_VALUE", "", "BUNDLE_QUIZ_ID_VALUE", "BUNDLE_USER_API_TOKEN_VALUE", "BUNDLE_USER_UUID_VALUE", "start", "", "context", "Landroid/content/Context;", "baseUrl", "token", "userId", "quizId", "", "quiz_debug"})
    public static final class Companion {
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String baseUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String token, @org.jetbrains.annotations.NotNull()
        java.lang.String userId, int quizId) {
        }
        
        private Companion() {
            super();
        }
    }
}