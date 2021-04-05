package com.vesam.quiz.utils.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vesam.quiz.data.model.quiz_detail.Details;

import java.lang.reflect.Type;

public class DetailsConverter {
    @TypeConverter
    public String fromValuesRuleModel(Details value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Details>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public Details toRuleModelValues(String value) {
        if (value== null) return (null);
        Gson gson = new Gson();
        Type type = new TypeToken<Details>() {
        }.getType();
        return gson.fromJson(value, type);
    }
}
