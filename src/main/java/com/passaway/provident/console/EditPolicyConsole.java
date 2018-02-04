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
import com.passaway.provident.policy.Policy;
import com.passaway.provident.policy.coverages.AdditionalRider;


public class EditPolicyConsole {
    
    private Menu<Policy> menu;
    
    
    public EditPolicyConsole() {
        menu = new Menu<>("Edit Policy");
        menu.register(1, "Edit status", this::editStatus);
        menu.register(2, "Set current premium", this::editPremium);
        menu.register(3, "Add rider", this::addRider);
        menu.register(4, "Pay premium", this::pay);
        menu.register(5, "Exit", policy -> {});
    }
    
    
    public void menu(Policy policy) {
        int index = 0;
        while (index != 5) {
            menu.view();
            index = menu.select(policy);
        }
    }
    
    
    private void editStatus(Policy policy) {
        Input.as("Enter status (lapsed, terminated): ", "Invalid input, must be (lapsed, terminated)", status -> {
            switch (status.toLowerCase()) {
                case "lapsed":
                    policy.lapse();
                    return true;
                    
                case "terminated":
                    policy.terminate();
                    return true;
                    
                default:
                    return null;
            }
        });
    }
    
    private void editPremium(Policy policy) {
        policy.setPremium(Input.as("Enter current premium: ", "Invalid input, must be a positive double", value -> {
            Double amount = Doubles.tryParse(value);
            if (amount != null && amount >= 0) {
                return amount;
                
            } else {
                return null;
            }
        }));
    }
    
    private void addRider(Policy policy) {
        System.out.println("<Insert fancy available rider retrieval here>");
        Input.as("===== Riders =====\n1. Additional rider", "Enter the index: ", Ints::tryParse, i -> i == 1);
        policy.setCoverage(new AdditionalRider(policy.getCoverage()));
        System.out.println("Added rider\n");
    }
    
    private void pay(Policy policy) {
        double amount = Input.as("Enter amount: ", "Invalid amount", Doubles::tryParse, value -> value > 0);
        policy.pay(new Payment(policy, PaymentType.CHEQUE, amount));
        System.out.println("Payment has been made");
    }
    
}
