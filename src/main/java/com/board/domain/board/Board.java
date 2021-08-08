package com.board.domain.board;

import com.board.domain.member.Member;
import com.board.domain.reply.Reply;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private String author;

    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    /**
     * 엔티티가 영속화되면 하이버네이트는 엔티티 안의 컬렉션을 자신의 특별한 컬렉션으로 감쌈
     *  그래서 필드에서 초기화해주는 것이 안전함
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //== 연관관계 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    //== 편의 메서드 ==//
    public void update(Board board){
        this.title = board.getTitle() != null ? board.getTitle() : this.title;
        this.content = board.getContent() != null ? board.getContent() : this.content;
    }
}
