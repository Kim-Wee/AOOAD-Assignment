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

import com.passaway.provident.client.Client;
import com.passaway.provident.client.Clients;
import com.passaway.provident.employees.*;
import com.passaway.provident.policy.Policies;


public class Controller {
    
    private Agents agents;
    private Clients clients;
    private Policies policies;
    
    
    public Controller(Agents agents, Clients clients, Policies policies) {
        this.agents = agents;
        this.clients = clients;
        this.policies = policies;
    }
    
    
    public void view() {
        String message = "===== Menu ====="
                + "\n1. Register agent"
                + "\n2. Find agent"
                + "\n3. Exit";
        
        while (true) {
            switch (Input.as(message, 3)) {
                case 1:
                    agents.register();
                    break;
                    
                case 2:
                    agents.find();
                    break;
                    
                default:
                    return;
            }
        }
    }
    
    public void view(Agent agent) {
        String message = "===== Menu ====="
                + "\n1. Register customer"
                + "\n2. Create new policy"
                + "\n3. View policies"
                + "\n4. Customer(s) information"
                + "\n5. Find policy"
                + "\n6. Find payment"
                + "\n7. Generate alerts"
                + "\n8. Pay premiums"
                + "\n9. Exit";
        
        while (true) {
            switch (Input.as(message, 9)) {
                case 1:
                    clients.register();
                    break;
                    
                case 2:
                    policies.createPolicy(agent);
                    break;
                    
                case 3:
                    policies.view(agent, message, policy -> true);
                    break;
                    
                case 4:
                    agent.getPolicies().values().forEach(policy -> clients.view(policy.getClient()));
                    break;
                    
                case 5:
                    policies.findPolicy();
                    break;
                    
                case 6:
                    policies.findPayment();
                    break;
                    
                case 7:
                    agents.remind(agent);
                    break;
                    
                case 8:
                    policies.editPolicy(agent);
                    break;
                    
                default:
                    return;
            }
        }
    }
    
    public void view(Client client) {
        String message = "===== Menu ====="
                + "\n1. View policies"
                + "\n2. View all policy information"
                + "\n3. Find policy"
                + "\n4. Find payment"
                + "\n5. Pay premiums"
                + "\n6. Exit";
        
        while (true) {
            switch (Input.as(message, 9)) {
                case 1:
                    policies.view(client, "", policy -> true);
                    break;
                    
                case 2:
                    policies.view();
                    break;
                    
                case 3:
                    policies.findPolicy();
                    break;
                    
                case 4:
                    policies.findPayment();
                    break;
                    
                case 5:
                    clients.payOustandingPremiums(client);
                    break;
                    
                default:
                    return;
            }
        }
    }

    
    public Agents getAgents() {
        return agents;
    }

    public void setAgents(Agents agents) {
        this.agents = agents;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Policies getPolicies() {
        return policies;
    }

    public void setPolicies(Policies policies) {
        this.policies = policies;
    }
    
}
