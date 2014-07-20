package com.dalsps.net.xmpp.ext;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;

import org.xmlpull.v1.XmlPullParser;

public class QueryPacketProvider implements IQProvider {
    
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        return new QueryPacket();
    }

}
