package com.dalsps.query;

import com.dalsps.CachedObj;


class IdNode implements Node {

  final String id;

  public IdNode(String i) {
    id = i;
  }


  public Object eval(B b) {
    //return ((CachedObj)b).data.values[0];
	 return  b.getAttribute(id);
  }
}
