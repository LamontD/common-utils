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
package com.lamontd.utils.transport;

import java.lang.reflect.Field;
import java.util.HashMap;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * Object that uses reflection-based mapping to package an object for transporting. 
 * @author lamontdozierjr
 */
public class MappedTransportObject {
    private String objectType;
    private String transactionId;
    private HashMap<String, Object> attributes = new HashMap<>();
    public MappedTransportObject() {}
    public MappedTransportObject(Object subject) {
        if (subject != null) {
        this.objectType = subject.getClass().getName();
        for(Field fld : FieldUtils.getAllFields(subject.getClass())) {
            try {
                attributes.put(fld.getName(), FieldUtils.readField(fld, subject, true));
            } catch (IllegalAccessException ex) {}
        }
        }
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    
}
