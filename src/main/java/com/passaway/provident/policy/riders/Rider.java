/*
 * The MIT License
 *
 * Copyright 2018 Karus Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.passaway.provident.policy.riders;

import com.passaway.provident.client.Client;
import com.passaway.provident.employees.Agent;
import com.passaway.provident.policy.*;
import com.passaway.provident.policy.status.Status;

import java.util.*;


public abstract class Rider implements Policy {
    
    protected Policy policy;

    
    public Rider(Policy policy) {
        this.policy = policy;
    }


    @Override
    public double claim(String context) {
        return policy.claim(context);
    }

    @Override
    public Premium calculate() {
        return policy.calculate();
    }

    @Override
    public UUID getID() {
        return policy.getID();
    }

    @Override
    public Agent getAgent() {
        return policy.getAgent();
    }

    @Override
    public Client getClient() {
        return policy.getClient();
    }
    
    @Override
    public PolicyType getType() {
        return policy.getType();
    }
    
    @Override
    public List<Premium> getPremiums() {
        return policy.getPremiums();
    }

    @Override
    public Status getStatus() {
        return policy.getStatus();
    }

    @Override
    public void setStatus(Status status) {
        policy.setStatus(status);
    }
    
}
