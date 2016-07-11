package com.commonSocket.net.codec.message;

import java.util.Map;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDefaultHandler extends DefaultHandler {

    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if ((!qName.equals("message")) && (qName.equals("entry"))) {
            try {
                int id = Integer.valueOf(attributes.getValue("id")).intValue();
                String messageName = attributes.getValue("messageName");
                DefaultMessageParser.getInstance().getCommandMap().put(messageName, Integer.valueOf(id));
            } catch (Exception e) {
                try {
                    DefaultMessageParser.getInstance().getLogger().error("initialize message parser exception", e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
