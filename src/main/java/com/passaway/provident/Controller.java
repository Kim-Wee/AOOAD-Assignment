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
        while (true) {
            switch (Input.as("===== Menu =====\n1. Register agent\n2. Exit", 2)) {
                case 1:
                    agents.register();
                    break;
                    
                default:
                    return;
            }
        }
    }
    
    public void view(Agent agent) {
        while (true) {
            switch (Input.as("===== Menu =====\n1. Register agent\n2. Exit", 2)) {
                case 1:
                    agents.register();
                    break;
                    
                default:
                    return;
            }
        }
    }
    
    public void view(Client client) {
        
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
