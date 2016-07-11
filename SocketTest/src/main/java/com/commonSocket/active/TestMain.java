package com.commonSocket.active;

import com.commonSocket.net.codec.message.DefaultMessageParser;

public class TestMain {

    public static void main(String[] args) {
        try {
            System.out.println(((Integer) DefaultMessageParser.getInstance().getCommandMap().get("UserLoginRequest")).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
