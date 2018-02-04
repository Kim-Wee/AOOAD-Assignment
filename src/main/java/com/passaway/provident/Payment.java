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
package com.passaway.provident;

import com.passaway.provident.policy.Policy;

import java.time.LocalDateTime;
import java.util.UUID;


public abstract class Payment {    
    
    private UUID id;
    private LocalDateTime date;
    private Policy policy;
    private double amount;
    
    
    public Payment(Policy policy, double amount) {
        this.id = UUID.randomUUID();
        this.policy = policy;
        this.date = LocalDateTime.now();
        this.amount = amount;
    }

    
    public UUID getID() {
        return id;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public Policy getPolicy() {
        return policy;
    }
    
    public double getAmount() {
        return amount;
    }
    
}
