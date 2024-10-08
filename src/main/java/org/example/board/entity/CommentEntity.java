package org.example.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.board.dto.CommentDTO;

@Entity
@Data
@Table(name="comment_table")
public class CommentEntity extends BaseEntity {
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

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
