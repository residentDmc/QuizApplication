package com.vesam.quiz.ui.view.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020 H\u0002J\u0010\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0003J\b\u0010&\u001a\u00020 H\u0002J\b\u0010\'\u001a\u00020 H\u0002J\b\u0010(\u001a\u00020 H\u0002J\u0010\u0010)\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010*\u001a\u00020 H\u0002J\u0010\u0010+\u001a\u00020 2\u0006\u0010$\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020 H\u0002J\u0010\u0010.\u001a\u00020 2\u0006\u0010$\u001a\u00020/H\u0002J\u0012\u00100\u001a\u00020 2\b\u00101\u001a\u0004\u0018\u000102H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001c\u0010\u001d\u00a8\u00063"}, d2 = {"Lcom/vesam/quiz/ui/view/activity/QuizActivity;", "Lcom/vesam/quiz/utils/base/BaseActivity;", "()V", "binding", "Lcom/vesam/quiz/databinding/ActivityQuizBinding;", "dummy", "", "handelErrorTools", "Lcom/vesam/quiz/utils/tools/HandelErrorTools;", "getHandelErrorTools", "()Lcom/vesam/quiz/utils/tools/HandelErrorTools;", "handelErrorTools$delegate", "Lkotlin/Lazy;", "navController", "Landroidx/navigation/NavController;", "progressBarStatus", "quizViewModel", "Lcom/vesam/quiz/ui/viewmodel/QuizViewModel;", "getQuizViewModel", "()Lcom/vesam/quiz/ui/viewmodel/QuizViewModel;", "quizViewModel$delegate", "throwableTools", "Lcom/vesam/quiz/utils/tools/ThrowableTools;", "getThrowableTools", "()Lcom/vesam/quiz/utils/tools/ThrowableTools;", "throwableTools$delegate", "toastTools", "Lcom/vesam/quiz/utils/tools/ToastTools;", "getToastTools", "()Lcom/vesam/quiz/utils/tools/ToastTools;", "toastTools$delegate", "initAction", "", "initBundle", "initClozeQuiz", "initDelayQuizDetailModel", "it", "Lcom/vesam/quiz/data/model/quiz_detail/ResponseQuizDetailModel;", "initHideLoading", "initMultimediaQuiz", "initNavController", "initQuizDetailModel", "initRequest", "initResultQuiz", "", "initShowLoading", "initThrowable", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "quiz_debug"})
public final class QuizActivity extends com.vesam.quiz.utils.base.BaseActivity {
    private com.vesam.quiz.databinding.ActivityQuizBinding binding;
    private androidx.navigation.NavController navController;
    private final kotlin.Lazy toastTools$delegate = null;
    private final kotlin.Lazy throwableTools$delegate = null;
    private final kotlin.Lazy handelErrorTools$delegate = null;
    private final kotlin.Lazy quizViewModel$delegate = null;
    private int progressBarStatus = 0;
    private int dummy = 0;
    
    private final com.vesam.quiz.utils.tools.ToastTools getToastTools() {
        return null;
    }
    
    private final com.vesam.quiz.utils.tools.ThrowableTools getThrowableTools() {
        return null;
    }
    
    private final com.vesam.quiz.utils.tools.HandelErrorTools getHandelErrorTools() {
        return null;
    }
    
    private final com.vesam.quiz.ui.viewmodel.QuizViewModel getQuizViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initAction() {
    }
    
    private final void initBundle() {
    }
    
    private final void initNavController() {
    }
    
    private final void initRequest() {
    }
    
    private final void initShowLoading() {
    }
    
    private final void initHideLoading() {
    }
    
    private final void initResultQuiz(java.lang.Object it) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initDelayQuizDetailModel(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel it) {
    }
    
    private final void initThrowable(java.lang.Throwable it) {
    }
    
    private final void initQuizDetailModel(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel it) {
    }
    
    private final void initClozeQuiz() {
    }
    
    private final void initMultimediaQuiz() {
    }
    
    public QuizActivity() {
        super();
    }
}