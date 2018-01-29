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
package com.passaway.provident.policy.coverages;

import com.passaway.provident.policy.*;

import java.util.Optional;


public class CarCoverage extends AbstractCoverage {

    public CarCoverage() {
        super("Car premium information", "Car payout information");
    }
    
    
    @Override
    public Optional<Payout> claim(Policy policy, String context) {
        System.out.println("<Insert fancy car coverage claim calculation here>");
        return Optional.of(new Payout(20, false));
    }

    @Override
    public void charge(Policy policy) {
        System.out.println("<Insert fancy car coverage claim calculation here>");
        policy.setPremium(policy.getPremium() + 20);
    }

    @Override
    public boolean isPeriodic() {
        return true;
    }
    
}
