package com.jess.arms;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * 作者:      周来
 * 包名:      com.jess.arms
 * 工程名:    MyMVPTest
 * 时间:      2018/11/8
 * 说明:
 */
public class Test {

    public static void main(String[] args) {

        Gson gson = new Gson();
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("jone", "cccc"));
        list.add(new Bean("fiona", "6666"));
        String s = gson.toJson(list, new TypeToken<ArrayList<Bean>>() {
        }.getType());
        System.out.println(s);

    }

    static class Bean {

        String name;
        String value;

        public Bean(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

}
