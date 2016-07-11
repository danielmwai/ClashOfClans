package com.commonSocket.net.action.support;

import com.commonSocket.net.AppContext;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.ActionFactory;
import com.commonSocket.net.exception.InitializeException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DefaultActionFactory
        implements ActionFactory {

    private final Logger logger = Logger.getLogger(getClass());
    private Map<String, String> actionMap;
    private AppContext context;

    public DefaultActionFactory() {
        init();
    }

    private void init() {
        this.actionMap = new HashMap();
        this.context = AppContext.getInstance();
        InputStream input = null;
        try {
            ClassLoader loader = super.getClass().getClassLoader();
            URL url = loader.getResource(ResourceBundle.getBundle("config").getString("action"));
            input = url.openStream();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(input, new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if ((!qName.equals("action-map")) && (qName.equals("action"))) {
                        try {
                            String name = attributes.getValue("name");
                            String beanName = attributes.getValue("bean");
                            DefaultActionFactory.this.actionMap.put(name, beanName);
                        } catch (Exception e) {
                            DefaultActionFactory.this.logger.error("initialize action exception", e);
                        }

                    }

                }

            });
        } catch (Exception e) {
            throw new InitializeException("Initialize ActionFactory Exception", e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                this.logger.error("IOException while closing configuration input stream. Error was " + e.getMessage());
            }
        }
    }

    public Action createAction(int commandId) throws Exception {
        String beanName = (String) this.actionMap.get(String.valueOf(commandId));
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("createAction:" + beanName);
        }
        return (Action) this.context.getApplicationContext().getBean(beanName);
    }
}
