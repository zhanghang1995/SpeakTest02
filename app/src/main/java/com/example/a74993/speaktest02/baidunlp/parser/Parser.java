/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.example.a74993.speaktest02.baidunlp.parser;

import com.baidu.aip.unit.exception.UnitError;
import com.example.a74993.speaktest02.baidunlp.exception.UnitError;

/**
 * JSON解析
 * @param <T>
 */
public interface Parser<T> {
    T parse(String json) throws UnitError, UnitError;
}
