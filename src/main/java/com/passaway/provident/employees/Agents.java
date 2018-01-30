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


import com.passaway.provident.Controller;
import com.passaway.provident.Input;
import com.passaway.provident.policy.*;

import java.util.*;

import org.apache.commons.lang3.tuple.Pair;



public class Agents {
    
    private Controller controller;
    private Map<UUID, Agent> agents;
    
    private Map<String, Pair<Pay, String>> information;
    
    
    public Agents(Controller controller, Map<UUID, Agent> agents) {
        this.controller = controller;
        this.agents = agents;
        
        information = new HashMap<>();
        information.put("junior", Pair.of(Pay.JUNIOR_AGENT, "Junior Agent information"));
        information.put("normal", Pair.of(Pay.AGENT, "Agent information"));
        information.put("senior", Pair.of(Pay.SENIOR_AGENT, "Senior Agent information"));
    }
    
    
    public void register() {
        String name = Input.get("Enter name: ");
        Pair<Pay, String> pair = Input.as("Enter the agent seniority (junior, normal, senior): ", "Invalid input", information::get);
     
        Agent agent = new Agent(name, pair.getLeft(), pair.getRight(), 0);
        agents.put(agent.getID(), agent);
        System.out.println("Created an agent: "+ agent.getID());
    }
    
    
    public void find() {
        UUID id = Input.as("Enter the Agent ID", "Invalid input", UUID::fromString);
        Agent agent = controller.getAgents().getAgents().get(id);
        if (agent != null) {
            view(agent);
            
        } else {
            System.out.println("Unable to find agent");
        }
    }
    
    public void view(Agent agent) {
        System.out.println("===== Agent information =====\n"
                + "Name: " + agent.getName() + "\n"
                + "ID: " + agent.getID() + "\n"
                + "Pay Information: " + agent.getInformation() + "\n"
                + "Policies sold: " + agent.getPolicies().size() + "\n"
                + "Total comission for month: " + agent.getCommission()
        );
    }
    
    public void remind(Agent agent) {
        List<Policy> policies = controller.getPolicies().view(agent, " with outstanding premiums", policy -> !policy.isPaid());
        
        if (policies.isEmpty()) {
            return;
        }
        
        Policy policy = policies.get(Input.as("Enter policy index: ", policies.size()) - 1);
        Input.match("Enter the option (send email, print letter, nothing", "Invalid input", value -> {
            switch (value.toLowerCase()) {
                case "send email":
                    agent.sendEmail(policy);
                    return true;
                    
                case "print letter":
                    agent.createReminderLetter(policy);
                    return true;
                    
                case "nothing":
                    return true;
                    
                default:
                    return false;
            }
        });
    }
    
        
    public Map<UUID, Agent> getAgents() {
        return agents;
    }
    
}
