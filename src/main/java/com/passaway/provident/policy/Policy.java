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

import com.passaway.provident.agent.Agent;
import com.passaway.provident.*;
import com.passaway.provident.policy.coverages.Coverage;
import com.passaway.provident.policy.statuses.*;

import java.util.*;
import java.util.function.Function;

import static java.util.UUID.randomUUID;


public class Policy {
    
    private UUID id;
    private Agent agent;
    private Customer customer;
    private PolicyType type;
    private Coverage coverage;
    private Status status;
    private Map<UUID, Payment> payments;
    private double premium;
    private boolean paidout;
    private String termsAndConditions;
    private String premiumInformation;
    private String payoutInformation;
    
    
    private Policy(UUID id, Agent agent, Customer customer, PolicyType type, Coverage coverage, String terms, String premium, String payout) {
        this.id = id;
        this.agent = agent;
        this.customer = customer;
        this.type = type;
        this.coverage = coverage;
        this.status = Active.INSTANCE;
        this.payments = new HashMap<>();
        this.premium = 0;
        this.paidout = false;
        this.termsAndConditions = terms;
        this.premiumInformation = premium;
        this.payoutInformation = payout;
    }
    
    
    public void charge() {
        status.charge(this);
    }
    
    public void pay(Payment payment) {
        status.pay(this, payment);
    }
    
    public Optional<Double> payout() {
        return status.payout(this);
    }
    
    public void lapse() {
        status.lapse(this);
    }
        
    public void terminate() {
        status.terminate(this);
    }

    
    public UUID getID() {
        return id;
    }

    public Agent getAgent() {
        return agent;
    }

    public Customer getCustomer() {
        return customer;
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

    public Map<UUID, Payment> getPayments() {
        return payments;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public boolean isPaidout() {
        return paidout;
    }

    public void setPaidout(boolean paidout) {
        this.paidout = paidout;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public String getPremiumInformation() {
        return premiumInformation;
    }

    public String getPayoutInformation() {
        return payoutInformation;
    }

    
    public static Builder builder() {
        return new Builder(new Policy(randomUUID(), null, null, null, null, "", "", ""));
    }
    
    public static class Builder {
        
        private Policy policy;
        
        
        private Builder(Policy policy) {
            this.policy = policy;
        }
        
        
        public Builder id(UUID id) {
            policy.id = id;
            return this;
        }
        
        public Builder agent(Agent agent) {
            policy.agent = agent;
            return this;
        }
        
        public Builder customer(Customer customer) {
            policy.customer = customer;
            return this;
        }
        
        public Builder type(PolicyType type) {
            policy.type = type;
            return this;
        }
        
        public Builder coverage(Coverage coverage) {
            policy.setCoverage(coverage);
            return this;
        }
        
        public Builder rider(Function<Coverage, Coverage> rider) {
            policy.setCoverage(rider.apply(policy.getCoverage()));
            return this;
        }
        
        public Builder termsAndConditions(String terms) {
            policy.termsAndConditions = terms;
            return this;
        }
        
        public Builder premiumInformation(String info) {
            policy.premiumInformation = info;
            return this;
        }
        
        public Builder payoutInformation(String info) {
            policy.payoutInformation = info;
            return this;
        } 
        
        
        public Policy build() {
            return policy;
        }
        
    }
    
}
