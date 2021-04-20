package com.vesam.quiz.utils.build_config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/vesam/quiz/utils/build_config/BuildConfig;", "", "()V", "Companion", "quiz_debug"})
public final class BuildConfig {
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String BASE_URL = "";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String BASE_URL_IMAGE_AND_VIDEO_VALUE = "";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CONTENT_TYPE_HEADER = "Content-Type";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String APPLICATION_JSON_HEADER = "application/json";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AUTHORIZATION = "Authorization";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BEARER = "Bearer ";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_UUID = "user_uuid";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_API_TOKEN = "user_api_token";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String USER_UUID_VALUE = "";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String USER_API_TOKEN_VALUE = "";
    private static int USER_QUIZ_ID_VALUE = -1;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String QUIZ_LIST = "quiz/get-quiz-list";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String QUIZ_DETAIL = "quiz/get-quiz-with-details";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SET_QUIZ_RESULT = "quiz/set-quiz-result";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_QUIZ_RESULT = "quiz/get-quiz-result";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String QUIZ_ID = "quiz_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PERIOD_TIME = "period_time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_ANSWERS = "user_answers";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String QUIZ_DATABASE = "quiz_database";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DETAILS_ENTITY = "details_entity";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SET_QUIZ_RESULT_ENTITY = "set_quiz_result_entity";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_QUIZ_RESULT_ENTITY = "get_quiz_result_entity";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_QUESTION = "question";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_USER_QUESTION_LIST = "userQuestionList";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_USER_ANSWER_LIST_ID = "userAnswerListId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_MULTIMEDIA = "multimedia";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FORMAT_TEXT = "text";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FORMAT_VIDEO = "video";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FORMAT_AUDIO = "audio";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FORMAT_IMAGE = "image";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MULTIMEDIA = "multimedia";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CLOZE = "cloze";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CORRECT_ANSWER = "correct_answer.wav";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXAM_PASS = "exam_pass.wav";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String WRONG_ANSWER = "wrong_answer.wav";
    public static final int HIDE_THRESHOLD = 20;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String STEP_BY_STEP = "stepByStep";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FINAL_LEVEL = "finalLevel";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String HOW_DISPLAY_CORRECT_ANSWER = "";
    private static int PASS_CONDITION = -1;
    @org.jetbrains.annotations.NotNull()
    public static final com.vesam.quiz.utils.build_config.BuildConfig.Companion Companion = null;
    
    public BuildConfig() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u001f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010\nR\u000e\u0010$\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u00104\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\b\"\u0004\b6\u0010\nR\u001a\u00107\u001a\u00020 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\'\"\u0004\b9\u0010)R\u000e\u0010:\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\b\"\u0004\b=\u0010\nR\u000e\u0010>\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006?"}, d2 = {"Lcom/vesam/quiz/utils/build_config/BuildConfig$Companion;", "", "()V", "APPLICATION_JSON_HEADER", "", "AUTHORIZATION", "BASE_URL", "getBASE_URL", "()Ljava/lang/String;", "setBASE_URL", "(Ljava/lang/String;)V", "BASE_URL_IMAGE_AND_VIDEO_VALUE", "getBASE_URL_IMAGE_AND_VIDEO_VALUE", "setBASE_URL_IMAGE_AND_VIDEO_VALUE", "BEARER", "BUNDLE_MULTIMEDIA", "BUNDLE_QUESTION", "BUNDLE_USER_ANSWER_LIST_ID", "BUNDLE_USER_QUESTION_LIST", "CLOZE", "CONTENT_TYPE_HEADER", "CORRECT_ANSWER", "DETAILS_ENTITY", "EXAM_PASS", "FINAL_LEVEL", "FORMAT_AUDIO", "FORMAT_IMAGE", "FORMAT_TEXT", "FORMAT_VIDEO", "GET_QUIZ_RESULT", "GET_QUIZ_RESULT_ENTITY", "HIDE_THRESHOLD", "", "HOW_DISPLAY_CORRECT_ANSWER", "getHOW_DISPLAY_CORRECT_ANSWER", "setHOW_DISPLAY_CORRECT_ANSWER", "MULTIMEDIA", "PASS_CONDITION", "getPASS_CONDITION", "()I", "setPASS_CONDITION", "(I)V", "PERIOD_TIME", "QUIZ_DATABASE", "QUIZ_DETAIL", "QUIZ_ID", "QUIZ_LIST", "SET_QUIZ_RESULT", "SET_QUIZ_RESULT_ENTITY", "STEP_BY_STEP", "USER_ANSWERS", "USER_API_TOKEN", "USER_API_TOKEN_VALUE", "getUSER_API_TOKEN_VALUE", "setUSER_API_TOKEN_VALUE", "USER_QUIZ_ID_VALUE", "getUSER_QUIZ_ID_VALUE", "setUSER_QUIZ_ID_VALUE", "USER_UUID", "USER_UUID_VALUE", "getUSER_UUID_VALUE", "setUSER_UUID_VALUE", "WRONG_ANSWER", "quiz_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBASE_URL() {
            return null;
        }
        
        public final void setBASE_URL(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBASE_URL_IMAGE_AND_VIDEO_VALUE() {
            return null;
        }
        
        public final void setBASE_URL_IMAGE_AND_VIDEO_VALUE(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUSER_UUID_VALUE() {
            return null;
        }
        
        public final void setUSER_UUID_VALUE(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUSER_API_TOKEN_VALUE() {
            return null;
        }
        
        public final void setUSER_API_TOKEN_VALUE(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public final int getUSER_QUIZ_ID_VALUE() {
            return 0;
        }
        
        public final void setUSER_QUIZ_ID_VALUE(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHOW_DISPLAY_CORRECT_ANSWER() {
            return null;
        }
        
        public final void setHOW_DISPLAY_CORRECT_ANSWER(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public final int getPASS_CONDITION() {
            return 0;
        }
        
        public final void setPASS_CONDITION(int p0) {
        }
        
        private Companion() {
            super();
        }
    }
}