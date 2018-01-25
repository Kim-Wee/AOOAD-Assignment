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
package com.passaway.provident.employees;

import com.passaway.provident.policy.Policy;

import java.util.*;


public class Agent {
    
    private String name;
    private String information;
    private List<Policy> policies;
    private Pay pay;
    private double commission;
    
    
    public Agent(String name, String information, Pay pay, double commission) {
        this(name, information, new ArrayList<>(), pay, commission);
    }
    
    public Agent(String name, String information, List<Policy> policies, Pay pay, double commission) {
        this.name = name;
        this.information = information;
        this.policies = policies;
        this.pay = pay;
        this.commission = commission;
    }
    
    
    public void sendEmail(Policy policy) {
        String email = policy.getClient().getEmail();
        System.out.println("Send an email");
    }
    
    public void createReminderLetter(Policy policy) {
        String address = policy.getClient().getAddress();
        System.out.println("Print reminder letter");
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
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
