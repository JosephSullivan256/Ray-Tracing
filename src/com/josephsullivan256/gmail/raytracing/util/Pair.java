package com.josephsullivan256.gmail.raytracing.util;

public class Pair<A,B> {
	public final A a;
	public final B b;
	public Pair(A a, B b){
		this.a = a;
		this.b = b;
	}
	public static <C,D> Pair<C,D> init(C c, D d){
		return new Pair<C,D>(c,d);
	}
}
