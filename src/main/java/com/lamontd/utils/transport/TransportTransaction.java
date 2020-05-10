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

import java.time.Instant;

/**
 *
 * @author lamontdozierjr
 */
public class TransportTransaction {

    public enum Type {
        START, ACK, NAK
    }

    private String id;
    private String component;
    private Instant time;
    private Type type;

    public TransportTransaction() {
    }

    public TransportTransaction(String transactionId, String component, Instant time, Type type) {
        this.id = transactionId;
        this.component = component;
        this.time = time;
        this.type = type;
    }

    public static TransportTransaction start(String transactionId, String component) {
        return new TransportTransaction(transactionId, component, Instant.now(), Type.START);
    }

    public static TransportTransaction ack(String transactionId, String component) {
        return new TransportTransaction(transactionId, component, Instant.now(), Type.ACK);
    }

    public static TransportTransaction nak(String transactionId, String component) {
        return new TransportTransaction(transactionId, component, Instant.now(), Type.NAK);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    
}
