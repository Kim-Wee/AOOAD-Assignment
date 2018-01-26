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
package com.passaway.provident.policy.states;

import com.passaway.provident.policy.*;
import com.passaway.provident.policy.coverages.Coverage;


public class Active extends Status {
    
    public Active() {
        this("This policy is current active");
    }
    
    public Active(String information) {
        super(information);
    }
    
    
    @Override
    public void pay(Policy policy, Payment payment) {
        policy.setPremium(policy.getPremium() - payment.getAmount());
        policy.getPayments().add(payment);
    }

    @Override
    public Payout claim(Policy policy, Coverage coverage, String context) {
        Payout payout = coverage.claim(policy, context);
        if (payout.isCompletelyPaidOut()) {
            policy.setStatus(Terminated.PAID_OUT);
        }
        
        return payout;
    }

    @Override
    public void charge(Policy policy, Coverage coverage) {
        if (policy.getPremium() > 0) {
            policy.setStatus(new Lapsed());
        }
       coverage.charge(policy);
    }
    
}
