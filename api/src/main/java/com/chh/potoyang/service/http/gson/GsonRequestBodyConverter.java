package com.chh.potoyang.service.http.gson;

import com.chh.potoyang.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by potoyang on 2017/8/7.
 */

public final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
//        Buffer buffer = new Buffer();
//        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
//        JsonWriter jsonWriter = gson.newJsonWriter(writer);
//        adapter.write(jsonWriter, value);
//
        String string = gson.toJson(value);
        LogUtils.i("BB", string);
//        LogUtils.i("BB", value.toString());
//
//        jsonWriter.close();
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());

        return RequestBody.create(MEDIA_TYPE, string);
    }
}
