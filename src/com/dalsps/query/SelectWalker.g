tree grammar SelectWalker;

options {
  tokenVocab=Select;
  ASTLabelType=CommonTree;
}

@header {
  package com.dalsps.query;
  import java.util.List;
  import java.util.Map;
  import java.util.Set;
  import com.dalsps.Cache;
  import java.util.ArrayList;	
}

@members {

  private Cache cache;
  private ArrayList<String> attributeList;
  private ArrayList<String> intentList;	
  public SelectWalker(CommonTreeNodeStream nodes, Cache cache) {
    super(nodes);
    this.cache = cache;
    attributeList = new ArrayList<String>();	
    intentList = new ArrayList<String>();		    
  }
 public ArrayList<String> getAttributeList(){
 	return attributeList; 
 }

 public ArrayList<String> getIntentList(){
 	return intentList; 
 }
		
}

query returns [List<Object> result]
 : ^(ROOT select_stat) {$result = (List<Object>)$select_stat.node.eval(null);}
 ;

select_stat returns [Node node]
 : ^(Select attr_list Id expr intent_list?) 
    {$node = new SelectNode($attr_list.attributes,$intent_list.intents, cache, $expr.node);}
 ;

attr_list returns [ArrayList<String> attributes]
@init{$attributes = new ArrayList<String>();}
 : ^(ATTR_LIST (Id {$attributes.add($Id.text);attributeList.add($Id.text);})+)
 ;

intent_list returns [ArrayList<String> intents]
@init{$intents= new ArrayList<String>();}
 : ^(INTENT_LIST (Id {$intents.add($Id.text);intentList.add($Id.text);})+)
 ;

 
expr returns [Node node]
 : ^(Or a=expr b=expr)   {$node = new OrNode($a.node, $b.node);}
 | ^(And a=expr b=expr)  {$node = new AndNode($a.node, $b.node);}
 | ^(Eq a=expr b=expr)   {$node = new EqNode($a.node, $b.node);}
 | ^(NEq a=expr b=expr)  {$node = new NEqNode($a.node, $b.node);}
 | ^(LT a=expr b=expr)   {$node = new LTNode($a.node, $b.node);}
 | ^(LTEq a=expr b=expr) {$node = new LTEqNode($a.node, $b.node);}
 | ^(GT a=expr b=expr)   {$node = new GTNode($a.node, $b.node);}
 | ^(GTEq a=expr b=expr) {$node = new GTEqNode($a.node, $b.node);}
 | ^(UNARY_MINUS a=expr) {$node = null; /* TODO */}
 | ^(Not a=expr)         {$node = null; /* TODO */}
 | Str                   {$node = new AtomNode($Str.text);}
 | Int                   {$node = new AtomNode(Float.valueOf($Int.text));}
 | Id                    {$node = new IdNode($Id.text);}
 ;