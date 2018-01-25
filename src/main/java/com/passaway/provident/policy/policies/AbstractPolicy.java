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
package com.passaway.provident.policy.policies;

import com.passaway.provident.client.Client;
import com.passaway.provident.employees.Agent;
import com.passaway.provident.policy.*;
import com.passaway.provident.policy.status.Status;

import java.util.*;


public abstract class AbstractPolicy implements Policy {
    
    protected UUID id;
    protected Agent agent;
    protected Client client;
    protected List<Premium> premiums;
    protected Status status;
    
    
    public AbstractPolicy(UUID id, Agent agent, Client client, List<Premium> premiums, Status status) {
        this.id = id;
        this.agent = agent;
        this.client = client;
        this.premiums = premiums;
        this.status = status;
    }
    
    
    @Override
    public double claim(String context) {
        return status.claim(this, context);
    }
    
    public abstract double claimPolicy(String context);
    
    @Override
    public Premium calculate() {
        return status.calculate(this);
    }
    
    public abstract Premium calculatePremium();
        

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public Agent getAgent() {
        return agent;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public List<Premium> getPremiums() {
        return premiums;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
    
}
