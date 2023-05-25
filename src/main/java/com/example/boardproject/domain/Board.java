package com.example.boardproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;



    @Builder
    public  Board(String title, String content,LocalDateTime createdDate,Member member) {
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.member = member;
    }

    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
