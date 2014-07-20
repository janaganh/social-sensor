package com.dalsps.net.xmpp;

import android.os.Parcel;
import android.os.Parcelable;

import com.dalsps.util.Helper;

public class XmppMsg implements Parcelable {
	private StringBuilder mMessage = new StringBuilder();
    public XmppMsg() {
       
    }
    
    public XmppMsg(String msg) {
        
        mMessage.append(msg);
    }

   
   public void append(String msg) {
        mMessage.append(msg);
    }
    
    public void append(int value) {
        append(Integer.toString(value));
    }

    public void appendLine(String msg) {
        mMessage.append(msg);
        newLine();
    }
    
    public void appendLine(int value) {
        appendLine(Integer.toString(value));
    }

    public void insertLineBegin(String msg) {
        mMessage.insert(0, msg + Helper.LineSep);
    }
    
   
    public void newLine() {
        mMessage.append(Helper.LineSep);
    }
    
    /**
     * Adds the Strings in the given Arrary to the XmppMsg
     * One String per line
     * 
     * @param s
     */
    public final void addStringArray(String[] s) {
        for(String line : s) {
            this.appendLine(line);
        }
    }
    
    public XmppMsg append(XmppMsg input) {
        mMessage.append(input.mMessage);
        return this;
    }
    
    public String generateTxt() {
        String message = removeLastNewline(mMessage.toString());
        return message;
                    
    }

    
    
    public String toString() {
        return generateTxt();
    }
    
    
    /**
     * If the last char in a given string is newline,
     * return a string without the newline as last char
     * 
     * @param str
     * @return
     */
    private static String removeLastNewline(String str) {
        int strlen = str.length();
        if (strlen == 0) {
            return str;
        }
        
        int lastNewline = str.lastIndexOf("\n");
        if (strlen == lastNewline + 1) {
            return str.substring(0, strlen-1);            
        } else {
            return str;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toString());
    }
    
    public static final Parcelable.Creator<XmppMsg> CREATOR = new Parcelable.Creator<XmppMsg>() {
        public XmppMsg createFromParcel(Parcel in) {
            return new XmppMsg(in.readString());
        }

        public XmppMsg[] newArray(int size) {
            return new XmppMsg[size];
        }
    };
}
