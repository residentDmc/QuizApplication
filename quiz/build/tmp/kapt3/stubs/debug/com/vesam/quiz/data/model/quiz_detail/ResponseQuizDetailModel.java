package com.vesam.quiz.data.model.quiz_detail;

import java.lang.System;

@androidx.room.Entity(tableName = "details_entity")
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\u0019\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH\u00c6\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001d"}, d2 = {"Lcom/vesam/quiz/data/model/quiz_detail/ResponseQuizDetailModel;", "", "id", "", "details", "Lcom/vesam/quiz/data/model/quiz_detail/Details;", "questions", "Ljava/util/ArrayList;", "Lcom/vesam/quiz/data/model/quiz_detail/Question;", "Lkotlin/collections/ArrayList;", "(ILcom/vesam/quiz/data/model/quiz_detail/Details;Ljava/util/ArrayList;)V", "getDetails", "()Lcom/vesam/quiz/data/model/quiz_detail/Details;", "getId", "()I", "setId", "(I)V", "getQuestions", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "quiz_debug"})
public final class ResponseQuizDetailModel {
    @androidx.room.ColumnInfo(name = "id")
    @androidx.room.PrimaryKey(autoGenerate = false)
    @com.google.gson.annotations.SerializedName(value = "id")
    private int id;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "details")
    private final com.vesam.quiz.data.model.quiz_detail.Details details = null;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "questions")
    private final java.util.ArrayList<com.vesam.quiz.data.model.quiz_detail.Question> questions = null;
    
    public final int getId() {
        return 0;
    }
    
    public final void setId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.vesam.quiz.data.model.quiz_detail.Details getDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.vesam.quiz.data.model.quiz_detail.Question> getQuestions() {
        return null;
    }
    
    public ResponseQuizDetailModel(int id, @org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.model.quiz_detail.Details details, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.vesam.quiz.data.model.quiz_detail.Question> questions) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.vesam.quiz.data.model.quiz_detail.Details component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.vesam.quiz.data.model.quiz_detail.Question> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel copy(int id, @org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.model.quiz_detail.Details details, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.vesam.quiz.data.model.quiz_detail.Question> questions) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}