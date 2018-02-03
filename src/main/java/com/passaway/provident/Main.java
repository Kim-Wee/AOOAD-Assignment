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

import com.passaway.provident.agent.*;
import com.passaway.provident.console.*;

import java.util.*;


public class Main {
    
    private static Map<UUID, Agent> agents = new HashMap<>();
    private static Map<UUID, Customer> customers = new HashMap<>();
    
    
    public static void main(String[] args) {        
        register(new Agent("Francis Kok", "Senior agent", Pay.SENIOR));
        register(new Agent("Bob", "Standard agent", Pay.STANDARD));
        register(new Agent("Lim Ko Pi", "Junior agent", Pay.JUNIOR));
        
        register(new Customer("John Doe", "Over the rainbow", "doe@gmail.com"));
        register(new Customer("Douglas Adams", "The galaxy", "douglas@gmail.com"));
        register(new Customer("ED iot", "Idk", "idk@gmail.com"));
        
        new LoginConsole(new AgentConsole(agents, customers), new CustomerConsole(agents, customers), agents, customers).login();
    }
    
    
    public static void register(Agent agent) {
        agents.put(agent.getID(), agent);
        System.out.println("Generated " + agent.getInformation() + " with ID: " + agent.getID() + "\n");
    }
    
    public static void register(Customer customer) {
        customers.put(customer.getID(), customer);
        System.out.println("Generated " + customer.getName() + " with ID " + customer.getID() + "\n");
    }
    
}
