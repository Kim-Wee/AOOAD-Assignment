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

import java.util.*;

import static java.util.UUID.randomUUID;


public class Customer {
    
    private UUID id;
    private String name;
    private String address;
    private String email;
    private Map<UUID, Policy> policies;
    
    
    public Customer(String name, String address, String email) {
        this(randomUUID(), name, address, email, new HashMap<>());
    }
    
    public Customer(UUID id, String name, String address, String email, Map<UUID, Policy> policies) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.policies = policies;
    }
    
    
    public UUID getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Map<UUID, Policy> getPolicies() {
        return policies;
    }
    
}
