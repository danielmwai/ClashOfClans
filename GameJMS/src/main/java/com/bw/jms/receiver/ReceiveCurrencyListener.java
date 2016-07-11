package com.bw.jms.receiver;

import javax.jms.Message;
import javax.jms.MessageListener;

public class ReceiveCurrencyListener implements MessageListener {

//	private CGUserCurrencyDAO currencyDAO;
//
//	public void setCurrencyDAO(CGUserCurrencyDAO currencyDAO) {
//		this.currencyDAO = currencyDAO;
//	}
    @Override
    public void onMessage(Message message) {

//		try {
//			CGUserCurrencyVO currencyVO = (CGUserCurrencyVO) ((ObjectMessage) message).getObject();
//
//			currencyDAO.update(currencyVO);
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
    }
}
