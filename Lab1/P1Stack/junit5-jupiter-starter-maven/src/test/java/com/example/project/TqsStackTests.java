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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTests {
	TqsStack<String> tqsStack;
	@BeforeEach
	public void setup(){
		tqsStack = new TqsStack(4);
		tqsStack.push("Alberto");
		tqsStack.push("Bernardo");
		tqsStack.push("Carlos");
	}

	@Test
	@DisplayName("size")
	void testsize() {
		assertEquals(3,tqsStack.size());
		tqsStack = new TqsStack();
		assertEquals(0,tqsStack.size());
	}

	@Test
	@DisplayName("isEmpty")
	void checkempty() {
		assertFalse(tqsStack.isEmpty());
		tqsStack = new TqsStack();
		assertTrue(tqsStack.isEmpty(), "stack deve estar vazia apos criar");
	}

	@Test
	@DisplayName("push")
	void testpush() {
		tqsStack.push("Diogo");
		assertEquals(4,tqsStack.size());
	}

	@Test
	@DisplayName("pushbound")
	void testpushbound() {
		tqsStack.push("Diogo");
		assertThrows(IllegalStateException.class, ()->tqsStack.push("Eduardo"));
	}

	@Test
	@DisplayName("pop")
	void testpop() {
		String x = tqsStack.pop();
		assertEquals("Carlos", x);
		assertEquals(2, tqsStack.size());
		tqsStack = new TqsStack();
		assertThrows(NoSuchElementException.class,()->tqsStack.pop());
	}

	@Test
	@DisplayName("peek")
	void testpeek() {
		String x = tqsStack.peek();
		assertEquals("Carlos", x);
		assertEquals(3, tqsStack.size());
		tqsStack = new TqsStack();
		assertThrows(NoSuchElementException.class,()->tqsStack.peek());
	}

	@Test
	@DisplayName("pushpoppeek")
	void testpushpoppeek() {
		tqsStack.push("Diogo");
		String d1 = tqsStack.peek();
		assertEquals("Diogo", d1);
		assertEquals(4, tqsStack.size());
		String d2 = tqsStack.pop();
		assertEquals(d1,d2);
		assertEquals(3, tqsStack.size());
		String c1 = tqsStack.peek();
		assertEquals("Carlos", c1);
		assertEquals(3, tqsStack.size());
		String c2 = tqsStack.pop();
		assertEquals(c1,c2);
		assertEquals(2, tqsStack.size());
		String b1 = tqsStack.peek();
		assertEquals("Bernardo", b1);
		assertEquals(2, tqsStack.size());
		String b2 = tqsStack.pop();
		assertEquals(b1,b2);
		assertEquals(1, tqsStack.size());
		String a1 = tqsStack.peek();
		assertEquals("Alberto", a1);
		assertEquals(1, tqsStack.size());
		String a2 = tqsStack.pop();
		assertEquals(a1,a2);
		assertEquals(0, tqsStack.size());
		assertTrue(tqsStack.isEmpty());
	}
}
