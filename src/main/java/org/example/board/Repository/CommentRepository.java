package org.example.board.Repository;

import org.example.board.dto.CommentDTO;
import org.example.board.entity.BoardEntity;
import org.example.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // select * from comment_table where board_id = ? order by id desc;

    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
