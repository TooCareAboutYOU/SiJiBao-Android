package com.tuoniao.sijibao.anno;

import java.lang.annotation.Repeatable;

/**
 * @author zhangshuai
 * Repeatable: 同一个类型的注解使用多次
 */
@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
