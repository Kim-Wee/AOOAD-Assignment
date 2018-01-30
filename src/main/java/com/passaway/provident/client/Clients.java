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

import com.google.common.primitives.Doubles;
import com.passaway.provident.Controller;
import com.passaway.provident.Input;
import com.passaway.provident.policy.*;

import java.util.*;


public class Clients {
    
    private Controller controller;
    private Map<UUID, Client> clients;
    
    
    public Clients(Controller controller, Map<UUID, Client> clients) {
        this.controller = controller;
        this.clients = clients;
    }
    
    
    public void register() {
        Client client = new Client(Input.get("Enter name: "), Input.get("Enter email: "), Input.get("Enter address: "));
        clients.put(client.getID(), client);
        System.out.println("Created a client: " + client.getID());
    }
    
    
    public void view(Client client) {
        System.out.println("===== Client information =====\n"
                + "Name: " + client.getName() + "\n"
                + "ID: " + client.getID() + "\n"
                + "Email: " + client.getEmail() + "\n"
                + "Address: " + client.getAddress() + "\n"
        );
    }
    
    
    public void payOustandingPremiums(Client client) {
        List<Policy> policies = controller.getPolicies().view(client, "with outstanding premiums ", policy -> !policy.isPaid());
        Policy policy = policies.get(Input.as("Enter the index of the policy", policies.size()) - 1);
        Input.match("Enter your credit card number", "Input can only contain numbers", value -> value.matches("\\d+"));
        double amount = Input.as("Enter amount: ", "Invalid amount", Doubles::tryParse);
        
        policy.pay(new Payment(policy, PaymentType.CREDIT_CARD, amount));
    }

    
    public Map<UUID, Client> getClients() {
        return clients;
    }
    
}
