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

import java.util.*;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;


public class Menu<T> {
    
    private String title;
    private Map<Integer, Pair<String, Consumer<T>>> options; 
    
    
    public Menu(String title) {
        this.title = title;
        options = new HashMap<>();
    }
    
    
    public void view() {
        System.out.println("===== " + title + " Menu =====");
        options.forEach((index, option) -> System.out.println(index + ". " + option.getLeft()));
        System.out.println("\n");
    }
    
    public int select(T viewer) {
        int index = Input.match("Please select an option: ", options::containsKey);
        options.get(index).getRight().accept(viewer);
        
        return index;
    }
    
    
    public void register(int index, String title, Consumer<T> consumer) {
        options.put(index, Pair.of(title, consumer));
    }
    
}
