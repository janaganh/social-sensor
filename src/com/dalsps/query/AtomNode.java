package com.dalsps.query;


class AtomNode implements Node {

  final Object value;

  public AtomNode(Object v) {
    value = v;
  }

  public Object eval(B b) {
	  //to do not good have to change
	  return new Double(String.valueOf(value));
  }
}