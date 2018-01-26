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

import java.util.List;


public class PolicyInformation {
    
    private String termsAndConditions;
    private String premiumInformation;
    private String payoutInformation;
    private List<String> riders;
    
    
    public PolicyInformation(String termsAndConditions, String premiumInformation, String payoutInformation, List<String> riders) {
        this.termsAndConditions = termsAndConditions;
        this.premiumInformation = premiumInformation;
        this.payoutInformation = payoutInformation;
        this.riders = riders;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public String getPremiumInformation() {
        return premiumInformation;
    }

    public String getPayoutInformation() {
        return payoutInformation;
    }

    public List<String> getRiders() {
        return riders;
    }
    
}
