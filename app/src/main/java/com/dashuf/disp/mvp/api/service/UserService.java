/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dashuf.disp.mvp.api.service;

import android.support.v4.media.VolumeProviderCompat;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

import com.dashuf.disp.mvp.api.Api;
import com.dashuf.disp.mvp.model.entity.DiscoveryNewsBean;
import com.dashuf.disp.mvp.model.entity.DiscoveryTopTabBean;
import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.dashuf.disp.mvp.model.entity.LoginBean;
import com.dashuf.disp.mvp.model.entity.PostTestBean;
import com.dashuf.disp.mvp.model.entity.RegisterBean;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.mvp.model.entity.User;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ================================================
 * 展示 {@link Retrofit#create(Class)} 中需要传入的 ApiService 的使用方式
 * 存放关于用户的一些 API
 * <p>    https://www.jianshu.com/p/308f3c54abdd
 * Created by JessYan on 08/05/2016 12:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface UserService {
    //    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @GET("/users")
    Observable<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);

    @Headers({Api.HEADER_URL_TEST})
    @GET("/posts")
    Observable<List<PostTestBean>> postTest();


    @Headers({Api.HEADER_URL_DASHBOARD})
    @FormUrlEncoded
    @POST("api/config/findAppConfigs")
    Observable<ResultBean<HomeBean>> getHomeData(@FieldMap Map<String, String> params);

    @Headers({Api.HEADER_URL_DASHBOARD})
    @FormUrlEncoded
    @POST("cms-api/cms/api/column/list")
    Observable<ResultBean<List<DiscoveryTopTabBean>>> getDiscoveryPages(@FieldMap Map<String, String> params);

    @Headers({Api.HEADER_URL_DASHBOARD})
    @FormUrlEncoded
    @POST("cms-api/cms/api/article/list")
    Observable<ResultBean<DiscoveryNewsBean>> getNewsPageDetail(@FieldMap Map<String, String> params);

    @Headers({Api.HEADER_URL_DASHBOARD})
    @FormUrlEncoded
    @POST("/cms-api/cms/api/article/list")
    Observable<String> getRecyclerList(@FieldMap Map<String, String> params);

    //    @Headers({Api.HEADER_URL_DASHBOARD})
//    @Headers("Content-Type: application/json")
    @POST("user/login")
    Observable<ResultBean<LoginBean>> doLogin(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("user/register")
    Observable<ResultBean<RegisterBean>> doRegister(@FieldMap Map<String, String> params);


}
