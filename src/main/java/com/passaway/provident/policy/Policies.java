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

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

import com.passaway.provident.*;
import com.passaway.provident.employees.Agent;
import com.passaway.provident.policy.Policy.Builder;
import com.passaway.provident.policy.coverages.*;

import java.util.*;
import java.util.function.Predicate;

import static com.passaway.provident.policy.PolicyType.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;


public class Policies {
    
    private Controller controller;
    private Map<Policy, List<String>> policies;
    
    
    public Policies(Controller controller) {
        this.controller = controller;
        policies = new HashMap<>();
        policies.put(Policy.builder().coverage(new CarCoverage()).type(CAR).termsAndConditions("Car terms").build(), asList("Additional rider"));
        policies.put(Policy.builder().coverage(new MedicalCoverage()).type(CAR).termsAndConditions("Medical terms").build(), asList("Additional rider"));
        policies.put(Policy.builder().coverage(new TravelCoverage()).type(CAR).termsAndConditions("Travel terms").build(), asList("Additional rider"));
    }
    
    
    public void findPolicy() {
        UUID id = Input.as("Enter the Policy ID", "Invalid input", UUID::fromString);
        
        for (Agent agent : controller.getAgents().getAgents().values()) {
            Policy policy = agent.getPolicies().get(id);
            if (policy != null) {
                System.out.println("===== Policyt ====="
                            + "\nID: " + policy.getID()
                            + "\nStatus: " + policy.getStatus().getInformation()
                            + "\nPremium information: " + policy.getCoverage().getPremiumInformation()
                            + "\nPayout information: " + policy.getCoverage().getPayoutInformation()
                            + "\nTerms and Conditions: " + policy.getTermsAndConditions()
                    );
                    return;
            }
        }
    }
    
    public void findPayment() {
        UUID id = Input.as("Enter the Receipt ID", "Invalid input", UUID::fromString);
        
        for (Agent agent : controller.getAgents().getAgents().values()) {
            for (Policy policy : agent.getPolicies().values()) {
                Payment payment = policy.getPayments().get(id);
                
                if (payment != null) {
                    System.out.println("===== Payment ====="
                            + "\nID: " + payment.getID()
                            + "\nDate: " + payment.getDate()
                            + "\nPayment type: " + payment.getType().toString()
                            + "\nAmount: " + payment.getAmount()
                    );
                    return;
                }
            }
        }
    }
    
    
    public void view() {
        System.out.println("====== Policies =====");
        policies.forEach(
            (policy, riders)-> System.out.println("Policy type: " + policy.getType() 
                    + " Premium: " + policy.getCoverage().getPremiumInformation()
                    + " Payout: " + policy.getCoverage().getPayoutInformation()
                    + " Terms and conditions: " + policy.getTermsAndConditions()
                    + " Riders: " + riders.toString()
            )
        );
    }
    
    public List<Policy> view(PolicyHolder holder, String header, Predicate<Policy> filter) {
        List<Policy> policies = holder.getPolicies().values().stream().filter(filter).collect(toList());
        
        System.out.println("===== Policies " + header + "=====");
        for (int i = 0; i < policies.size(); i++) {
            Policy policy = policies.get(i);
            System.out.println("Policy ID: " + policy.getID() + " Type: " + policy.getType() + " Client ID: " + policy.getClient().getID() + " Oustanding premium: " + policy.getPremium());
        }
        
        return policies;
    }
    
    
    public void createPolicy(Agent agent) {                
        Builder builder = Policy.builder().agent(agent).client(Input.as("Enter ID of customer", "Invalid ID", controller.getClients().getClients()::get));
        Input.match("Enter the policy type (CAR, MEDICAL, TRAVEL)", "Invalid input", value -> {
            switch (value.toLowerCase()) {
                case "car":
                    builder.type(PolicyType.CAR).coverage(new CarCoverage()).termsAndConditions("Car terms");
                    return true;
                    
                case "medical":
                    builder.type(PolicyType.MEDICAL).coverage(new MedicalCoverage()).termsAndConditions("Medical terms");
                    return true;
                
                case "travel":
                    builder.type(PolicyType.TRAVEL).coverage(new TravelCoverage()).termsAndConditions("Travel terms");
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
        
        Policy policy = builder.build();
        System.out.println("<Insert fancy commission calculation here>");
        
        agent.setCommission(agent.getCommission() + 3);
        
        policy.getAgent().getPolicies().put(policy.getID(), policy);
        policy.getClient().getPolicies().put(policy.getID(), policy);
    }
    
    
    public void editPolicy(Agent agent) {
        List<Policy> policies = view(agent, "Policies", policy -> true);
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
                Policy policy = policies.get(Input.as("Enter index of policy", policies.size()) - 1);
                
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
                    if (index != null && index > 0 && index <= policies.size() && !(p = policies.get(index - 1)).isPaid()) {
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
    
}
