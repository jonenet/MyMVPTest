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
package com.dashuf.disp.mvp.api;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by JessYan on 08/05/2016 11:25
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    int TAG_ONE = 1;
    int TAG_TWO = 2;
    int TAG_THREE = 3;
    int TAG_FOUR = 4;

    String HEADER_URL_TEST = "Domain-Name: douban";
    String HEADER_URL_DASHBOARD = "Domain-Name: dashboard";


    String APP_DOMAIN = "https://api.github.com/";
    String APP_SERVER_JSON = "http://192.168.1.101:3000/";
    String RequestSuccess = "0";
    //    String APP_DASHBOARD = "http://10.64.0.155:8082/";
    String APP_DASHBOARD = "https://pxy-disp-sit2.banketech.com/";
}