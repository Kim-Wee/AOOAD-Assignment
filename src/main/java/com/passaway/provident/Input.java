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

import com.google.common.primitives.Ints;

import java.util.Scanner;
import java.util.function.*;


public class Input {
    
    private static Scanner scanner = new Scanner(System.in);
    
    
    public static String get(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
    
    
    public static int as(String message, int size) {
        return as(message, "Input must be an integer between 1 and " + size, value -> {
            Integer index = Ints.tryParse(value);
            if (index != null && index > 0 && index <= size) {
                return index;
                
            } else {
                return null;
            }
        });
    }
    
    
    public static <T> T as(String message, String error, Function<String, T> function) {
        System.out.println(message);
        while (true) {
            T value = function.apply(scanner.nextLine());
            if (value == null) {
                System.out.println(error);
                
            } else {
                return value;
            }
        }
    }
    
    
    public static String match(String message, String error, Predicate<String> predicate) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine();
            if (predicate.test(value)) {
                return value;
                
            } else {
                System.out.println(error);
            }
        }
    }
    
}
