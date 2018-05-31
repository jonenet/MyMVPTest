package com.dashuf.disp.dagger.anno;


import javax.inject.Qualifier;

/**
 * Created by ex-zhoulai on 2018/5/31.
 */

@Qualifier
public @interface AdapterQualifier {
    String value() default "";
}
