package com.tuoniao.sijibao;

import com.tuoniao.sijibao.model.UserDataBean;

public interface UserFactory<P extends UserDataBean> {

    /**
     * 创建UserDataBean对象
     * @param firstName 姓
     * @param lastName  名
     * @return
     */
    P create(String firstName, String lastName);
}
