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

import com.lamontd.utils.transport.MappedTransportObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for object converters for transport objects that will be used to populate objects. 
 * <p>
 * In order to define a new converter from the TransportObject to an external types, extend this
 * class for the type T that will be a Neo4J entity (not enforced) and register the class as a @Service. 
 * The appropriate handlers will autowire things for you beyond that.
 * 
 * @author lamontdozierjr
 */
public abstract class MappedTransportObjectConverter<T> {

    private List<String> supportedTransportClasses = new ArrayList<>();

    protected MappedTransportObjectConverter(String expectedTransportClass) {
        this.supportedTransportClasses.add(expectedTransportClass);
    }

    protected void addSupportedTransportClass(String expectedTransportClass) {
        this.supportedTransportClasses.add(expectedTransportClass);
    }

    public boolean canHandle(MappedTransportObject transportObject) {
        return supportedTransportClasses.contains(transportObject.getObjectType())
                && transportObject.getAttributes() != null
                && !transportObject.getAttributes().isEmpty();
    }

    public void convertAndStore(MappedTransportObject transportObject) {
        T conversionObject = convert(transportObject);
        if (conversionObject != null) {
            store(conversionObject);
        }
    }

    protected abstract T convert(MappedTransportObject transportObject);

    protected abstract void store(T object);

    public List<String> getSupportedTransportClasses() {
        return supportedTransportClasses;
    }

    public static LocalDate convertToLocalDate(Object object) {
        if (object instanceof String) {
            return LocalDate.parse((String) object);
        } else if (object instanceof LocalDate) {
            return (LocalDate) object;
        }
        return null;
    }

    public static boolean convertToBoolean(Object object) {
        if (object instanceof String) {
            return Boolean.valueOf((String) object);
        } else if (object instanceof Boolean) {
            return (Boolean) object;
        }
        return false;
    }

}
