/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */

package com.example.a74993.speaktest02.model;


/*
 * Created by troy379 on 28.03.17.
 */

import java.util.List;



public interface MessageContentType extends IMessage {

    /**
     * Default media type for image message.
     */
    interface Image extends IMessage {
        String getImageUrl();
    }

    // other default types will be here

    interface ComplexText extends IMessage {

        public List<Message> getComplexMessage();

    }
}
