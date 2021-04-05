package com.vesam.quiz.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J)\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ)\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ!\u0010\r\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJI\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0012j\b\u0012\u0004\u0012\u00020\n`\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/vesam/quiz/data/repository/QuizRepository;", "", "apiHelper", "Lcom/vesam/quiz/data/api/ApiHelper;", "(Lcom/vesam/quiz/data/api/ApiHelper;)V", "initGetQuizResult", "userUuid", "", "userApiToken", "quizId", "", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initQuizDetail", "initQuizList", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initSetQuizResult", "periodTime", "userAnswer", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/lang/String;IILjava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "quiz_debug"})
public final class QuizRepository {
    private final com.vesam.quiz.data.api.ApiHelper apiHelper = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initQuizList(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initQuizDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initSetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, int periodTime, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Integer> userAnswer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p5) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initGetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> p3) {
        return null;
    }
    
    public QuizRepository(@org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.api.ApiHelper apiHelper) {
        super();
    }
}