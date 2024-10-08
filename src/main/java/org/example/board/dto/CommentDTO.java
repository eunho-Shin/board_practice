package org.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.board.entity.CommentEntity;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardId){
        return CommentDTO.builder()
                .id(commentEntity.getId())
                .commentWriter(commentEntity.getCommentWriter())
                .commentContents(commentEntity.getCommentContents())
//                .boardId(commentEntity.getBoardEntity().getId())
                .boardId(boardId)
                .commentCreatedTime(commentEntity.getCreatedTime())
                .build();
    }
}
