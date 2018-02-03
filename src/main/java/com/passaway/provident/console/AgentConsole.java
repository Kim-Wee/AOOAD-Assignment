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

import com.google.common.primitives.*;

import com.passaway.provident.*;
import com.passaway.provident.agent.Agent;
import com.passaway.provident.policy.*;
import com.passaway.provident.policy.coverages.*;

import java.util.*;



public class AgentConsole {
    
    private Map<UUID, Agent> agents;
    private Map<UUID, Customer> customers;
    private Menu<Agent> menu;
    
    
    public AgentConsole(Map<UUID, Agent> agents, Map<UUID, Customer> customers) {
        this.agents = agents;
        this.customers = customers;
        menu = new Menu<>("Agent");
        menu.register(1, "View policies", this::view);
        menu.register(2, "Create policy", this::createPolicy);
        menu.register(3, "Edit policy", this::editPolicy);
        menu.register(4, "Remind customer(s)", this::remind);
        menu.register(5, "Exit", agent -> {});
    }
    
    
    public void menu(Agent agent) {
        int index = 0;
        while (index != 5) {
            menu.view();
            index = menu.select(agent);
        }
    }
    
    private void view(Agent agent) {
        Display.view("", agent.getPolicies().values(), policy -> true);
    }
    
    private void createPolicy(Agent agent) {
        Policy.Builder builder = Policy.builder().agent(agent).customer(Input.as("Enter ID of customer", "Invalid ID", customers::get, customers::containsKey));
        
        Input.match("Enter the policy type (CAR, MEDICAL, TRAVEL)", "Invalid input", value -> {
            switch (value.toLowerCase()) {
                case "car":
                    builder.type(PolicyType.CAR).coverage(new CarCoverage()).termsAndConditions("Car terms").premiumInformation("Car premium").payoutInformation("car payout");
                    return true;
                    
                case "medical":
                    builder.type(PolicyType.MEDICAL).coverage(new MedicalCoverage()).termsAndConditions("Medical terms").premiumInformation("Medical premium").payoutInformation("Medical payout");
                    return true;
                
                case "travel":
                    builder.type(PolicyType.TRAVEL).coverage(new TravelCoverage()).termsAndConditions("Travel terms").premiumInformation("Travel premium").payoutInformation("Travel payout");
                    return true;
                    
                default:
                    return false;
            }
        });
        
        Input.match("===== Riders =====\n1. Additional rider", "Enter the index or 'done' to exit", value -> {
           if (value.equalsIgnoreCase("done")) {
               return true;
           }
           
           Integer index = Ints.tryParse(value);
           if (index != null && index > 0 && index <= 1) {
                builder.rider(AdditionalRider::new);
                System.out.println("Added rider");
           }
           
           return false;
        });
        
        System.out.println("<Insert fancy commission calculation here>");
        agent.setCommission(agent.getCommission() + 3);
 
        Policy policy = builder.build();
        
        policy.getAgent().getPolicies().put(policy.getID(), policy);
        policy.getCustomer().getPolicies().put(policy.getID(), policy);
    }
    
    private void editPolicy(Agent agent) {
        List<Policy> policies = Display.view("", agent.getPolicies().values(), policy -> true);
        while (true) {
            boolean add = Input.as("Add rider(s) to policies?", "Invalid input", value -> {
                switch (value.toLowerCase()) {
                    case "yes":
                        return true;
                        
                    case "no":
                        return false;
                    
                    default:
                        return null;
                }
            });
            
            if (add) {
                Policy policy = policies.get(Input.between("Enter index of policy", 0, policies.size()) - 1);
                
                Input.match("===== Riders =====\n1. Additional rider", "Enter the index or 'done' to exit", value -> {
                    if (value.equalsIgnoreCase("done")) {
                        return true;
                    }

                    Integer index = Ints.tryParse(value);
                    if (index != null && index > 0 && index <= 1) {
                        policy.setCoverage(policy.getCoverage());
                        System.out.println("Added rider");
                    }

                    return false;
                });
                
            } else {
                break;
            }
        }
        
        while (true) {
            boolean pay = Input.as("Pay policies with outstanding premiums?", "Invalid input", value -> {
                switch (value.toLowerCase()) {
                    case "yes":
                        return true;
                        
                    case "no":
                        return false;
                    
                    default:
                        return null;
                }
            });
            
            if (pay) {
                Policy policy = Input.as("Enter index of policy", "Invalid input", value -> {
                    Integer index = Ints.tryParse(value);
                    Policy p;
                    if (index != null && index > 0 && index <= policies.size() && (p = policies.get(index - 1)).getPremium() != 0) {
                        return p;

                    } else {
                        return null;
                    }
                });
                double amount = Input.as("Enter amount: ", "Invalid amount", Doubles::tryParse);
                
                policy.pay(new Payment(policy, PaymentType.CHEQUE, amount));
                System.out.println("Payment has been made");
                
            } else {
                break;
            }
        }
    }
    
    private void remind(Agent agent) {
        List<Policy> policies = Display.view("with outstanding premiums ", agent.getPolicies().values(), policy -> policy.getPremium() != 0);
        if (policies.isEmpty()) {
            return;
        }
        
        Policy policy = policies.get(Input.between("Enter policy index: ", 0, policies.size()) - 1);
        Input.match("Enter the option (send email, print letter, both, nothing)", "Invalid input", value -> {
            switch (value.toLowerCase()) {
                case "send email":
                    agent.sendEmail(policy);
                    return true;
                    
                case "print letter":
                    agent.printLetter(policy);
                    return true;
                    
                case "both":
                    agent.sendEmail(policy);
                    agent.printLetter(policy);
                    return true;
                    
                case "nothing":
                    return true;
                    
                default:
                    return false;
            }
        });
    }
    
}
