package com.board.domain.reply;

import com.board.domain.Member;
import com.board.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reply {

    @Id @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    //게시판 글의 author 와 겹칠까봐 writer 로 햇음
    private String writer;
    private String content;
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //== 연관관계 메서드 ==//
    public void setBoard(Board board){
        this.board = board;
        board.getReplies().add(this);
    }

    public void setMember(Member member){
        this.member = member;
        member.getReplies().add(this);
    }
}
