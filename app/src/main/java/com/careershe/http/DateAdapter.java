package com.careershe.http;

import android.annotation.SuppressLint;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 类描述：解析服务器的时间戳。
 *
 * @author HeHongdan
 * @version v1/4/21
 * @date 1/4/21
 */
public class DateAdapter implements JsonDeserializer<Date> {

    /** 时间格式工具。"2021-06-02 12:29:07" */
    @SuppressLint("SimpleDateFormat")
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** 时间格式工具。"2021-06-02T12:29:07.198+0000" */
    @SuppressLint("SimpleDateFormat")
    private final DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Date deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        //时区
        df.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        try {
            return df.parse(arg0.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
