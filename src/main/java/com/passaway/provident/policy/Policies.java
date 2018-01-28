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

import java.util.*;

import static com.passaway.provident.policy.PolicyType.*;
import static java.util.Arrays.asList;


public class Policies {
    
    private Map<PolicyType, PolicyInformation> policies;
    
    
    public Policies() {
        policies = new EnumMap<>(PolicyType.class);
        policies.put(CAR, new PolicyInformation("Car conditions", "Car premium", "Car payout", asList("Additional rider")));
        policies.put(MEDICAL, new PolicyInformation("Medical conditions", "Car premium", "Car payout", asList("Additional rider")));
        policies.put(TRAVEL, new PolicyInformation("Travel information", "Travel premium", "Travel payout", asList("Additional rider")));
    }
    
    
    public void viewAvailablePolicies() {
        System.out.println("===== Policies =====");
        policies.forEach((type, info) -> 
            System.out.println("Type: " + type.toString() + 
                               "\nTerms and conditions: " + info.getTermsAndConditions() + 
                               "\nPremium information: " + info.getPremiumInformation() +
                               "\nAvailable riders: " + info.getRiders().toString() + "\n"
            )
        );
    }
    
    
    public Map<PolicyType, PolicyInformation> getPolicies() {
        return policies;
    }
    
}
