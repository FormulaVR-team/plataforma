package com.fvr._comun;

public final class Logger {

	private final String tagText;
	private StringBuffer text = null;
	
	public static Logger newLogger(String tagText) {
		return new Logger(tagText);
	}
	
	private Logger(String tagText) {
		this.tagText = tagText;
		this.initialize();
	}
	
	private void initialize() {
		this.text = new StringBuffer();
		this.text.append("[").append(this.tagText).append("] ");	
	}
	
	public Logger a(String s) {
		this.text.append(s);
		return this;
	}
	
	public Logger a(int i) {
		this.text.append(i);
		return this;
	}
	
	public Logger a(char c) {
		this.text.append(c);
		return this;
	}
	
	public Logger a(Object o) {
		this.text.append(o);
		return this;
	}
	
	public Logger a(long l) {
		this.text.append(l);
		return this;
	}
	
	public Logger a(boolean b) {
		this.text.append(b);
		return this;
	}
	
	public Logger a(char[] s) {
		this.text.append(s);
		return this;
	}
	
	public Logger a(double d) {
		this.text.append(d);
		return this;
	}
	
	public Logger a(float f) {
		this.text.append(f);
		return this;
	}
	
	public Logger a(StringBuffer sb) {
		this.text.append(sb);
		return this;
	}
	
	public void pln(String s) {
		this.a(s);
		this.pln();
	}
	
	public void pln(int i) {
		this.a(i);
		this.pln();
	}
	
	public void pln(Object o) {
		this.a(o);
		this.pln();
	}
	
	public void pln(long l) {
		this.a(l);
		this.pln();
	}
	
	public void pln(boolean b) {
		this.a(b);
		this.pln();
	}
	
	public void pln(char[] s) {
		this.a(s);
		this.pln();
	}
	
	public void pln(double d) {
		this.a(d);
		this.pln();
	}
	
	public void pln(float f) {
		this.a(f);
		this.pln();
	}
	
	public void pln(StringBuffer sb) {
		this.a(sb);
		this.pln();
	}

	public void pln(){
		this.writeOut();
		this.initialize();
	}

	private void writeOut() {
		System.out.println(this.text.toString());
	}
}
