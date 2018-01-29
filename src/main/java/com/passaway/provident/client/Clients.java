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
package com.passaway.provident.client;

import com.google.common.primitives.Ints;
import com.passaway.provident.Controller;
import com.passaway.provident.policy.*;

import java.util.*;

import static java.util.stream.Collectors.toList;


public class Clients {
    
    private Controller controller;
    private Map<UUID, Client> clients;
    
    
    public Clients(Controller controller, Map<UUID, Client> clients) {
        this.controller = controller;
        this.clients = clients;
    }
    
    
    public void registerClient() {
        System.out.println("Enter the name: ");
        String name = System.console().readLine();
        
        System.out.println("Enter the email: ");
        String email = System.console().readLine();
        
        System.out.println("Enter the address");
        String address = System.console().readLine();
        
        Client client = new Client(name, email, address);
        clients.put(client.getID(), client);
    }
    
    
    public void viewAccount(Client client) {
        System.out.println("===== Client information =====\n" + 
                           "Name: " + client.getName() + "\n" +
                           "ID: " + client.getID() + "\n" +
                           "Email: " + client.getEmail() + "\n" +
                           "Address: " + client.getAddress() + "\n"
                        );
    }
    
    
    public void viewOutstandingPolicies(Client client) {
        System.out.println("===== Policies with outstanding premiums =====");
        
        List<Policy> policies = client.getPolicies().stream().filter(policy -> !policy.isPaid()).collect(toList());
        if (policies.isEmpty()) {
            System.out.println("No outstanding policies");
            return;
        }
        
        for (int i = 0; i < policies.size(); i++) {
            Policy policy = policies.get(i);
            System.out.println(i + 1 + ". Policy ID: " + policy.getID() + " outstanding amount: " + policy.getPremium());
        }
        
        System.out.println("Would you like to pay the outstanding premium?");
        while (true) {
            if (System.console().readLine().equalsIgnoreCase("yes")) {
                payOutstandingPolicy(policies);
                break;
                
            } else if (System.console().readLine().equalsIgnoreCase("no")) {
                break;
                
            } else {
                System.out.println("Invalid choice (must be either yes/no)");
            }
        }
    }
    
    private void payOutstandingPolicy(List<Policy> policies) {
        System.out.println("Enter the policy index");
        Integer index;
        while (true) {
            if ((index = Ints.tryParse(System.console().readLine())) == null || index < 1 || index > policies.size()) {
                System.out.println("Invalid index, must be integer between 1 and " + policies.size());
                
            } else {
                break;
            }
        }
        
        System.out.println("Enter your credit card number");
        while (true) {
            if (!System.console().readLine().matches("\\d+")) {
                System.out.println("Credit card number can only contain numbers");
                
            } else {
                Policy policy = policies.get(index - 1);
                policy.pay(new Payment(policy, PaymentType.CREDIT_CARD, policy.getPremium()));
                System.out.println("Payment added.");
                break;
            }
        }
    }

    
    public Map<UUID, Client> getClients() {
        return clients;
    }
    
}
