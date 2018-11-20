package com.jess.arms.utils.rxFun;

/**
 * 作者:      周来
 * 包名:      com.jess.arms.utils.rxFun
 * 工程名:    MyMVPTest
 * 时间:      2018/11/13
 * 说明:
 */
public interface Observable {
    Observable lift(Operator operator);
}
