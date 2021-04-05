package com.vesam.quiz.ui.view.adapter.quiz_list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u000eH\u0017J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\u001a\u001a\u00020\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u001cR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lcom/vesam/quiz/ui/view/adapter/quiz_list/QuizAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/vesam/quiz/ui/view/adapter/quiz_list/ViewHolderQuiz;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/vesam/quiz/data/model/quiz_list/Quiz;", "onClickListenerAny", "Lcom/vesam/quiz/interfaces/OnClickListenerAny;", "getOnClickListenerAny", "()Lcom/vesam/quiz/interfaces/OnClickListenerAny;", "setOnClickListenerAny", "(Lcom/vesam/quiz/interfaces/OnClickListenerAny;)V", "getItemCount", "", "getViewHolder", "Landroid/view/View;", "parent", "Landroid/view/ViewGroup;", "onBindViewHolder", "", "viewHolderQuiz", "position", "onCreateViewHolder", "viewType", "setOnItemClickListener", "updateList", "listQuiz", "", "quiz_debug"})
public final class QuizAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.vesam.quiz.ui.view.adapter.quiz_list.ViewHolderQuiz> {
    public com.vesam.quiz.interfaces.OnClickListenerAny onClickListenerAny;
    private final java.util.ArrayList<com.vesam.quiz.data.model.quiz_list.Quiz> list = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.vesam.quiz.interfaces.OnClickListenerAny getOnClickListenerAny() {
        return null;
    }
    
    public final void setOnClickListenerAny(@org.jetbrains.annotations.NotNull()
    com.vesam.quiz.interfaces.OnClickListenerAny p0) {
    }
    
    public final void setOnItemClickListener(@org.jetbrains.annotations.NotNull()
    com.vesam.quiz.interfaces.OnClickListenerAny onClickListenerAny) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.vesam.quiz.ui.view.adapter.quiz_list.ViewHolderQuiz onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    private final android.view.View getViewHolder(android.view.ViewGroup parent) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.vesam.quiz.ui.view.adapter.quiz_list.ViewHolderQuiz viewHolderQuiz, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.vesam.quiz.data.model.quiz_list.Quiz> listQuiz) {
    }
    
    public QuizAdapter() {
        super();
    }
}