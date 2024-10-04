package org.example.board.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private  String commentWriter;

    @Column
    private String commentContents;

    /* Board:Comment = 1: N */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;
}
