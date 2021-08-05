package com.board.repository.methods;

import com.board.domain.Reply;

public class ReplyTestMethods {

    public static final String REPLY_CONTENT = "REPLYCONTENT";
    public static final String REPLY_WRITER = "REPLYWRITER";

    public static Reply createReply(){
        Reply reply = new Reply();
        reply.setContent(REPLY_CONTENT);
        reply.setWriter(REPLY_WRITER);
        return reply;
    }
}
