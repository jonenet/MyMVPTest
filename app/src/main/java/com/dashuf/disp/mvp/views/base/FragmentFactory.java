package com.dashuf.disp.mvp.views.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ex-zhoulai on 2018/4/9.
 */

public class FragmentFactory {

//    private static final WeakHashMap<String, Fragment> hashMap = new WeakHashMap<>();

    public static Fragment getFragment(Context context, String clazzName, Bundle bundle) {
//        Fragment fragment = hashMap.get(clazzName);
//        if (null == fragment) {
//            fragment = Fragment.instantiate(context, clazzName, bundle);
//            hashMap.put(clazzName, fragment);
//        }
//        return fragment;
        return Fragment.instantiate(context, clazzName, bundle);
    }

//    public static void clear() {
//        hashMap.clear();
//    }


}
