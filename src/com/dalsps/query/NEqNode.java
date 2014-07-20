package com.dalsps.query;

class NEqNode extends BinNode {

	  public NEqNode(Node l, Node r) {
	    super(l, r);
	  }

	  @Override
	  public Object eval(B b) {
		  Object leftVal = super.left.eval(b);
		  Object rightVal = super.left.eval(b);
		  //if sensor type is not the same then compare to true
		  if (leftVal == null || rightVal == null)
			 return true; 
		  
		  return !super.left.eval(b).equals(super.right.eval(b));
	  }
	}
