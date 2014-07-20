package com.dalsps.net.xmpp.ext;

import org.jivesoftware.smack.packet.IQ;


public class QueryPacket extends IQ {
	public static final String NAMESPACE = "urn:xmpp:dalsps.com";
    public static final String ELEMENT = "QueryPacket";
    
  
	public QueryPacket() {
    }
    
    public QueryPacket(String from, String to) {
        setTo(to);
        setFrom(from);
        setType(IQ.Type.GET);
        setPacketID(getPacketID());
    }
    
    public String getChildElementXML() {
        return "<" + ELEMENT + " xmlns=\'" + NAMESPACE + "\' />";
    }

}