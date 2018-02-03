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
package com.passaway.provident.console;

import com.passaway.provident.policy.Policy;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;


public class Display {    
    
    public static List<Policy> view(String header, Collection<Policy> collection, Predicate<Policy> filter) {
        List<Policy> policies = collection.stream().filter(filter).collect(toList());
        
        System.out.println("===== Policies " + header + "=====");
        for (int i = 0; i < policies.size(); i++) {
            Policy policy = policies.get(i);
            System.out.println("Policy ID: " + policy.getID() + " Type: " + policy.getType() + " Client ID: " + policy.getCustomer().getID() + " Oustanding premium: " + policy.getPremium());
        }
        
        return policies;
    }
    
}
