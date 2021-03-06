package com.hawky.rxpal.internal;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * REST APIs服务接口
 *
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public interface ApiService {

    @GET
    Observable<ResponseBody> get(@Url String url);// Content-Type: text/html; charset=UTF-8

    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, Object> params);// Content-Type: text/html; charset=UTF-8

    @POST
    Observable<ResponseBody> post(@Url String url, @Body RequestBody requestBody);// Content-Type: application/json; charset=utf-8

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap Map<String, Object> params);// FormUrlEncoded、FieldMap

    @Multipart
    @POST
    Observable<ResponseBody> uploadFile(@Url String url, @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);// Params、File

}