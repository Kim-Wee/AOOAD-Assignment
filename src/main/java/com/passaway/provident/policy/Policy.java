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
package com.passaway.provident.policy;

import com.passaway.provident.client.Client;
import com.passaway.provident.employees.Agent;
import com.passaway.provident.policy.coverages.Coverage;
import com.passaway.provident.policy.states.*;

import java.util.*;


public class Policy {
    
    private UUID id;
    private Agent agent;
    private Client client;
    private PolicyType type;
    private Coverage coverage;
    private Status status;
    private List<Payment> payments;
    private double due;
    private boolean periodic;
    
    
    public Policy(Agent agent, Client client, PolicyType type, Coverage coverage, double due, boolean periodic) {
        this(UUID.randomUUID(), agent, client, type, coverage, new Active(), new ArrayList<>(), due, periodic);
    }
    
    public Policy(UUID id, Agent agent, Client client, PolicyType type, Coverage coverage, Status status, List<Payment> payments, double due, boolean periodic) {
        this.id = id;
        this.agent = agent;
        this.client = client;
        this.type = type;
        this.coverage = coverage;
        this.status = status;
        this.payments = payments;
        this.due = due;
        this.periodic = periodic;
    }
    
    
    public void pay(Payment payment) {
        status.pay(this, payment);
    }
    
        
    public void charge() {
        status.charge(this, coverage);
    }
    
    public Payout claim(String context) {
        return status.claim(this, coverage, context);
    }
        
    public void cancelledByAgent() {
        status.cancelledByAgent(this);
    }
    
    public void cancelledByClient() {
        status.cancelledByClient(this);
    }

    
    public UUID getID() {
        return id;
    }

    public Agent getAgent() {
        return agent;
    }

    public Client getClient() {
        return client;
    }

    public PolicyType getType() {
        return type;
    }

    public Coverage getCoverage() {
        return coverage;
    }
    
    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
        
    public boolean isPaid() {
        return due <= 0;
    }
    
    public List<Payment> getPayments() {
        return payments;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }
    
    public boolean isPeriodic() {
        return periodic;
    }
    
    
    public static Builder builder() {
        return new Builder(new Policy(null, null, null, null, 0, false));
    }
    
    public static class Builder {
        
        private Policy policy;
        
        
        private Builder(Policy policy) {
            this.policy = policy;
        }
        
        
        public Builder agent(Agent agent) {
            policy.agent = agent;
            return this;
        }
        
        public Builder client(Client client) {
            policy.client = client;
            return this;
        }
        
        public Builder type(PolicyType type) {
            policy.type = type;
            return this;
        }
        
        public Builder coverage(Coverage coverage) {
            policy.coverage = coverage;
            return this;
        }
        
        public Builder status(Status status) {
            policy.status = status;
            return this;
        }
        
        public Builder due(double due) {
            policy.due = due;
            return this;
        }
        
        public Builder periodic(boolean periodic) {
            policy.periodic = periodic;
            return this;
        }
        
        
        public Policy build() {
            return policy;
        }
        
    }
    
}
