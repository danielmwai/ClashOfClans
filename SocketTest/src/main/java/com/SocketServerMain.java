package com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import com.test.application.message.Msg.CMsg;
import com.test.application.message.Msg.CMsgHead;
import com.test.application.message.Msg.CMsgReg;

/**
 * @author Administrator
 */
public class SocketServerMain implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Socket client = null;
        try {
//			ServerSocket serversocket = new ServerSocket(8989);
            ServerSocket serversocket = new ServerSocket();
            SocketAddress sa = new InetSocketAddress("188.166.20.234", 8989);
            serversocket.bind(sa);
            System.out.println("socket 测试开始 .......");
            while (true) {
                System.out.println("等待客户端连接.......");
                client = serversocket.accept();
                DataInputStream datainputstream = null;
                DataOutputStream dataOutputStream = null;
                InputStream inputStream = client.getInputStream();
                dataOutputStream = new DataOutputStream(client.getOutputStream());
                byte[] len = new byte[1024];
                int count = inputStream.read(len);
                byte[] temp = new byte[count];
                for (int i = 0; i < count; i++) {
                    temp[i] = len[i];
                }
                //解析信息
                CMsg cmsg = CMsg.parseFrom(temp);
                //解析head和body
                CMsgHead h = CMsgHead.parseFrom(cmsg.getMsghead().getBytes());
                StringBuffer sb = new StringBuffer();
                if (h.hasMsglen()) {
                    sb.append("==len===" + h.getMsglen() + "\n");
                }
                if (h.hasMsgres()) {
                    sb.append("==res===" + h.getMsgres() + "\n");
                }
                if (h.hasMsgseq()) {
                    sb.append("==seq===" + h.getMsgseq() + "\n");
                }
                if (h.hasMsgtype()) {
                    sb.append("==type===" + h.getMsgtype() + "\n");
                }
                if (h.hasTermid()) {
                    sb.append("==Termid===" + h.getTermid() + "\n");
                }
                if (h.hasTermversion()) {
                    sb.append("==Termversion===" + h.getTermversion() + "\n");
                }
                System.out.println("接收到的head信息:" + sb.toString());
                sb = new StringBuffer();
                CMsgReg bo = CMsgReg.parseFrom(cmsg.getMsgbody().getBytes());
                if (bo.hasArea()) {
                    sb.append("==area==" + bo.getArea() + "\n");
                }
                if (bo.hasRegion()) {
                    sb.append("==Region==" + bo.getRegion() + "\n");
                }
                if (bo.hasShop()) {
                    sb.append("==shop==" + bo.getShop() + "\n");
                }
                if (bo.hasRet()) {
                    sb.append("==Ret==" + bo.getRet() + "\n");
                }
                if (bo.hasTermid()) {
                    sb.append("==Termid==" + bo.getTermid() + "\n");
                }
                System.out.println("接收到的body信息:" + sb.toString());
                sendProtobufback(dataOutputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread postthread = new Thread(new SocketServerMain());
        postthread.start();
    }

    public void sendProtobufback(DataOutputStream dataOutputStream) {
        byte[] sendInfo = this.getProtobufback();
        try {
            dataOutputStream.write(sendInfo, 0, sendInfo.length);
            dataOutputStream.flush();
            //dataOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public byte[] getProtobufback() {
        CMsgHead.Builder head = CMsgHead.newBuilder();
        head.setMsglen(15);
        head.setMsgtype(1);
        head.setTermversion(41);
        head.setMsgres(5);
        head.setTermid("11111111");
        head.setMsgseq(121);
        // body
        CMsgReg body = CMsgReg.newBuilder().setArea(22)
                .setRegion(33).setShop(44).build();
        CMsg msg = CMsg.newBuilder()
                .setMsghead(head.build().toByteString().toStringUtf8())
                .setMsgbody(body.toByteString().toStringUtf8())
                .build();
        return msg.toByteArray();
    }
}
