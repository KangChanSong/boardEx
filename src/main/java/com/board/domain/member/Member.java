package com.board.domain.member;

import com.board.domain.board.Board;
import com.board.domain.reply.Reply;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    private String username;
    private String password;

    public void update(Member member) {

        this.username = member.getUsername() == null ? this.username : member.getUsername();
        this.password = member.getPassword() == null ? this.password : member.getPassword();
    }
}
