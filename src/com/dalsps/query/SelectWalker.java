// $ANTLR 3.4 E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g 2013-02-10 22:05:22

  package com.dalsps.query;
  import java.util.List;
  import java.util.Map;
  import java.util.Set;
  import com.dalsps.Cache;
  import java.util.ArrayList;	


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SelectWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ATTR_LIST", "Action_Intent", "And", "Digit", "Eq", "From", "GT", "GTEq", "INTENT_LIST", "Id", "Int", "LT", "LTEq", "Minus", "NEq", "Not", "Or", "ROOT", "Select", "Space", "Str", "UNARY_MINUS", "Where", "'('", "')'", "','", "';'"
    };

    public static final int EOF=-1;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int ATTR_LIST=4;
    public static final int Action_Intent=5;
    public static final int And=6;
    public static final int Digit=7;
    public static final int Eq=8;
    public static final int From=9;
    public static final int GT=10;
    public static final int GTEq=11;
    public static final int INTENT_LIST=12;
    public static final int Id=13;
    public static final int Int=14;
    public static final int LT=15;
    public static final int LTEq=16;
    public static final int Minus=17;
    public static final int NEq=18;
    public static final int Not=19;
    public static final int Or=20;
    public static final int ROOT=21;
    public static final int Select=22;
    public static final int Space=23;
    public static final int Str=24;
    public static final int UNARY_MINUS=25;
    public static final int Where=26;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public SelectWalker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public SelectWalker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return SelectWalker.tokenNames; }
    public String getGrammarFileName() { return "E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g"; }



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
    		



    // $ANTLR start "query"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:38:1: query returns [List<Object> result] : ^( ROOT select_stat ) ;
    public final List<Object> query() throws RecognitionException {
        List<Object> result = null;


        Node select_stat1 =null;


        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:39:2: ( ^( ROOT select_stat ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:39:4: ^( ROOT select_stat )
            {
            match(input,ROOT,FOLLOW_ROOT_in_query49); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_select_stat_in_query51);
            select_stat1=select_stat();

            state._fsp--;


            match(input, Token.UP, null); 


            result = (List<Object>)select_stat1.eval(null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "query"



    // $ANTLR start "select_stat"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:42:1: select_stat returns [Node node] : ^( Select attr_list Id expr ( intent_list )? ) ;
    public final Node select_stat() throws RecognitionException {
        Node node = null;


        ArrayList<String> attr_list2 =null;

        ArrayList<String> intent_list3 =null;

        Node expr4 =null;


        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:43:2: ( ^( Select attr_list Id expr ( intent_list )? ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:43:4: ^( Select attr_list Id expr ( intent_list )? )
            {
            match(input,Select,FOLLOW_Select_in_select_stat70); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_attr_list_in_select_stat72);
            attr_list2=attr_list();

            state._fsp--;


            match(input,Id,FOLLOW_Id_in_select_stat74); 

            pushFollow(FOLLOW_expr_in_select_stat76);
            expr4=expr();

            state._fsp--;


            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:43:31: ( intent_list )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==INTENT_LIST) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:43:31: intent_list
                    {
                    pushFollow(FOLLOW_intent_list_in_select_stat78);
                    intent_list3=intent_list();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 


            node = new SelectNode(attr_list2,intent_list3, cache, expr4);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return node;
    }
    // $ANTLR end "select_stat"



    // $ANTLR start "attr_list"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:47:1: attr_list returns [ArrayList<String> attributes] : ^( ATTR_LIST ( Id )+ ) ;
    public final ArrayList<String> attr_list() throws RecognitionException {
        ArrayList<String> attributes = null;


        CommonTree Id5=null;

        attributes = new ArrayList<String>();
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:49:2: ( ^( ATTR_LIST ( Id )+ ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:49:4: ^( ATTR_LIST ( Id )+ )
            {
            match(input,ATTR_LIST,FOLLOW_ATTR_LIST_in_attr_list107); 

            match(input, Token.DOWN, null); 
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:49:16: ( Id )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==Id) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:49:17: Id
            	    {
            	    Id5=(CommonTree)match(input,Id,FOLLOW_Id_in_attr_list110); 

            	    attributes.add((Id5!=null?Id5.getText():null));attributeList.add((Id5!=null?Id5.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return attributes;
    }
    // $ANTLR end "attr_list"



    // $ANTLR start "intent_list"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:52:1: intent_list returns [ArrayList<String> intents] : ^( INTENT_LIST ( Id )+ ) ;
    public final ArrayList<String> intent_list() throws RecognitionException {
        ArrayList<String> intents = null;


        CommonTree Id6=null;

        intents = new ArrayList<String>();
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:54:2: ( ^( INTENT_LIST ( Id )+ ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:54:4: ^( INTENT_LIST ( Id )+ )
            {
            match(input,INTENT_LIST,FOLLOW_INTENT_LIST_in_intent_list135); 

            match(input, Token.DOWN, null); 
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:54:18: ( Id )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==Id) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:54:19: Id
            	    {
            	    Id6=(CommonTree)match(input,Id,FOLLOW_Id_in_intent_list138); 

            	    intents.add((Id6!=null?Id6.getText():null));intentList.add((Id6!=null?Id6.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return intents;
    }
    // $ANTLR end "intent_list"



    // $ANTLR start "expr"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:58:1: expr returns [Node node] : ( ^( Or a= expr b= expr ) | ^( And a= expr b= expr ) | ^( Eq a= expr b= expr ) | ^( NEq a= expr b= expr ) | ^( LT a= expr b= expr ) | ^( LTEq a= expr b= expr ) | ^( GT a= expr b= expr ) | ^( GTEq a= expr b= expr ) | ^( UNARY_MINUS a= expr ) | ^( Not a= expr ) | Str | Int | Id );
    public final Node expr() throws RecognitionException {
        Node node = null;


        CommonTree Str7=null;
        CommonTree Int8=null;
        CommonTree Id9=null;
        Node a =null;

        Node b =null;


        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:59:2: ( ^( Or a= expr b= expr ) | ^( And a= expr b= expr ) | ^( Eq a= expr b= expr ) | ^( NEq a= expr b= expr ) | ^( LT a= expr b= expr ) | ^( LTEq a= expr b= expr ) | ^( GT a= expr b= expr ) | ^( GTEq a= expr b= expr ) | ^( UNARY_MINUS a= expr ) | ^( Not a= expr ) | Str | Int | Id )
            int alt4=13;
            switch ( input.LA(1) ) {
            case Or:
                {
                alt4=1;
                }
                break;
            case And:
                {
                alt4=2;
                }
                break;
            case Eq:
                {
                alt4=3;
                }
                break;
            case NEq:
                {
                alt4=4;
                }
                break;
            case LT:
                {
                alt4=5;
                }
                break;
            case LTEq:
                {
                alt4=6;
                }
                break;
            case GT:
                {
                alt4=7;
                }
                break;
            case GTEq:
                {
                alt4=8;
                }
                break;
            case UNARY_MINUS:
                {
                alt4=9;
                }
                break;
            case Not:
                {
                alt4=10;
                }
                break;
            case Str:
                {
                alt4=11;
                }
                break;
            case Int:
                {
                alt4=12;
                }
                break;
            case Id:
                {
                alt4=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:59:4: ^( Or a= expr b= expr )
                    {
                    match(input,Or,FOLLOW_Or_in_expr161); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr165);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr169);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new OrNode(a, b);

                    }
                    break;
                case 2 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:60:4: ^( And a= expr b= expr )
                    {
                    match(input,And,FOLLOW_And_in_expr180); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr184);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr188);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new AndNode(a, b);

                    }
                    break;
                case 3 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:61:4: ^( Eq a= expr b= expr )
                    {
                    match(input,Eq,FOLLOW_Eq_in_expr198); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr202);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr206);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new EqNode(a, b);

                    }
                    break;
                case 4 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:62:4: ^( NEq a= expr b= expr )
                    {
                    match(input,NEq,FOLLOW_NEq_in_expr217); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr221);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr225);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new NEqNode(a, b);

                    }
                    break;
                case 5 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:63:4: ^( LT a= expr b= expr )
                    {
                    match(input,LT,FOLLOW_LT_in_expr235); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr239);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr243);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new LTNode(a, b);

                    }
                    break;
                case 6 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:64:4: ^( LTEq a= expr b= expr )
                    {
                    match(input,LTEq,FOLLOW_LTEq_in_expr254); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr258);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr262);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new LTEqNode(a, b);

                    }
                    break;
                case 7 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:65:4: ^( GT a= expr b= expr )
                    {
                    match(input,GT,FOLLOW_GT_in_expr271); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr275);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr279);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new GTNode(a, b);

                    }
                    break;
                case 8 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:66:4: ^( GTEq a= expr b= expr )
                    {
                    match(input,GTEq,FOLLOW_GTEq_in_expr290); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr294);
                    a=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr298);
                    b=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = new GTEqNode(a, b);

                    }
                    break;
                case 9 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:67:4: ^( UNARY_MINUS a= expr )
                    {
                    match(input,UNARY_MINUS,FOLLOW_UNARY_MINUS_in_expr307); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr311);
                    a=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = null; /* TODO */

                    }
                    break;
                case 10 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:68:4: ^( Not a= expr )
                    {
                    match(input,Not,FOLLOW_Not_in_expr320); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr324);
                    a=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    node = null; /* TODO */

                    }
                    break;
                case 11 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:69:4: Str
                    {
                    Str7=(CommonTree)match(input,Str,FOLLOW_Str_in_expr340); 

                    node = new AtomNode((Str7!=null?Str7.getText():null));

                    }
                    break;
                case 12 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:70:4: Int
                    {
                    Int8=(CommonTree)match(input,Int,FOLLOW_Int_in_expr365); 

                    node = new AtomNode(Float.valueOf((Int8!=null?Int8.getText():null)));

                    }
                    break;
                case 13 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\SelectWalker.g:71:4: Id
                    {
                    Id9=(CommonTree)match(input,Id,FOLLOW_Id_in_expr390); 

                    node = new IdNode((Id9!=null?Id9.getText():null));

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return node;
    }
    // $ANTLR end "expr"

    // Delegated rules


 

    public static final BitSet FOLLOW_ROOT_in_query49 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_select_stat_in_query51 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Select_in_select_stat70 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attr_list_in_select_stat72 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Id_in_select_stat74 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_select_stat76 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_intent_list_in_select_stat78 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTR_LIST_in_attr_list107 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Id_in_attr_list110 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_INTENT_LIST_in_intent_list135 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Id_in_intent_list138 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_Or_in_expr161 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr165 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr169 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expr180 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr184 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr188 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Eq_in_expr198 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr202 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr206 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEq_in_expr217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr221 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr225 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expr235 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr239 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr243 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEq_in_expr254 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr258 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr262 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expr271 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr275 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr279 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEq_in_expr290 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr294 = new BitSet(new long[]{0x00000000031DED40L});
    public static final BitSet FOLLOW_expr_in_expr298 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MINUS_in_expr307 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr311 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Not_in_expr320 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr324 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Str_in_expr340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Int_in_expr365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Id_in_expr390 = new BitSet(new long[]{0x0000000000000002L});

}