package com.vesam.quiz.data.model.quiz_detail;

import java.lang.System;

@androidx.room.Entity(tableName = "get_quiz_with_details_entity", foreignKeys = {@androidx.room.ForeignKey(entity = com.vesam.quiz.data.model.quiz_list.Quiz.class, childColumns = {"id"}, onDelete = 5, parentColumns = {"id"})})
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J-\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lcom/vesam/quiz/data/model/quiz_detail/ResponseQuizDetailModel;", "", "id", "", "details", "Lcom/vesam/quiz/data/model/quiz_detail/Details;", "questions", "", "Lcom/vesam/quiz/data/model/quiz_detail/Question;", "(ILcom/vesam/quiz/data/model/quiz_detail/Details;Ljava/util/List;)V", "getDetails", "()Lcom/vesam/quiz/data/model/quiz_detail/Details;", "getId", "()I", "setId", "(I)V", "getQuestions", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "quiz_debug"})
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
    private final java.util.List<com.vesam.quiz.data.model.quiz_detail.Question> questions = null;
    
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
    public final java.util.List<com.vesam.quiz.data.model.quiz_detail.Question> getQuestions() {
        return null;
    }
    
    public ResponseQuizDetailModel(int id, @org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.model.quiz_detail.Details details, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vesam.quiz.data.model.quiz_detail.Question> questions) {
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
    public final java.util.List<com.vesam.quiz.data.model.quiz_detail.Question> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel copy(int id, @org.jetbrains.annotations.NotNull()
    com.vesam.quiz.data.model.quiz_detail.Details details, @org.jetbrains.annotations.NotNull()
    java.util.List<com.vesam.quiz.data.model.quiz_detail.Question> questions) {
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