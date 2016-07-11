package com.commonSocket.net.codec.demux;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.commonSocket.net.IMessage;
import com.commonSocket.net.codec.message.DefaultMessageParser;
import com.commonSocket.net.util.PnpcAdler32;
import com.google.protobuf.Message;

public class DefaultMessageEncoder
        implements MessageEncoder<Object> {

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
            throws Exception {
//    Message body = (Message)message;
//    int commandId = ((Integer)DefaultMessageParser.getInstance().getCommandMap().get(body.getDescriptorForType().getName())).intValue();
////    IMessage.Head head = IMessage.Head.newBuilder().setCommandId(commandId).setSequence(1L).setCheckSum(PnpcAdler32.adler32(body.toByteArray())).build();
//    IMessage.Head head = IMessage.Head.newBuilder().setCommandId(commandId).setSequence(1L).setCheckSum(body.toByteArray().length).build();
//    IMessage.PNPCMessage msg = IMessage.PNPCMessage.newBuilder().setMsgHead(head.toByteString()).setMsgBody(body.toByteString()).build();
//    IoBuffer buf = IoBuffer.allocate(256);
//    buf.setAutoExpand(true);
//    //可以不要长度
////    buf.putInt(msg.toByteArray().length);
//    buf.put(msg.toByteArray());
//    buf.flip();
//    out.write(buf);

        //111
        Message body = (Message) message;
        int commandId = ((Integer) DefaultMessageParser.getInstance().getCommandMap().get(body.getDescriptorForType().getName())).intValue();
        IMessage.Head head = IMessage.Head.newBuilder().setCommandId(commandId).setSequence(1L).setCheckSum(PnpcAdler32.adler32(body.toByteArray())).build();
        IMessage.PNPCMessage msg = IMessage.PNPCMessage.newBuilder().setMsgHead(head.toByteString()).setMsgBody(body.toByteString()).build();
        IoBuffer buf = IoBuffer.allocate(256);
        buf.setAutoExpand(true);
        System.out.println("下发时的数据长度:" + msg.toByteArray().length);
        buf.putInt(msg.toByteArray().length);
        buf.put(msg.toByteArray());
        buf.flip();
        out.write(buf);
    }
}
