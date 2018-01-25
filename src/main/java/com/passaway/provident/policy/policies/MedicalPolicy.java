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


public class MedicalPolicy extends AbstractPolicy {

    public MedicalPolicy(UUID id, Agent agent, Client client, List<Premium> premiums, Status status) {
        super(id, agent, client, premiums, status);
    }

    
    @Override
    public double claimPolicy(String context) {
        System.out.println("Medical claim");
        return 0;
    }

    @Override
    public Premium calculatePremium() {
        System.out.println("Create medical premium");
        return new Premium(0);
    }

    @Override
    public PolicyType getType() {
        return PolicyType.MEDICAL;
    }
    
}
