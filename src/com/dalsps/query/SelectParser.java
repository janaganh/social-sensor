// $ANTLR 3.4 E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g 2013-02-10 22:05:22

  package com.dalsps.query;
  


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class SelectParser extends Parser {
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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public SelectParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public SelectParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return SelectParser.tokenNames; }
    public String getGrammarFileName() { return "E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g"; }


    public static class parse_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parse"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:39:1: parse : select_stat EOF -> ^( ROOT select_stat ) ;
    public final SelectParser.parse_return parse() throws RecognitionException {
        SelectParser.parse_return retval = new SelectParser.parse_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF2=null;
        SelectParser.select_stat_return select_stat1 =null;


        Object EOF2_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_select_stat=new RewriteRuleSubtreeStream(adaptor,"rule select_stat");
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:40:2: ( select_stat EOF -> ^( ROOT select_stat ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:40:4: select_stat EOF
            {
            pushFollow(FOLLOW_select_stat_in_parse231);
            select_stat1=select_stat();

            state._fsp--;

            stream_select_stat.add(select_stat1.getTree());

            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_parse233);  
            stream_EOF.add(EOF2);


            // AST REWRITE
            // elements: select_stat
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 40:20: -> ^( ROOT select_stat )
            {
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:40:23: ^( ROOT select_stat )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ROOT, "ROOT")
                , root_1);

                adaptor.addChild(root_1, stream_select_stat.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parse"


    public static class select_stat_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "select_stat"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:43:1: select_stat : Select attr_list From Id where_stat ( Action_Intent intent_list )? ';' -> ^( Select attr_list Id where_stat ( intent_list )? ) ;
    public final SelectParser.select_stat_return select_stat() throws RecognitionException {
        SelectParser.select_stat_return retval = new SelectParser.select_stat_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token Select3=null;
        Token From5=null;
        Token Id6=null;
        Token Action_Intent8=null;
        Token char_literal10=null;
        SelectParser.attr_list_return attr_list4 =null;

        SelectParser.where_stat_return where_stat7 =null;

        SelectParser.intent_list_return intent_list9 =null;


        Object Select3_tree=null;
        Object From5_tree=null;
        Object Id6_tree=null;
        Object Action_Intent8_tree=null;
        Object char_literal10_tree=null;
        RewriteRuleTokenStream stream_Select=new RewriteRuleTokenStream(adaptor,"token Select");
        RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
        RewriteRuleTokenStream stream_Id=new RewriteRuleTokenStream(adaptor,"token Id");
        RewriteRuleTokenStream stream_Action_Intent=new RewriteRuleTokenStream(adaptor,"token Action_Intent");
        RewriteRuleTokenStream stream_From=new RewriteRuleTokenStream(adaptor,"token From");
        RewriteRuleSubtreeStream stream_intent_list=new RewriteRuleSubtreeStream(adaptor,"rule intent_list");
        RewriteRuleSubtreeStream stream_attr_list=new RewriteRuleSubtreeStream(adaptor,"rule attr_list");
        RewriteRuleSubtreeStream stream_where_stat=new RewriteRuleSubtreeStream(adaptor,"rule where_stat");
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:44:2: ( Select attr_list From Id where_stat ( Action_Intent intent_list )? ';' -> ^( Select attr_list Id where_stat ( intent_list )? ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:44:4: Select attr_list From Id where_stat ( Action_Intent intent_list )? ';'
            {
            Select3=(Token)match(input,Select,FOLLOW_Select_in_select_stat252);  
            stream_Select.add(Select3);


            pushFollow(FOLLOW_attr_list_in_select_stat254);
            attr_list4=attr_list();

            state._fsp--;

            stream_attr_list.add(attr_list4.getTree());

            From5=(Token)match(input,From,FOLLOW_From_in_select_stat256);  
            stream_From.add(From5);


            Id6=(Token)match(input,Id,FOLLOW_Id_in_select_stat258);  
            stream_Id.add(Id6);


            pushFollow(FOLLOW_where_stat_in_select_stat260);
            where_stat7=where_stat();

            state._fsp--;

            stream_where_stat.add(where_stat7.getTree());

            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:44:40: ( Action_Intent intent_list )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==Action_Intent) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:44:41: Action_Intent intent_list
                    {
                    Action_Intent8=(Token)match(input,Action_Intent,FOLLOW_Action_Intent_in_select_stat263);  
                    stream_Action_Intent.add(Action_Intent8);


                    pushFollow(FOLLOW_intent_list_in_select_stat265);
                    intent_list9=intent_list();

                    state._fsp--;

                    stream_intent_list.add(intent_list9.getTree());

                    }
                    break;

            }


            char_literal10=(Token)match(input,30,FOLLOW_30_in_select_stat270);  
            stream_30.add(char_literal10);


            // AST REWRITE
            // elements: where_stat, Id, attr_list, Select, intent_list
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 44:74: -> ^( Select attr_list Id where_stat ( intent_list )? )
            {
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:44:77: ^( Select attr_list Id where_stat ( intent_list )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_Select.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_attr_list.nextTree());

                adaptor.addChild(root_1, 
                stream_Id.nextNode()
                );

                adaptor.addChild(root_1, stream_where_stat.nextTree());

                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:44:110: ( intent_list )?
                if ( stream_intent_list.hasNext() ) {
                    adaptor.addChild(root_1, stream_intent_list.nextTree());

                }
                stream_intent_list.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "select_stat"


    public static class attr_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "attr_list"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:47:1: attr_list : Id ( ',' Id )* -> ^( ATTR_LIST ( Id )+ ) ;
    public final SelectParser.attr_list_return attr_list() throws RecognitionException {
        SelectParser.attr_list_return retval = new SelectParser.attr_list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token Id11=null;
        Token char_literal12=null;
        Token Id13=null;

        Object Id11_tree=null;
        Object char_literal12_tree=null;
        Object Id13_tree=null;
        RewriteRuleTokenStream stream_Id=new RewriteRuleTokenStream(adaptor,"token Id");
        RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");

        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:48:2: ( Id ( ',' Id )* -> ^( ATTR_LIST ( Id )+ ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:48:4: Id ( ',' Id )*
            {
            Id11=(Token)match(input,Id,FOLLOW_Id_in_attr_list300);  
            stream_Id.add(Id11);


            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:48:7: ( ',' Id )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==29) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:48:8: ',' Id
            	    {
            	    char_literal12=(Token)match(input,29,FOLLOW_29_in_attr_list303);  
            	    stream_29.add(char_literal12);


            	    Id13=(Token)match(input,Id,FOLLOW_Id_in_attr_list305);  
            	    stream_Id.add(Id13);


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // AST REWRITE
            // elements: Id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 48:17: -> ^( ATTR_LIST ( Id )+ )
            {
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:48:20: ^( ATTR_LIST ( Id )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ATTR_LIST, "ATTR_LIST")
                , root_1);

                if ( !(stream_Id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_Id.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_Id.nextNode()
                    );

                }
                stream_Id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "attr_list"


    public static class where_stat_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "where_stat"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:51:1: where_stat : ( Where expr -> expr | -> ^( Eq Int[\"1\"] Int[\"1\"] ) );
    public final SelectParser.where_stat_return where_stat() throws RecognitionException {
        SelectParser.where_stat_return retval = new SelectParser.where_stat_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token Where14=null;
        SelectParser.expr_return expr15 =null;


        Object Where14_tree=null;
        RewriteRuleTokenStream stream_Where=new RewriteRuleTokenStream(adaptor,"token Where");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:52:2: ( Where expr -> expr | -> ^( Eq Int[\"1\"] Int[\"1\"] ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==Where) ) {
                alt3=1;
            }
            else if ( (LA3_0==Action_Intent||LA3_0==30) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:52:4: Where expr
                    {
                    Where14=(Token)match(input,Where,FOLLOW_Where_in_where_stat327);  
                    stream_Where.add(Where14);


                    pushFollow(FOLLOW_expr_in_where_stat329);
                    expr15=expr();

                    state._fsp--;

                    stream_expr.add(expr15.getTree());

                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 52:15: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:53:15: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 53:15: -> ^( Eq Int[\"1\"] Int[\"1\"] )
                    {
                        // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:53:18: ^( Eq Int[\"1\"] Int[\"1\"] )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(Eq, "Eq")
                        , root_1);

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(Int, "1")
                        );

                        adaptor.addChild(root_1, 
                        (Object)adaptor.create(Int, "1")
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "where_stat"


    public static class expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:57:1: expr : or_expr ;
    public final SelectParser.expr_return expr() throws RecognitionException {
        SelectParser.expr_return retval = new SelectParser.expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        SelectParser.or_expr_return or_expr16 =null;



        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:58:2: ( or_expr )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:58:4: or_expr
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_or_expr_in_expr389);
            or_expr16=or_expr();

            state._fsp--;

            adaptor.addChild(root_0, or_expr16.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class or_expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "or_expr"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:61:1: or_expr : and_expr ( Or ^ and_expr )* ;
    public final SelectParser.or_expr_return or_expr() throws RecognitionException {
        SelectParser.or_expr_return retval = new SelectParser.or_expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token Or18=null;
        SelectParser.and_expr_return and_expr17 =null;

        SelectParser.and_expr_return and_expr19 =null;


        Object Or18_tree=null;

        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:62:2: ( and_expr ( Or ^ and_expr )* )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:62:4: and_expr ( Or ^ and_expr )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_and_expr_in_or_expr400);
            and_expr17=and_expr();

            state._fsp--;

            adaptor.addChild(root_0, and_expr17.getTree());

            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:62:13: ( Or ^ and_expr )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==Or) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:62:14: Or ^ and_expr
            	    {
            	    Or18=(Token)match(input,Or,FOLLOW_Or_in_or_expr403); 
            	    Or18_tree = 
            	    (Object)adaptor.create(Or18)
            	    ;
            	    root_0 = (Object)adaptor.becomeRoot(Or18_tree, root_0);


            	    pushFollow(FOLLOW_and_expr_in_or_expr406);
            	    and_expr19=and_expr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, and_expr19.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "or_expr"


    public static class and_expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "and_expr"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:65:1: and_expr : eq_expr ( And ^ eq_expr )* ;
    public final SelectParser.and_expr_return and_expr() throws RecognitionException {
        SelectParser.and_expr_return retval = new SelectParser.and_expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token And21=null;
        SelectParser.eq_expr_return eq_expr20 =null;

        SelectParser.eq_expr_return eq_expr22 =null;


        Object And21_tree=null;

        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:66:2: ( eq_expr ( And ^ eq_expr )* )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:66:4: eq_expr ( And ^ eq_expr )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_eq_expr_in_and_expr419);
            eq_expr20=eq_expr();

            state._fsp--;

            adaptor.addChild(root_0, eq_expr20.getTree());

            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:66:12: ( And ^ eq_expr )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==And) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:66:13: And ^ eq_expr
            	    {
            	    And21=(Token)match(input,And,FOLLOW_And_in_and_expr422); 
            	    And21_tree = 
            	    (Object)adaptor.create(And21)
            	    ;
            	    root_0 = (Object)adaptor.becomeRoot(And21_tree, root_0);


            	    pushFollow(FOLLOW_eq_expr_in_and_expr425);
            	    eq_expr22=eq_expr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, eq_expr22.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "and_expr"


    public static class eq_expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "eq_expr"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:69:1: eq_expr : rel_expr ( ( Eq | NEq ) ^ rel_expr )* ;
    public final SelectParser.eq_expr_return eq_expr() throws RecognitionException {
        SelectParser.eq_expr_return retval = new SelectParser.eq_expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set24=null;
        SelectParser.rel_expr_return rel_expr23 =null;

        SelectParser.rel_expr_return rel_expr25 =null;


        Object set24_tree=null;

        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:70:2: ( rel_expr ( ( Eq | NEq ) ^ rel_expr )* )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:70:4: rel_expr ( ( Eq | NEq ) ^ rel_expr )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_rel_expr_in_eq_expr438);
            rel_expr23=rel_expr();

            state._fsp--;

            adaptor.addChild(root_0, rel_expr23.getTree());

            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:70:13: ( ( Eq | NEq ) ^ rel_expr )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==Eq||LA6_0==NEq) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:70:14: ( Eq | NEq ) ^ rel_expr
            	    {
            	    set24=(Token)input.LT(1);

            	    set24=(Token)input.LT(1);

            	    if ( input.LA(1)==Eq||input.LA(1)==NEq ) {
            	        input.consume();
            	        root_0 = (Object)adaptor.becomeRoot(
            	        (Object)adaptor.create(set24)
            	        , root_0);
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_rel_expr_in_eq_expr450);
            	    rel_expr25=rel_expr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, rel_expr25.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "eq_expr"


    public static class rel_expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rel_expr"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:73:1: rel_expr : unary_expr ( ( LT | LTEq | GT | GTEq ) ^ unary_expr )? ;
    public final SelectParser.rel_expr_return rel_expr() throws RecognitionException {
        SelectParser.rel_expr_return retval = new SelectParser.rel_expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set27=null;
        SelectParser.unary_expr_return unary_expr26 =null;

        SelectParser.unary_expr_return unary_expr28 =null;


        Object set27_tree=null;

        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:74:2: ( unary_expr ( ( LT | LTEq | GT | GTEq ) ^ unary_expr )? )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:74:4: unary_expr ( ( LT | LTEq | GT | GTEq ) ^ unary_expr )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_unary_expr_in_rel_expr463);
            unary_expr26=unary_expr();

            state._fsp--;

            adaptor.addChild(root_0, unary_expr26.getTree());

            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:74:15: ( ( LT | LTEq | GT | GTEq ) ^ unary_expr )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0 >= GT && LA7_0 <= GTEq)||(LA7_0 >= LT && LA7_0 <= LTEq)) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:74:16: ( LT | LTEq | GT | GTEq ) ^ unary_expr
                    {
                    set27=(Token)input.LT(1);

                    set27=(Token)input.LT(1);

                    if ( (input.LA(1) >= GT && input.LA(1) <= GTEq)||(input.LA(1) >= LT && input.LA(1) <= LTEq) ) {
                        input.consume();
                        root_0 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(set27)
                        , root_0);
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_unary_expr_in_rel_expr483);
                    unary_expr28=unary_expr();

                    state._fsp--;

                    adaptor.addChild(root_0, unary_expr28.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rel_expr"


    public static class unary_expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unary_expr"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:77:1: unary_expr : ( Minus atom -> ^( UNARY_MINUS atom ) | Not atom -> ^( Not atom ) | atom );
    public final SelectParser.unary_expr_return unary_expr() throws RecognitionException {
        SelectParser.unary_expr_return retval = new SelectParser.unary_expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token Minus29=null;
        Token Not31=null;
        SelectParser.atom_return atom30 =null;

        SelectParser.atom_return atom32 =null;

        SelectParser.atom_return atom33 =null;


        Object Minus29_tree=null;
        Object Not31_tree=null;
        RewriteRuleTokenStream stream_Minus=new RewriteRuleTokenStream(adaptor,"token Minus");
        RewriteRuleTokenStream stream_Not=new RewriteRuleTokenStream(adaptor,"token Not");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:78:2: ( Minus atom -> ^( UNARY_MINUS atom ) | Not atom -> ^( Not atom ) | atom )
            int alt8=3;
            switch ( input.LA(1) ) {
            case Minus:
                {
                alt8=1;
                }
                break;
            case Not:
                {
                alt8=2;
                }
                break;
            case Id:
            case Int:
            case Str:
            case 27:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }

            switch (alt8) {
                case 1 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:78:4: Minus atom
                    {
                    Minus29=(Token)match(input,Minus,FOLLOW_Minus_in_unary_expr496);  
                    stream_Minus.add(Minus29);


                    pushFollow(FOLLOW_atom_in_unary_expr498);
                    atom30=atom();

                    state._fsp--;

                    stream_atom.add(atom30.getTree());

                    // AST REWRITE
                    // elements: atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 78:15: -> ^( UNARY_MINUS atom )
                    {
                        // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:78:18: ^( UNARY_MINUS atom )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(UNARY_MINUS, "UNARY_MINUS")
                        , root_1);

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:79:4: Not atom
                    {
                    Not31=(Token)match(input,Not,FOLLOW_Not_in_unary_expr511);  
                    stream_Not.add(Not31);


                    pushFollow(FOLLOW_atom_in_unary_expr513);
                    atom32=atom();

                    state._fsp--;

                    stream_atom.add(atom32.getTree());

                    // AST REWRITE
                    // elements: Not, atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 79:15: -> ^( Not atom )
                    {
                        // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:79:18: ^( Not atom )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_Not.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:80:4: atom
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atom_in_unary_expr528);
                    atom33=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom33.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unary_expr"


    public static class atom_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atom"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:83:1: atom : ( Str | Int | Id | '(' expr ')' -> expr );
    public final SelectParser.atom_return atom() throws RecognitionException {
        SelectParser.atom_return retval = new SelectParser.atom_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token Str34=null;
        Token Int35=null;
        Token Id36=null;
        Token char_literal37=null;
        Token char_literal39=null;
        SelectParser.expr_return expr38 =null;


        Object Str34_tree=null;
        Object Int35_tree=null;
        Object Id36_tree=null;
        Object char_literal37_tree=null;
        Object char_literal39_tree=null;
        RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
        RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:84:2: ( Str | Int | Id | '(' expr ')' -> expr )
            int alt9=4;
            switch ( input.LA(1) ) {
            case Str:
                {
                alt9=1;
                }
                break;
            case Int:
                {
                alt9=2;
                }
                break;
            case Id:
                {
                alt9=3;
                }
                break;
            case 27:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:84:4: Str
                    {
                    root_0 = (Object)adaptor.nil();


                    Str34=(Token)match(input,Str,FOLLOW_Str_in_atom539); 
                    Str34_tree = 
                    (Object)adaptor.create(Str34)
                    ;
                    adaptor.addChild(root_0, Str34_tree);


                    }
                    break;
                case 2 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:85:4: Int
                    {
                    root_0 = (Object)adaptor.nil();


                    Int35=(Token)match(input,Int,FOLLOW_Int_in_atom544); 
                    Int35_tree = 
                    (Object)adaptor.create(Int35)
                    ;
                    adaptor.addChild(root_0, Int35_tree);


                    }
                    break;
                case 3 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:86:4: Id
                    {
                    root_0 = (Object)adaptor.nil();


                    Id36=(Token)match(input,Id,FOLLOW_Id_in_atom549); 
                    Id36_tree = 
                    (Object)adaptor.create(Id36)
                    ;
                    adaptor.addChild(root_0, Id36_tree);


                    }
                    break;
                case 4 :
                    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:87:4: '(' expr ')'
                    {
                    char_literal37=(Token)match(input,27,FOLLOW_27_in_atom554);  
                    stream_27.add(char_literal37);


                    pushFollow(FOLLOW_expr_in_atom556);
                    expr38=expr();

                    state._fsp--;

                    stream_expr.add(expr38.getTree());

                    char_literal39=(Token)match(input,28,FOLLOW_28_in_atom558);  
                    stream_28.add(char_literal39);


                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 87:17: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atom"


    public static class intent_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "intent_list"
    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:90:1: intent_list : Id ( ',' Id )* -> ^( INTENT_LIST ( Id )+ ) ;
    public final SelectParser.intent_list_return intent_list() throws RecognitionException {
        SelectParser.intent_list_return retval = new SelectParser.intent_list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token Id40=null;
        Token char_literal41=null;
        Token Id42=null;

        Object Id40_tree=null;
        Object char_literal41_tree=null;
        Object Id42_tree=null;
        RewriteRuleTokenStream stream_Id=new RewriteRuleTokenStream(adaptor,"token Id");
        RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");

        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:91:2: ( Id ( ',' Id )* -> ^( INTENT_LIST ( Id )+ ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:92:3: Id ( ',' Id )*
            {
            Id40=(Token)match(input,Id,FOLLOW_Id_in_intent_list576);  
            stream_Id.add(Id40);


            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:92:6: ( ',' Id )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==29) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:92:7: ',' Id
            	    {
            	    char_literal41=(Token)match(input,29,FOLLOW_29_in_intent_list579);  
            	    stream_29.add(char_literal41);


            	    Id42=(Token)match(input,Id,FOLLOW_Id_in_intent_list581);  
            	    stream_Id.add(Id42);


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            // AST REWRITE
            // elements: Id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 92:16: -> ^( INTENT_LIST ( Id )+ )
            {
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:92:19: ^( INTENT_LIST ( Id )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(INTENT_LIST, "INTENT_LIST")
                , root_1);

                if ( !(stream_Id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_Id.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_Id.nextNode()
                    );

                }
                stream_Id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "intent_list"

    // Delegated rules


 

    public static final BitSet FOLLOW_select_stat_in_parse231 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Select_in_select_stat252 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_attr_list_in_select_stat254 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_From_in_select_stat256 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Id_in_select_stat258 = new BitSet(new long[]{0x0000000044000020L});
    public static final BitSet FOLLOW_where_stat_in_select_stat260 = new BitSet(new long[]{0x0000000040000020L});
    public static final BitSet FOLLOW_Action_Intent_in_select_stat263 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_intent_list_in_select_stat265 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_select_stat270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Id_in_attr_list300 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_attr_list303 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Id_in_attr_list305 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_Where_in_where_stat327 = new BitSet(new long[]{0x00000000090A6000L});
    public static final BitSet FOLLOW_expr_in_where_stat329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_expr_in_expr389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_expr_in_or_expr400 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_Or_in_or_expr403 = new BitSet(new long[]{0x00000000090A6000L});
    public static final BitSet FOLLOW_and_expr_in_or_expr406 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_eq_expr_in_and_expr419 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_And_in_and_expr422 = new BitSet(new long[]{0x00000000090A6000L});
    public static final BitSet FOLLOW_eq_expr_in_and_expr425 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_rel_expr_in_eq_expr438 = new BitSet(new long[]{0x0000000000040102L});
    public static final BitSet FOLLOW_set_in_eq_expr441 = new BitSet(new long[]{0x00000000090A6000L});
    public static final BitSet FOLLOW_rel_expr_in_eq_expr450 = new BitSet(new long[]{0x0000000000040102L});
    public static final BitSet FOLLOW_unary_expr_in_rel_expr463 = new BitSet(new long[]{0x0000000000018C02L});
    public static final BitSet FOLLOW_set_in_rel_expr466 = new BitSet(new long[]{0x00000000090A6000L});
    public static final BitSet FOLLOW_unary_expr_in_rel_expr483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Minus_in_unary_expr496 = new BitSet(new long[]{0x0000000009006000L});
    public static final BitSet FOLLOW_atom_in_unary_expr498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Not_in_unary_expr511 = new BitSet(new long[]{0x0000000009006000L});
    public static final BitSet FOLLOW_atom_in_unary_expr513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unary_expr528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Str_in_atom539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Int_in_atom544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Id_in_atom549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_atom554 = new BitSet(new long[]{0x00000000090A6000L});
    public static final BitSet FOLLOW_expr_in_atom556 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_atom558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Id_in_intent_list576 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_intent_list579 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Id_in_intent_list581 = new BitSet(new long[]{0x0000000020000002L});

}