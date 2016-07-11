package com.commonSocket.net.notice.support;

import com.commonSocket.net.AppContext;
import com.commonSocket.net.notice.Notice;
import com.commonSocket.net.notice.NoticeFactory;

public class DefaultNoticeFactory
        implements NoticeFactory {

    public Notice creatNotice(String noticeId)
            throws Exception {
        return (Notice) AppContext.getInstance().getApplicationContext().getBean(noticeId);
    }
}
