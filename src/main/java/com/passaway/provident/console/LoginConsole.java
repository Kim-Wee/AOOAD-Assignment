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

import com.passaway.provident.Customer;
import com.passaway.provident.agent.Agent;

import java.util.*;



public class LoginConsole {
        
    private AgentConsole agent;
    private CustomerConsole customer;
    private Map<UUID, Agent> agents;
    private Map<UUID, Customer> customers;
    private Menu<Void> menu;
    
    
    public LoginConsole(AgentConsole agent, CustomerConsole customer, Map<UUID, Agent> agents, Map<UUID, Customer> customers) {
        this.agent = agent;
        this.customer = customer;
        this.agents = agents;
        this.customers = customers;
        menu = new Menu<>("Login");
        menu.register(1, "Login as agent", this::asAgent);
        menu.register(2, "Login as customer", this::asCustomer);
        menu.register(3, "Exit", v -> {});
    }
    
    
    public void login() {
        int index = 0;
        while (index != 3) {
            menu.view();
            index = menu.select(null);
        }
    }
    
    private void asAgent(Void v) {
        UUID id = Input.as("Enter your ID: ", "Invalid ID", text -> {
            try {
                return UUID.fromString(text);
                
            } catch (IllegalArgumentException e) {
                return null;
            }
        }, agents::containsKey);
        
        agent.menu(agents.get(id));
    }
    
    private void asCustomer(Void v) {
        UUID id = Input.as("Enter your ID: ", "Invalid ID", text -> {
            try {
                return UUID.fromString(text);
                
            } catch (IllegalArgumentException e) {
                return null;
            }
        }, customers::containsKey);
        
        customer.menu(customers.get(id));
    }
    
}
