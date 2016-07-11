package com.commonSocket.net.codec.demux;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.commonSocket.net.IMessage;
import com.commonSocket.net.util.PnpcAdler32;

public class DefaultMessageDecoder
        implements MessageDecoder {

    private Logger logger = Logger.getLogger(getClass());

    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
        int remaining = in.remaining();

        if (remaining <= 0) {
            return MessageDecoderResult.NOT_OK;
        }
        if (remaining < 4) {
            return MessageDecoderResult.NEED_DATA;
        }

        int length = in.getInt();
        if (remaining - 4 < length) {
            return MessageDecoderResult.NEED_DATA;
        }
        try {
            byte[] data = new byte[length];
            in.get(data);
            IMessage.PNPCMessage message = IMessage.PNPCMessage.parseFrom(data);
            IMessage.Head head = IMessage.Head.parseFrom(message.getMsgHead());
            if (head.getCommandId() == 2000) {
                System.out.println("EnterLobbyRequest!");
            }
            if ((head.getCommandId() == 20111102)
                    && (head.getSequence() == 618675487293078713L)) {
                System.exit(0);
            }
            if ((head.getCommandId() <= 0) || (head.getCommandId() > 99999)) {
                return MessageDecoderResult.NOT_OK;
            }
            session.setAttribute("http", Boolean.valueOf(head.getIsHttp()));
            //用于检测消息头里面给的长度和 消息体里面的长度是否相等 因客户端和服务器端问题暂时不在比较
            byte[] b = message.getMsgBody().toByteArray();
            long CheckSum = PnpcAdler32.adler32(b);
            if (head.getCheckSum() != CheckSum) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Head's checkSum[" + head.getCheckSum() + "] != data's checkSum[" + CheckSum + "] error!");
                }
                return MessageDecoderResult.NOT_OK;
            }
            return MessageDecoderResult.OK;
        } catch (IOException ex) {
            return MessageDecoderResult.NOT_OK;
        } catch (Exception e) {
        }
        return MessageDecoderResult.NOT_OK;
    }
    //old没有验证长度的

    public MessageDecoderResult xdecodableXXXX(IoSession session, IoBuffer in) {
        int remaining = in.remaining();

        if (remaining < 4) {
            return MessageDecoderResult.NEED_DATA;
        }
        int length = in.getInt();
        if (remaining - 4 < length) {
            return MessageDecoderResult.NEED_DATA;
        }
//    int length =remaining;
        //暂时先把获取字节流长度的in.get()注释掉 length可以通过in.remaining() 或者in.limit() 来获取  开始
//    int length = in.get();
//    if (remaining < length) {
//      return MessageDecoderResult.NOT_OK;
//    }
        //暂时先把获取字节流长度的in.get()注释掉 length可以通过in.remaining() 或者in.limit() 来获取 结束
        try {
            //11111111111
//    	 byte[] data = new byte[27];
//         in.get(data);
//         IMessage.PNPCMessage message = IMessage.PNPCMessage.parseFrom(data);
//         IMessage.Head head = IMessage.Head.parseFrom(message.getMsgHead());
//   		UserLoginRequest response= UserLoginRequest.parseFrom(message.getMsgBody());
//   	StringBuffer sb = new StringBuffer();
//   	if (head.hasCommandId())
//   	sb.append("==CommandId===" +head.getCommandId() + "\n");
//   	if (head.hasIsHttp())
//   	sb.append("==IsHttp===" +head.getIsHttp() + "\n");
//   	if (head.hasSequence())
//   	sb.append("==seq===" + head.getSequence() + "\n");
//   	if (head.hasCheckSum())
//   	sb.append("==CheckSum===" + head.getCheckSum() + "\n");
//   	if(response.hasUserName()){
//       sb.append("==result===" + response.getUserName() + "\n");
//   	}
//   	if(response.hasPassword()){
//   	    sb.append("==info===" + response.getPassword() + "\n");
//   		}	
//   		System.out.println("接收到的body信息:"+sb.toString());
            //11111111111111
            byte[] data = new byte[length];
            in.get(data);
            IMessage.PNPCMessage message = IMessage.PNPCMessage.parseFrom(data);
            IMessage.Head head = IMessage.Head.parseFrom(message.getMsgHead());
            if ((head.getCommandId() == 20111102)
                    && (head.getSequence() == 618675487293078713L)) {
                System.exit(0);
            }
            if ((head.getCommandId() <= 0) || (head.getCommandId() > 99999)) {
                return MessageDecoderResult.NOT_OK;
            }
            session.setAttribute("http", Boolean.valueOf(head.getIsHttp()));
            //用于检测消息头里面给的长度和 消息体里面的长度是否相等 因客户端和服务器端问题暂时不在比较
//      byte[] b = message.getMsgBody().toByteArray();
//      long CheckSum = PnpcAdler32.adler32(b);
//      if (head.getCheckSum() != CheckSum) {
//        if (this.logger.isDebugEnabled()) {
//          this.logger.debug("Head's checkSum[" + head.getCheckSum() + "] != data's checkSum[" + CheckSum + "] error!");
//        }
//        return MessageDecoderResult.NOT_OK;
//      }
//      return MessageDecoderResult.OK;
        } catch (IOException ex) {
            return MessageDecoderResult.NOT_OK;
        } catch (Exception e) {
        }
        return MessageDecoderResult.NOT_OK;
    }

    public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
            throws Exception {
        try {
//        int remaining = in.remaining();
//	  byte[] data = new byte[remaining];
//      in.get(data);
//      IMessage.PNPCMessage message = IMessage.PNPCMessage.parseFrom(data);
//      out.write(message);
            byte[] data = new byte[in.getInt()];
            in.get(data);
            IMessage.PNPCMessage message = IMessage.PNPCMessage.parseFrom(data);
            out.write(message);
            return MessageDecoderResult.OK;
        } catch (Exception e) {
        }
        return MessageDecoderResult.NOT_OK;
    }

    public void finishDecode(IoSession iosession, ProtocolDecoderOutput protocoldecoderoutput)
            throws Exception {
    }
}
