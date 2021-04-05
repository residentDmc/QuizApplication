package com.vesam.quiz.utils.converter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/vesam/quiz/utils/converter/ListAnswerConverter;", "", "()V", "Companion", "quiz_debug"})
public final class ListAnswerConverter {
    @org.jetbrains.annotations.NotNull()
    private static com.google.gson.Gson gson;
    @org.jetbrains.annotations.NotNull()
    public static final com.vesam.quiz.utils.converter.ListAnswerConverter.Companion Companion = null;
    
    public ListAnswerConverter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public static final java.util.List<com.vesam.quiz.data.model.quiz_detail.Answer> fromTimestamp(@org.jetbrains.annotations.Nullable()
    java.lang.String data) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public static final java.lang.String someObjectListToString(@org.jetbrains.annotations.Nullable()
    java.util.List<com.vesam.quiz.data.model.quiz_detail.Answer> someObjects) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0010"}, d2 = {"Lcom/vesam/quiz/utils/converter/ListAnswerConverter$Companion;", "", "()V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "setGson", "(Lcom/google/gson/Gson;)V", "fromTimestamp", "", "Lcom/vesam/quiz/data/model/quiz_detail/Answer;", "data", "", "someObjectListToString", "someObjects", "quiz_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.gson.Gson getGson() {
            return null;
        }
        
        public final void setGson(@org.jetbrains.annotations.NotNull()
        com.google.gson.Gson p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        @androidx.room.TypeConverter()
        public final java.util.List<com.vesam.quiz.data.model.quiz_detail.Answer> fromTimestamp(@org.jetbrains.annotations.Nullable()
        java.lang.String data) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @androidx.room.TypeConverter()
        public final java.lang.String someObjectListToString(@org.jetbrains.annotations.Nullable()
        java.util.List<com.vesam.quiz.data.model.quiz_detail.Answer> someObjects) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}