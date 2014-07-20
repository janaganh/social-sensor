package com.dalsps.query;

public abstract class BinNode implements Node {

	  final Node left;
	  final Node right;

	  public BinNode(Node l, Node r) {
	    left = l;
	    right = r;
	  }

	public abstract Object eval(B b);
	
}