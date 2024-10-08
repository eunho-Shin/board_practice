package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.Repository.BoardRepository;
import org.example.board.Repository.CommentRepository;
import org.example.board.dto.CommentDTO;
import org.example.board.entity.BoardEntity;
import org.example.board.entity.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository  boardRepository;

    public Long save(CommentDTO commentDTO) {
        /*부모엔티티(BoardEntity) 조회*/
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            return commentRepository.save(CommentEntity.toSaveEntity(commentDTO, boardEntity)).getId();
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long boardId){
        // select * from comment_table where board_id = ? order by id desc;
        BoardEntity boardEntity =  boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
