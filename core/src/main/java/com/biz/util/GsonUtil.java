package com.biz.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wangwei on 2016/1/27.
 */
public class GsonUtil {
    public static <T> T fromJson(String json, Type classOfT) {
        Gson gson =new GsonBuilder().registerTypeAdapter(Long.class, new JsonDeserializer<Long>() {
            @Override
            public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json.getAsJsonPrimitive().getAsLong();
                }catch (Exception e){}
                try {
                    JsonArray jsonElements=json.getAsJsonPrimitive().getAsJsonArray();
                    List<Integer> list=Lists.newArrayList();
                    for(JsonElement jsonElement:jsonElements){
                        list.add(jsonElement.getAsInt());
                    }
                    if(list.size()==2){
                        long date=list.get(0)*60*60*1000+list.get(1)*60*1000;
                        return date;
                    }
                }catch (Exception e){}
                try {
                    String data=json.getAsJsonPrimitive().getAsString();
                    long d=Utils.getLong(data);
                    if(d!=0){
                        return d;
                    }
                    try {
                       d=TimeUtil.parse(data,TimeUtil.FORMAT_YYYYMMDDHHMMSS);
                    }catch (Exception e1){}
                    if(d>0) return d;
                    try {
                        d= TimeUtil.parse(data,TimeUtil.FORMAT_YYYYMMDD);
                    }catch (Exception e1){}
                    if(d>0) return d;
                    try {
                        return TimeUtil.parse(data,TimeUtil.FORMAT_YYYYMM);
                    }catch (Exception e1){}
                }catch (Exception e){}

                return 0l;
            }
        }).create();
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }


}
