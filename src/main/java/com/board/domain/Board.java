package com.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    /**
     * 엔티티가 영속화되면 하이버네이트는 엔티티 안의 컬렉션을 자신의 특별한 컬렉션으로 감쌈
     *  그래서 필드에서 초기화해주는 것이 안전함
     */
    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //== 연관관계 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }
}
