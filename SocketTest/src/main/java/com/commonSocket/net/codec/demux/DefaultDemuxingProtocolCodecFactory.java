package com.commonSocket.net.codec.demux;

import com.commonSocket.net.util.Report;
import com.google.protobuf.Message;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class DefaultDemuxingProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    public DefaultDemuxingProtocolCodecFactory() {
//	new Report();
        super.addMessageDecoder(DefaultMessageDecoder.class);
        super.addMessageEncoder(getMessageTypes(), DefaultMessageEncoder.class);
    }

    public Set<Class<?>> getMessageTypes() {
        Set set = new HashSet();
        set.add(Message.class);

        return Collections.unmodifiableSet(set);
    }
}
