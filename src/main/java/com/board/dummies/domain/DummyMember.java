package com.board.dummies.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class DummyMember {

    @Id @GeneratedValue
    private Long id;

    private String username;
}
