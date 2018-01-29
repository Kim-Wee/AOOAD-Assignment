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

import java.util.*;

import static java.util.Arrays.asList;


public class Main {
    
    private static Controller controller;
    private static Set<String> roles;
    
    
    public static void main(String... args) {
        initialise();
        while (true) {
            String role = Input.match("Enter login type: ", "Invalid login type", value -> roles.contains(value.toLowerCase()));
            Input.match("Enter ID: ", "No such person with ID found", value -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        controller.view();
                        return true;

                    case "agent":
                        Agent agent = controller.getAgents().getAgents().get(UUID.fromString(value));
                        if (agent != null) {
                            controller.view(agent);
                            return true;

                        } else {
                            return false;
                        }

                    case "client":
                        Client client = controller.getClients().getClients().get(UUID.fromString(value));
                        if (client != null) {
                            controller.view(client);
                            return true;

                        } else {
                            return false;
                        }

                    case "exit":
                        return true;

                    default:
                        return false;
                }
            });
        }
    }
    
    
    private static void initialise() {
        controller = new Controller(null, null, null);
        controller.setAgents(new Agents(controller, new HashMap<>()));
        controller.setClients(new Clients(controller, new HashMap<>()));
        controller.setPolicies(new Policies(controller));
        roles = new HashSet<>(asList("admin", "agent", "client", "exit"));
    }
    
}
