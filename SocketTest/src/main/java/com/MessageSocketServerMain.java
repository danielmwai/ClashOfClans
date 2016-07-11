package com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import com.commonSocket.net.IMessage.Head;
import com.commonSocket.net.IMessage.PNPCMessage;
import com.test.application.message.Msg.CMsg;
import com.test.application.message.Msg.CMsgHead;
import com.test.application.message.Msg.CMsgReg;
import com.test.application.message.UserLoginInfo.UserLoginResponse;

/**
 * @author Administrator
 */
public class MessageSocketServerMain implements Runnable {

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
                PNPCMessage g = PNPCMessage.parseFrom(temp);
                Head mhead = Head.parseFrom(g.getMsgHead());
                UserLoginResponse response = UserLoginResponse.parseFrom(g.getMsgBody());
                StringBuffer sb = new StringBuffer();
                if (mhead.hasCommandId()) {
                    sb.append("==CommandId===" + mhead.getCommandId() + "\n");
                }
                if (mhead.hasIsHttp()) {
                    sb.append("==IsHttp===" + mhead.getIsHttp() + "\n");
                }
                if (mhead.hasSequence()) {
                    sb.append("==seq===" + mhead.getSequence() + "\n");
                }
                if (mhead.hasCheckSum()) {
                    sb.append("==CheckSum===" + mhead.getCheckSum() + "\n");
                }
                if (response.hasResult()) {
                    sb.append("==result===" + response.getResult() + "\n");
                }
                if (response.hasInfo()) {
                    sb.append("==info===" + response.getInfo() + "\n");
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
        Thread postthread = new Thread(new MessageSocketServerMain());
        postthread.start();
    }

    /**
     * @param dataOutputStream ���ͽ��յ�����Ϣ
     */
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
        UserLoginResponse response = UserLoginResponse.newBuilder().setResult(1).setInfo("成功").build();
        Head mhead = Head.newBuilder().setCommandId(61001).setCheckSum(response.toByteArray().length).setSequence(1).setIsHttp(false).build();
        PNPCMessage cmessage = PNPCMessage.newBuilder().setMsgHead(mhead.toByteString()).setMsgBody(response.toByteString()).build();
        return cmessage.toByteArray();
    }
}
