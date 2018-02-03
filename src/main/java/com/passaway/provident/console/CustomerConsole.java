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

import com.google.common.primitives.Doubles;

import com.passaway.provident.*;
import com.passaway.provident.agent.Agent;
import com.passaway.provident.policy.Policy;

import java.util.*;



public class CustomerConsole {
    
    private Map<UUID, Agent> agents;
    private Map<UUID, Customer> customers;
    private Menu<Customer> menu;
    
    
    public CustomerConsole(Map<UUID, Agent> agents, Map<UUID, Customer> customers) {
        this.agents = agents;
        this.customers = customers;
        menu = new Menu<>("Customer");
        menu.register(1, "View policies", this::view);
        menu.register(2, "Pay outstanding policies", this::pay);
        menu.register(3, "Exit", agent -> {});
    }
    
    
    public void menu(Customer customer) {
        int index = 0;
        while (index != 3) {
            menu.view();
            index = menu.select(customer);
        }
    }
    
    private void view(Customer customer) {
        Display.view("", customer.getPolicies().values(), policy -> true);
    }
    
    private void pay(Customer customer) {
        List<Policy> policies = Display.view("with oustanding premiums", customer.getPolicies().values(), policy -> policy.getPremium() != 0);
        Policy policy = policies.get(Input.between("Enter the index of the policy: ", 0, policies.size()) - 1);
        
        Input.match("Enter your credit card number: ", "Input can only contain numbers", value -> value.matches("\\d+"));

        policy.pay(new Payment(policy, PaymentType.CREDIT_CARD, Input.as("Enter amount: ", "Invalid amount", Doubles::tryParse)));
    }
    
}
