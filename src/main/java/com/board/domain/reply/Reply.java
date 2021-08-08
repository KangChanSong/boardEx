package com.board.domain.reply;

import com.board.domain.member.Member;
import com.board.domain.board.Board;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    //게시판 글의 author 와 겹칠까봐 writer 로 햇음
    private String writer;
    private String content;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

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

    //==편의 메서드==//
    public void update(Reply reply) {
        this.content = reply.getContent() != null ? reply.getContent() : this.content;
    }
}
