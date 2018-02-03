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
package com.passaway.provident.policy.statuses;

import com.passaway.provident.Payment;
import com.passaway.provident.policy.*;

import java.util.Optional;


public class Lapsed implements Status {
    
    public static final Lapsed INSTANCE = new Lapsed();
    
    
    private Lapsed() {}

    
    @Override
    public void charge(Policy policy) {
        policy.getCoverage().charge(policy);
    }
    
    @Override
    public void pay(Policy policy, Payment payment) {
        policy.setPremium(policy.getPremium() - payment.getAmount());
        if (policy.getPremium() <= 0) {
            policy.setStatus(Active.INSTANCE);
        }
    }

    @Override
    public Optional<Double> payout(Policy policy) {
        System.out.println("Policy canot be claimed while lapsed");
        return Optional.empty();
    }
    
    @Override
    public void lapse(Policy policy) {
        System.out.println("Policy is already lapsed");
    }
    
}
