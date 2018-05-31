package com.jess.arms.base.delegate;

import com.jess.arms.mvp.Message;

/**
 * Created by ex-zhoulai on 2018/5/25.
 */

public interface IHandler {

    void handleMessage(Message msg);

}
