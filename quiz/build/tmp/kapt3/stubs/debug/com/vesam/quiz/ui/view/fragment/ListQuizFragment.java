package com.vesam.quiz.ui.view.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020%H\u0002J\u0010\u0010\'\u001a\u00020%2\u0006\u0010(\u001a\u00020)H\u0002J\u0014\u0010*\u001a\u00020%2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030,H\u0002J\b\u0010-\u001a\u00020%H\u0002J\u0010\u0010.\u001a\u00020%2\u0006\u0010+\u001a\u00020)H\u0002J\u0010\u0010/\u001a\u00020%2\u0006\u0010+\u001a\u00020)H\u0002J\u0010\u00100\u001a\u00020%2\u0006\u0010+\u001a\u000201H\u0002J$\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u0001072\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u001a\u0010:\u001a\u00020%2\u0006\u0010;\u001a\u0002032\b\u00108\u001a\u0004\u0018\u000109H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b!\u0010\"\u00a8\u0006<"}, d2 = {"Lcom/vesam/quiz/ui/view/fragment/ListQuizFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/vesam/quiz/databinding/FragmentListQuizBinding;", "handelErrorTools", "Lcom/vesam/quiz/utils/tools/HandelErrorTools;", "getHandelErrorTools", "()Lcom/vesam/quiz/utils/tools/HandelErrorTools;", "handelErrorTools$delegate", "Lkotlin/Lazy;", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "navController$delegate", "quizAdapter", "Lcom/vesam/quiz/ui/view/adapter/quiz_list/QuizAdapter;", "getQuizAdapter", "()Lcom/vesam/quiz/ui/view/adapter/quiz_list/QuizAdapter;", "quizAdapter$delegate", "quizViewModel", "Lcom/vesam/quiz/ui/viewmodel/QuizViewModel;", "getQuizViewModel", "()Lcom/vesam/quiz/ui/viewmodel/QuizViewModel;", "quizViewModel$delegate", "throwableTools", "Lcom/vesam/quiz/utils/tools/ThrowableTools;", "getThrowableTools", "()Lcom/vesam/quiz/utils/tools/ThrowableTools;", "throwableTools$delegate", "toastTools", "Lcom/vesam/quiz/utils/tools/ToastTools;", "getToastTools", "()Lcom/vesam/quiz/utils/tools/ToastTools;", "toastTools$delegate", "initAction", "", "initAdapter", "initOnItemClick", "any", "", "initQuizListModel", "it", "", "initRequestListQuiz", "initResultListQuiz", "initResultQuizDetail", "initThrowable", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "quiz_debug"})
public final class ListQuizFragment extends androidx.fragment.app.Fragment {
    private com.vesam.quiz.databinding.FragmentListQuizBinding binding;
    private final kotlin.Lazy navController$delegate = null;
    private final kotlin.Lazy toastTools$delegate = null;
    private final kotlin.Lazy throwableTools$delegate = null;
    private final kotlin.Lazy handelErrorTools$delegate = null;
    private final kotlin.Lazy quizViewModel$delegate = null;
    private final kotlin.Lazy quizAdapter$delegate = null;
    
    private final androidx.navigation.NavController getNavController() {
        return null;
    }
    
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
    
    private final com.vesam.quiz.ui.view.adapter.quiz_list.QuizAdapter getQuizAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initAction() {
    }
    
    private final void initRequestListQuiz() {
    }
    
    private final void initResultListQuiz(java.lang.Object it) {
    }
    
    private final void initQuizListModel(java.util.List<?> it) {
    }
    
    private final void initThrowable(java.lang.Throwable it) {
    }
    
    private final void initAdapter() {
    }
    
    private final void initOnItemClick(java.lang.Object any) {
    }
    
    private final void initResultQuizDetail(java.lang.Object it) {
    }
    
    public ListQuizFragment() {
        super();
    }
}