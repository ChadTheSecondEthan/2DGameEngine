package com.chad.engine.utils;

public class Pair<E, T> {
	
	private E key;
	private T value;
	
	public Pair(E key, T value) {
		this.key = key;
		this.value = value;
	}
	
	public E getKey() { return key; }
	public T getValue() { return value; }
}