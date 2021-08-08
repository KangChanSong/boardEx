package com.board.test.methods;

import com.board.domain.reply.Reply;

public class ReplyTestMethods {

    public static final String REPLY_CONTENT = "REPLYCONTENT";
    public static final String REPLY_WRITER = "REPLYWRITER";

    public static Reply createReply(){
        return Reply.builder()
                .writer(REPLY_WRITER)
                .content(REPLY_CONTENT)
                .build();
    }
}
