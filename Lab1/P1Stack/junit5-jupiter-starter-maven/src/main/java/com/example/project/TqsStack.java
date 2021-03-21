/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import java.util.LinkedList;

public class TqsStack<T> {

	LinkedList<T> stack;
	int bound = -1;

	public TqsStack(){ stack = new LinkedList<>(); }

	public TqsStack(int limit){
		bound = limit;
		stack = new LinkedList<>();
	}

	public T push(T o) {
		if(stack.size()==bound){
			throw new IllegalStateException();
		}
		stack.push(o);
		return o;
	}

	public T pop(){ return stack.pop(); }

	public T peek(){
		return stack.getFirst();
	}

	public int size(){
		return stack.size();
	}

	public boolean isEmpty(){
		return stack.isEmpty();
	}

}
