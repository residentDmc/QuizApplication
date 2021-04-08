package com.vesam.quiz.data.database;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.vesam.quiz.utils.converter.ListQuestionConverter.class, com.vesam.quiz.utils.converter.DetailsConverter.class})
@androidx.room.Database(entities = {com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/vesam/quiz/data/database/QuizDatabase;", "Landroidx/room/RoomDatabase;", "()V", "detailsDAO", "Lcom/vesam/quiz/data/database/DetailsDAO;", "Companion", "quiz_debug"})
public abstract class QuizDatabase extends androidx.room.RoomDatabase {
    private static com.vesam.quiz.data.database.QuizDatabase instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.vesam.quiz.data.database.QuizDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.vesam.quiz.data.database.DetailsDAO detailsDAO();
    
    public QuizDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/vesam/quiz/data/database/QuizDatabase$Companion;", "", "()V", "instance", "Lcom/vesam/quiz/data/database/QuizDatabase;", "getInstance", "context", "Landroid/content/Context;", "quiz_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.vesam.quiz.data.database.QuizDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}