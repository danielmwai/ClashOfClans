package com.commonSocket.net.codec.demux;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

public class HttpParserUtil {

    private static Logger logger = Logger.getLogger(HttpParserUtil.class);
    public static final int HTTP_STATUS_SUCCESS = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final byte[] CRLF = {13, 10};
    private static final byte[] CONTENT_LENGTH = new String("Content-Length:").getBytes();
    private static final byte[] CONTENT_LENGTH_LOWER = new String("content-length:").getBytes();

    public static boolean isHttp(IoBuffer in) {
        return ((in.get(0) == 71) && (in.get(1) == 69) && (in.get(2) == 84)) || ((in.get(0) == 80) && (in.get(1) == 79) && (in.get(2) == 83) && (in.get(3) == 84));
    }

    public static boolean httpMessageComplete(IoBuffer in)
            throws Exception {
        int last = in.remaining() - 1;
        if (in.remaining() < 4) {
            return false;
        }

        if ((in.get(0) == 71) && (in.get(1) == 69) && (in.get(2) == 84)) {
            return (in.get(last) == 10) && (in.get(last - 1) == 13) && (in.get(last - 2) == 10) && (in.get(last - 3) == 13);
        }

        if ((in.get(0) == 80) && (in.get(1) == 79) && (in.get(2) == 83) && (in.get(3) == 84)) {
            int eoh = -1;
            for (int i = last; i > 2; i--) {
                if ((in.get(i) != 10) || (in.get(i - 1) != 13) || (in.get(i - 2) != 10) || (in.get(i - 3) != 13)) {
                    continue;
                }
                eoh = i + 1;
                break;
            }

            if (eoh == -1) {
                return false;
            }
            int mark = in.position();
            for (int i = 0; i < last; i++) {
                boolean found = false;
                for (int j = 0; j < CONTENT_LENGTH.length; j++) {
                    if (in.get(i + j) != CONTENT_LENGTH[j]) {
                        found = false;
                        break;
                    }
                    found = true;
                }

                if (!found) {
                    in.position(mark);
                    for (int j = 0; j < CONTENT_LENGTH_LOWER.length; j++) {
                        if (in.get(i + j) != CONTENT_LENGTH_LOWER[j]) {
                            found = false;
                            break;
                        }
                        found = true;
                    }
                }

                if (found) {
                    StringBuilder contentLength = new StringBuilder();
                    for (int j = i + CONTENT_LENGTH.length; j < last; j++) {
                        if (in.get(j) == 13) {
                            break;
                        }
                        contentLength.append(new String(new byte[]{in.get(j)}));
                    }
                    return Integer.parseInt(contentLength.toString().trim()) + eoh == in.remaining();
                }
            }
        }
        return false;
    }

    public static byte[] copyPostBytes(IoBuffer in) {
        int last = in.remaining() - 1;
        byte[] result = (byte[]) null;

        if ((in.get(0) == 71) && (in.get(1) == 69) && (in.get(2) == 84)) {
            return null;
        }

        if ((in.get(0) == 80) && (in.get(1) == 79) && (in.get(2) == 83) && (in.get(3) == 84)) {
            int eoh = -1;
            for (int i = last; i > 2; i--) {
                if ((in.get(i) != 10) || (in.get(i - 1) != 13) || (in.get(i - 2) != 10) || (in.get(i - 3) != 13)) {
                    continue;
                }
                eoh = i + 1;
                break;
            }

            if (eoh > -1) {
                in.position(eoh);
                int length = in.getInt();
                result = new byte[length];
                in.get(result);
            }
        }
        return result;
    }

    public static IoBuffer ConvertToHttpResponse(byte[] b) {
        IoBuffer buf = IoBuffer.allocate(256);
        buf.setAutoExpand(true);
        try {
            CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
            buf.putString("HTTP/1.1 ", encoder);
            buf.putString(String.valueOf(200), encoder);
            buf.putString(" OK", encoder);
            buf.put(CRLF);
            Iterator it = getHttpHeaders().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                buf.putString((String) entry.getKey(), encoder);
                buf.putString(": ", encoder);
                buf.putString((String) entry.getValue(), encoder);
                buf.put(CRLF);
            }
            buf.putString("Content-Length: ", encoder);
            buf.putString(String.valueOf(b.length), encoder);
            buf.put(CRLF);
            buf.put(CRLF);
            buf.put(b);
            if (logger.isDebugEnabled()) {
                logger.debug(buf);
            }
        } catch (CharacterCodingException ex) {
            ex.printStackTrace();
        }
        return buf.flip();
    }

    private static Map<String, String> getHttpHeaders() {
        Map headers = new HashMap();
        SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        headers.put("Cache-Control", "private");
        headers.put("Content-Type", "text/html; charset=UTF-8");
        headers.put("Connection", "keep-alive");
        headers.put("Keep-Alive", "200");
        headers.put("Date", df.format(new Date()));
        headers.put("Last-Modified", df.format(new Date()));
        return headers;
    }
}
