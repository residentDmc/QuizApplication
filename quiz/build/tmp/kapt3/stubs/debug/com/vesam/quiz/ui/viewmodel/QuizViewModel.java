package com.vesam.quiz.ui.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ7\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J/\u0010\u0018\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019JD\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0016\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u001dj\b\u0012\u0004\u0012\u00020\u0010`\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lcom/vesam/quiz/ui/viewmodel/QuizViewModel;", "Lcom/vesam/quiz/utils/abstracts/BaseViewModel;", "quizRepository", "Lcom/vesam/quiz/data/repository/QuizRepository;", "quizDatabaseRepository", "Lcom/vesam/quiz/data/repository/QuizDatabaseRepository;", "quizDetailDatabaseRepository", "Lcom/vesam/quiz/data/repository/QuizDetailDatabaseRepository;", "(Lcom/vesam/quiz/data/repository/QuizRepository;Lcom/vesam/quiz/data/repository/QuizDatabaseRepository;Lcom/vesam/quiz/data/repository/QuizDetailDatabaseRepository;)V", "initGetQuizResult", "Landroidx/lifecycle/LiveData;", "", "userUuid", "", "userApiToken", "quizId", "", "initQuizDetail", "initQuizList", "initRequestQuizDetail", "", "liveData", "Landroidx/lifecycle/MutableLiveData;", "(Ljava/lang/String;Ljava/lang/String;ILandroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initRequestQuizList", "(Ljava/lang/String;Ljava/lang/String;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initSetQuizResult", "periodTime", "userAnswer", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "quiz_debug"})
public final class QuizViewModel extends com.vesam.quiz.utils.abstracts.BaseViewModel {
    private final com.vesam.quiz.data.repository.QuizRepository quizRepository = null;
    private final com.vesam.quiz.data.repository.QuizDatabaseRepository quizDatabaseRepository = null;
    private final com.vesam.quiz.data.repository.QuizDetailDatabaseRepository quizDetailDatabaseRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Object> initQuizList(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Object> initQuizDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Object> initSetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId, int periodTime, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Integer> userAnswer) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Object> initGetQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userUuid, @org.jetbrains.annotations.NotNull()
    java.lang.String userApiToken, int quizId) {
        return null;
    }
    
    public QuizViewModel(@org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.repository.QuizRepository quizRepository, @org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.repository.QuizDatabaseRepository quizDatabaseRepository, @org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.repository.QuizDetailDatabaseRepository quizDetailDatabaseRepository) {
        super();
    }
}