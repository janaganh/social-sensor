// $ANTLR 3.4 E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g 2013-02-10 22:05:22

  package com.dalsps.query;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SelectLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public SelectLexer() {} 
    public SelectLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SelectLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g"; }

    // $ANTLR start "Action_Intent"
    public final void mAction_Intent() throws RecognitionException {
        try {
            int _type = Action_Intent;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:6:15: ( 'action_intents' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:6:17: 'action_intents'
            {
            match("action_intents"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Action_Intent"

    // $ANTLR start "And"
    public final void mAnd() throws RecognitionException {
        try {
            int _type = And;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:7:5: ( 'and' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:7:7: 'and'
            {
            match("and"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "And"

    // $ANTLR start "Eq"
    public final void mEq() throws RecognitionException {
        try {
            int _type = Eq;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:8:4: ( '=' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:8:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Eq"

    // $ANTLR start "From"
    public final void mFrom() throws RecognitionException {
        try {
            int _type = From;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:9:6: ( 'from' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:9:8: 'from'
            {
            match("from"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "From"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:10:4: ( '>' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:10:6: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "GTEq"
    public final void mGTEq() throws RecognitionException {
        try {
            int _type = GTEq;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:11:6: ( '>=' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:11:8: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GTEq"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:12:4: ( '<' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:12:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LTEq"
    public final void mLTEq() throws RecognitionException {
        try {
            int _type = LTEq;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:13:6: ( '<=' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:13:8: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LTEq"

    // $ANTLR start "Minus"
    public final void mMinus() throws RecognitionException {
        try {
            int _type = Minus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:14:7: ( '-' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:14:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Minus"

    // $ANTLR start "NEq"
    public final void mNEq() throws RecognitionException {
        try {
            int _type = NEq;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:15:5: ( '!=' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:15:7: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEq"

    // $ANTLR start "Not"
    public final void mNot() throws RecognitionException {
        try {
            int _type = Not;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:16:5: ( '!' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:16:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Not"

    // $ANTLR start "Or"
    public final void mOr() throws RecognitionException {
        try {
            int _type = Or;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:17:4: ( 'or' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:17:6: 'or'
            {
            match("or"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Or"

    // $ANTLR start "Select"
    public final void mSelect() throws RecognitionException {
        try {
            int _type = Select;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:18:8: ( 'select' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:18:10: 'select'
            {
            match("select"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Select"

    // $ANTLR start "Where"
    public final void mWhere() throws RecognitionException {
        try {
            int _type = Where;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:19:7: ( 'where' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:19:9: 'where'
            {
            match("where"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Where"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:20:7: ( '(' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:20:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:21:7: ( ')' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:21:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:22:7: ( ',' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:22:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:23:7: ( ';' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:23:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "Id"
    public final void mId() throws RecognitionException {
        try {
            int _type = Id;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:98:7: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '\\.' | Digit )* )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:98:9: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '\\.' | Digit )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:98:37: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '\\.' | Digit )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='.'||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Id"

    // $ANTLR start "Str"
    public final void mStr() throws RecognitionException {
        try {
            int _type = Str;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:100:7: ( '\\'' ( '\\'\\'' |~ ( '\\'' | '\\r' | '\\n' ) )* '\\'' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:100:9: '\\'' ( '\\'\\'' |~ ( '\\'' | '\\r' | '\\n' ) )* '\\''
            {
            match('\''); 

            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:100:14: ( '\\'\\'' |~ ( '\\'' | '\\r' | '\\n' ) )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\'') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='\'') ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0 >= '\u0000' && LA2_0 <= '\t')||(LA2_0 >= '\u000B' && LA2_0 <= '\f')||(LA2_0 >= '\u000E' && LA2_0 <= '&')||(LA2_0 >= '(' && LA2_0 <= '\uFFFF')) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:100:15: '\\'\\''
            	    {
            	    match("''"); 



            	    }
            	    break;
            	case 2 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:100:24: ~ ( '\\'' | '\\r' | '\\n' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            match('\''); 


                     // strip the surrounding quotes and replace '' with '
                     setText(getText().substring(1, getText().length() - 1).replace("''", "'"));
                    

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Str"

    // $ANTLR start "Int"
    public final void mInt() throws RecognitionException {
        try {
            int _type = Int;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:106:7: ( ( Digit )+ )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:106:9: ( Digit )+
            {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:106:9: ( Digit )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Int"

    // $ANTLR start "Space"
    public final void mSpace() throws RecognitionException {
        try {
            int _type = Space;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:108:7: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:108:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Space"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:110:16: ( '0' .. '9' )
            // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Digit"

    public void mTokens() throws RecognitionException {
        // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:8: ( Action_Intent | And | Eq | From | GT | GTEq | LT | LTEq | Minus | NEq | Not | Or | Select | Where | T__27 | T__28 | T__29 | T__30 | Id | Str | Int | Space )
        int alt4=22;
        switch ( input.LA(1) ) {
        case 'a':
            {
            switch ( input.LA(2) ) {
            case 'c':
                {
                int LA4_19 = input.LA(3);

                if ( (LA4_19=='t') ) {
                    int LA4_31 = input.LA(4);

                    if ( (LA4_31=='i') ) {
                        int LA4_37 = input.LA(5);

                        if ( (LA4_37=='o') ) {
                            int LA4_42 = input.LA(6);

                            if ( (LA4_42=='n') ) {
                                int LA4_46 = input.LA(7);

                                if ( (LA4_46=='_') ) {
                                    int LA4_49 = input.LA(8);

                                    if ( (LA4_49=='i') ) {
                                        int LA4_51 = input.LA(9);

                                        if ( (LA4_51=='n') ) {
                                            int LA4_52 = input.LA(10);

                                            if ( (LA4_52=='t') ) {
                                                int LA4_53 = input.LA(11);

                                                if ( (LA4_53=='e') ) {
                                                    int LA4_54 = input.LA(12);

                                                    if ( (LA4_54=='n') ) {
                                                        int LA4_55 = input.LA(13);

                                                        if ( (LA4_55=='t') ) {
                                                            int LA4_56 = input.LA(14);

                                                            if ( (LA4_56=='s') ) {
                                                                int LA4_57 = input.LA(15);

                                                                if ( (LA4_57=='.'||(LA4_57 >= '0' && LA4_57 <= '9')||(LA4_57 >= 'A' && LA4_57 <= 'Z')||LA4_57=='_'||(LA4_57 >= 'a' && LA4_57 <= 'z')) ) {
                                                                    alt4=19;
                                                                }
                                                                else {
                                                                    alt4=1;
                                                                }
                                                            }
                                                            else {
                                                                alt4=19;
                                                            }
                                                        }
                                                        else {
                                                            alt4=19;
                                                        }
                                                    }
                                                    else {
                                                        alt4=19;
                                                    }
                                                }
                                                else {
                                                    alt4=19;
                                                }
                                            }
                                            else {
                                                alt4=19;
                                            }
                                        }
                                        else {
                                            alt4=19;
                                        }
                                    }
                                    else {
                                        alt4=19;
                                    }
                                }
                                else {
                                    alt4=19;
                                }
                            }
                            else {
                                alt4=19;
                            }
                        }
                        else {
                            alt4=19;
                        }
                    }
                    else {
                        alt4=19;
                    }
                }
                else {
                    alt4=19;
                }
                }
                break;
            case 'n':
                {
                int LA4_20 = input.LA(3);

                if ( (LA4_20=='d') ) {
                    int LA4_32 = input.LA(4);

                    if ( (LA4_32=='.'||(LA4_32 >= '0' && LA4_32 <= '9')||(LA4_32 >= 'A' && LA4_32 <= 'Z')||LA4_32=='_'||(LA4_32 >= 'a' && LA4_32 <= 'z')) ) {
                        alt4=19;
                    }
                    else {
                        alt4=2;
                    }
                }
                else {
                    alt4=19;
                }
                }
                break;
            default:
                alt4=19;
            }

            }
            break;
        case '=':
            {
            alt4=3;
            }
            break;
        case 'f':
            {
            int LA4_3 = input.LA(2);

            if ( (LA4_3=='r') ) {
                int LA4_21 = input.LA(3);

                if ( (LA4_21=='o') ) {
                    int LA4_33 = input.LA(4);

                    if ( (LA4_33=='m') ) {
                        int LA4_39 = input.LA(5);

                        if ( (LA4_39=='.'||(LA4_39 >= '0' && LA4_39 <= '9')||(LA4_39 >= 'A' && LA4_39 <= 'Z')||LA4_39=='_'||(LA4_39 >= 'a' && LA4_39 <= 'z')) ) {
                            alt4=19;
                        }
                        else {
                            alt4=4;
                        }
                    }
                    else {
                        alt4=19;
                    }
                }
                else {
                    alt4=19;
                }
            }
            else {
                alt4=19;
            }
            }
            break;
        case '>':
            {
            int LA4_4 = input.LA(2);

            if ( (LA4_4=='=') ) {
                alt4=6;
            }
            else {
                alt4=5;
            }
            }
            break;
        case '<':
            {
            int LA4_5 = input.LA(2);

            if ( (LA4_5=='=') ) {
                alt4=8;
            }
            else {
                alt4=7;
            }
            }
            break;
        case '-':
            {
            alt4=9;
            }
            break;
        case '!':
            {
            int LA4_7 = input.LA(2);

            if ( (LA4_7=='=') ) {
                alt4=10;
            }
            else {
                alt4=11;
            }
            }
            break;
        case 'o':
            {
            int LA4_8 = input.LA(2);

            if ( (LA4_8=='r') ) {
                int LA4_28 = input.LA(3);

                if ( (LA4_28=='.'||(LA4_28 >= '0' && LA4_28 <= '9')||(LA4_28 >= 'A' && LA4_28 <= 'Z')||LA4_28=='_'||(LA4_28 >= 'a' && LA4_28 <= 'z')) ) {
                    alt4=19;
                }
                else {
                    alt4=12;
                }
            }
            else {
                alt4=19;
            }
            }
            break;
        case 's':
            {
            int LA4_9 = input.LA(2);

            if ( (LA4_9=='e') ) {
                int LA4_29 = input.LA(3);

                if ( (LA4_29=='l') ) {
                    int LA4_35 = input.LA(4);

                    if ( (LA4_35=='e') ) {
                        int LA4_40 = input.LA(5);

                        if ( (LA4_40=='c') ) {
                            int LA4_44 = input.LA(6);

                            if ( (LA4_44=='t') ) {
                                int LA4_47 = input.LA(7);

                                if ( (LA4_47=='.'||(LA4_47 >= '0' && LA4_47 <= '9')||(LA4_47 >= 'A' && LA4_47 <= 'Z')||LA4_47=='_'||(LA4_47 >= 'a' && LA4_47 <= 'z')) ) {
                                    alt4=19;
                                }
                                else {
                                    alt4=13;
                                }
                            }
                            else {
                                alt4=19;
                            }
                        }
                        else {
                            alt4=19;
                        }
                    }
                    else {
                        alt4=19;
                    }
                }
                else {
                    alt4=19;
                }
            }
            else {
                alt4=19;
            }
            }
            break;
        case 'w':
            {
            int LA4_10 = input.LA(2);

            if ( (LA4_10=='h') ) {
                int LA4_30 = input.LA(3);

                if ( (LA4_30=='e') ) {
                    int LA4_36 = input.LA(4);

                    if ( (LA4_36=='r') ) {
                        int LA4_41 = input.LA(5);

                        if ( (LA4_41=='e') ) {
                            int LA4_45 = input.LA(6);

                            if ( (LA4_45=='.'||(LA4_45 >= '0' && LA4_45 <= '9')||(LA4_45 >= 'A' && LA4_45 <= 'Z')||LA4_45=='_'||(LA4_45 >= 'a' && LA4_45 <= 'z')) ) {
                                alt4=19;
                            }
                            else {
                                alt4=14;
                            }
                        }
                        else {
                            alt4=19;
                        }
                    }
                    else {
                        alt4=19;
                    }
                }
                else {
                    alt4=19;
                }
            }
            else {
                alt4=19;
            }
            }
            break;
        case '(':
            {
            alt4=15;
            }
            break;
        case ')':
            {
            alt4=16;
            }
            break;
        case ',':
            {
            alt4=17;
            }
            break;
        case ';':
            {
            alt4=18;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '_':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'p':
        case 'q':
        case 'r':
        case 't':
        case 'u':
        case 'v':
        case 'x':
        case 'y':
        case 'z':
            {
            alt4=19;
            }
            break;
        case '\'':
            {
            alt4=20;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt4=21;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt4=22;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 4, 0, input);

            throw nvae;

        }

        switch (alt4) {
            case 1 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:10: Action_Intent
                {
                mAction_Intent(); 


                }
                break;
            case 2 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:24: And
                {
                mAnd(); 


                }
                break;
            case 3 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:28: Eq
                {
                mEq(); 


                }
                break;
            case 4 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:31: From
                {
                mFrom(); 


                }
                break;
            case 5 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:36: GT
                {
                mGT(); 


                }
                break;
            case 6 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:39: GTEq
                {
                mGTEq(); 


                }
                break;
            case 7 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:44: LT
                {
                mLT(); 


                }
                break;
            case 8 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:47: LTEq
                {
                mLTEq(); 


                }
                break;
            case 9 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:52: Minus
                {
                mMinus(); 


                }
                break;
            case 10 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:58: NEq
                {
                mNEq(); 


                }
                break;
            case 11 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:62: Not
                {
                mNot(); 


                }
                break;
            case 12 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:66: Or
                {
                mOr(); 


                }
                break;
            case 13 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:69: Select
                {
                mSelect(); 


                }
                break;
            case 14 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:76: Where
                {
                mWhere(); 


                }
                break;
            case 15 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:82: T__27
                {
                mT__27(); 


                }
                break;
            case 16 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:88: T__28
                {
                mT__28(); 


                }
                break;
            case 17 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:94: T__29
                {
                mT__29(); 


                }
                break;
            case 18 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:100: T__30
                {
                mT__30(); 


                }
                break;
            case 19 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:106: Id
                {
                mId(); 


                }
                break;
            case 20 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:109: Str
                {
                mStr(); 


                }
                break;
            case 21 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:113: Int
                {
                mInt(); 


                }
                break;
            case 22 :
                // E:\\MSC_PROJECT\\src\\dalsps-project\\src\\com\\dalsps\\query\\Select.g:1:117: Space
                {
                mSpace(); 


                }
                break;

        }

    }


 

}