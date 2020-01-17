package com.example.board.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by bissal on 16/01/2020.
 */
@Getter
@NoArgsConstructor
@Entity
public class Board extends TimeStamp {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
