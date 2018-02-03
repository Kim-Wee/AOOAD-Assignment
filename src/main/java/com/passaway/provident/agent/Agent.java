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
package com.passaway.provident.agent;

import com.passaway.provident.policy.Policy;

import java.util.*;

import static java.util.UUID.randomUUID;


public class Agent {
    
    private UUID id;
    private String name;
    private String information;
    private Map<UUID, Policy> policies;
    private Pay pay;
    private double commission;
    
    
    public Agent(String name, String information, Pay pay) {
        this(randomUUID(), name, information, new HashMap<>(), pay, 0);
    }
    
    public Agent(UUID id, String name, String information, Map<UUID, Policy> policies, Pay pay, double commission) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.policies = policies;
        this.pay = pay;
        this.commission = commission;
    }
    
    
    public void sendEmail(Policy policy) {
        String email = policy.getCustomer().getEmail();
        System.out.println("<Insert fancy send email code here>");
    }
    
    public void printLetter(Policy policy) {
        String address = policy.getCustomer().getAddress();
        System.out.println("<Insert fancy print letter code here>");
    }

    
    public UUID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return information;
    }

    public Map<UUID, Policy> getPolicies() {
        return policies;
    }

    public double getPay() {
        return pay.calculate(this);
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
    
}
