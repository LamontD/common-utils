/*
 * Copyright 2020 lamontdozierjr.
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
package com.lamontd.utils.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Singleton for getting a Jackson JSON mapper that looks the way I want it to.
 *
 * @author lamontdozierjr
 */
public class JacksonMapper {

    private static ObjectMapper jacksonMapper;
    private static ObjectMapper prettyMapper;

    private JacksonMapper() {
    }

    public static final ObjectMapper getStandardMapper() {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
            jacksonMapper.registerModule(new JavaTimeModule());
            jacksonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            jacksonMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            jacksonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            jacksonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return jacksonMapper;
    }

    public static final ObjectMapper getPrettyMapper() {
        if (prettyMapper == null) {
            prettyMapper = new ObjectMapper();
            prettyMapper.registerModule(new JavaTimeModule());
            prettyMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            prettyMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            prettyMapper.enable(SerializationFeature.INDENT_OUTPUT);
            prettyMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            prettyMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        }
        return prettyMapper;
    }

}
