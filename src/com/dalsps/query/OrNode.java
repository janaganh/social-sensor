package com.dalsps.query;

class OrNode extends BinNode {

	  public OrNode(Node l, Node r) {
	    super(l, r);
	  }
	  @Override
	  public Object eval(B b) { 
		  Object leftVal = super.left.eval(b);
		  Object rightVal = super.left.eval(b);
		  //if sensor type is not the same then compare to true
		  if (leftVal == null || rightVal == null)
			 return true; 
		  return (Boolean)super.left.eval(b) || (Boolean)super.right.eval(b);
	  }
	}