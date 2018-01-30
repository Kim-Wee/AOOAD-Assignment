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
package com.passaway.provident.policy.status;

import com.passaway.provident.policy.*;
import com.passaway.provident.policy.coverages.Coverage;

import java.util.Optional;


public class Terminated extends Status {
    
    public static final Terminated AGENT = new Terminated("Policy has been terminated by the agent");
    public static final Terminated CLIENT = new Terminated("Policy has been terminated by the client");
    public static final Terminated PAID_OUT = new Terminated("Policy has been paid out");
    
    
    public Terminated(String information) {
        super(information);
    }
    
    
    @Override
    public void pay(Policy policy, Payment payment) {
        System.out.println("Policy has already been terminated");
    }

    @Override
    public Optional<Payout> claim(Policy policy, Coverage coverage, String context) {
        System.out.println("Policy has already been terminated");
        return Optional.empty();
    }

    @Override
    public void charge(Policy policy, Coverage coverage) {
        System.out.println("Policy has already been terminated");
    }

    @Override
    public void cancelledByAgent(Policy policy) {
        System.out.println("Policy has already been terminated");
    }
    
    @Override
    public void cancelledByClient(Policy policy) {
        System.out.println("Policy has already been terminated");
    }
    
}
