package com.vesam.quiz.data.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ)\u0010\t\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ1\u0010\n\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0004H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/vesam/quiz/data/api/ApiHelper;", "", "initGetQuizResult", "userUuid", "", "userApiToken", "quizId", "", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initQuizDetail", "initSetQuizResult", "userAnswer", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "quiz_debug"})
public abstract interface ApiHelper {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object initQuizDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p3);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object initSetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, @org.jetbrains.annotations.NotNull()
    java.lang.String userAnswer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p4);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object initGetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p3);
}