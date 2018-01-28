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
package com.passaway.provident.employees;

import com.google.common.primitives.Ints;

import com.passaway.provident.Controller;
import com.passaway.provident.client.Client;
import com.passaway.provident.policy.*;
import com.passaway.provident.policy.coverages.*;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;


public class Agents {
    
    private Controller controller;
    private Map<UUID, Agent> agents;
    
    
    public Agents(Controller controller, Map<UUID, Agent> agents) {
        this.controller = controller;
        this.agents = agents;
    }
    
    
    public void registerAgent() {
        System.out.println("Enter the name: ");
        String name = System.console().readLine();
        
        System.out.println("Enter the seniority (junior, normal, senior): ");
        String information;
        Pay pay;
        while (true) {
            String input = System.console().readLine();
            
            if (input.equalsIgnoreCase("junior")) {
                information = "Junior Agent information";
                pay = Pay.JUNIOR_AGENT;
                break;
                
            } else if (input.equalsIgnoreCase("normal")) {
                information = "Agent information";
                pay = Pay.AGENT;
                break;
                
            } else if (input.equalsIgnoreCase("senior")) {
                information = "Senior Agent information";
                pay = Pay.SENIOR_AGENT;
                break;
                
            } else {
                System.out.println("Invalid input, must be either junior, normal or senior");
            }
        }
        
        Agent agent = new Agent(name, information, pay, 0);
        agents.put(agent.getID(), agent);
    }
    
    
    public void createPolicy(Agent agent) {
        System.out.println("Enter the ID of the customer: ");
        Client client;
        while (true) {
            String input = System.console().readLine();
            if ((client = controller.getClients().getClients().get(UUID.fromString(input))) == null) {
                System.out.println("Invalid customer ID");
                
            } else {
                break;
            }
        }
                
        Policy.Builder builder = Policy.builder().agent(agent).client(client);
        
        System.out.println("Enter the policy type (CAR, MEDICAL, TRAVEL): ");
        while (true) {
            String input = System.console().readLine();
            
            if (input.equalsIgnoreCase("car")) {
                builder.type(PolicyType.CAR).coverage(new CarCoverage());
                break;
                
            } else if (input.equalsIgnoreCase("medical")) {
                builder.type(PolicyType.MEDICAL).coverage(new MedicalCoverage());
                break;
                
            } else if (input.equalsIgnoreCase("travel")) {
                builder.type(PolicyType.TRAVEL).coverage(new TravelCoverage());
                break;
                
            } else {
                System.out.println("Invalid input, must be either CAR, MEDICAL or TRAVEL");
            }
        }
        
        while (true) {
            
            System.out.println("===== Available riders =====");
            System.out.println("1. Additional Rider");
        }
        
    }
    
    
    public void viewAgent(Agent agent) {
        System.out.println("===== Agent information =====\n" + 
                           "Name: " + agent.getName() + "\n" +
                           "ID: " + agent.getID() + "\n" +
                           "Pay Information: " + agent.getInformation() + "\n" +
                           "Policies sold: " + agent.getPolicies().size() + "\n" +
                           "Total comission for month: " + agent.getCommission()
                        );
    }
    
    
    public void remindClient(Agent agent) {
        List<Policy> policies = viewPolicies(agent, policy -> !policy.isPaid());
        if (!policies.isEmpty()) {
            Integer index;
            while (true) {
                if ((index = Ints.tryParse(System.console().readLine())) == null || index < 1 || index > policies.size()) {
                    System.out.println("Invalid index, must be integer between 1 and " + policies.size());

                } else {
                    break;
                }
            }
            
            Policy policy = policies.get(index - 1);
            System.out.println("Enter the option (send email, print letter, nothing)");
            while (true) {
                String input = System.console().readLine();

                if (input.equalsIgnoreCase("send email")) {
                    agent.sendEmail(policy);
                    break;

                } else if (input.equalsIgnoreCase("print letter")) {
                    agent.createReminderLetter(policy);
                    break;

                } else if (input.equalsIgnoreCase("nothing")) {
 
                    break;

                } else {
                    System.out.println("Invalid input, must be either send email, print letter, or nothing");
                }
            }
        }
    }
    
    public List<Policy> viewPolicies(Agent agent, Predicate<Policy> predicate) {
        System.out.println("===== Policies =====");
        List<Policy> policies = agent.getPolicies().stream().filter(predicate).collect(toList());
        for (int i = 0; i < policies.size(); i++) {
            Policy policy = policies.get(i);
            System.out.println(i + 1 + ". " + policy.getID() + " client ID: " + policy.getClient() + " Premium due: " + !policy.isPaid() + "\n");
        }
        
        return policies;
    }
    
        
    public Map<UUID, Agent> getAgents() {
        return agents;
    }
    
}
