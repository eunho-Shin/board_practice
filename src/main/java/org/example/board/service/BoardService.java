package org.example.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.board.Repository.BoardFileRepository;
import org.example.board.Repository.BoardRepository;
import org.example.board.dto.BoardDTO;
import org.example.board.entity.BoardEntity;
import org.example.board.entity.BoardFileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        // 파일 첨부 여부에 따라 로직 분리, private MultipartFile boardFiles
        if(boardDTO.getBoardFiles().isEmpty()) {
            // 첨부 파일 없음
            boardRepository.save(BoardEntity.toSaveEntity(boardDTO));
        } else {
            // 첨부 파일 있음
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일 이름 가져옴
                3. 서버 저장용 이름을 만듦
                => 15618384313_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. board_table에 해당 데이터 save 처리
                7. board_file_table에 해당 데이터 save 처리
            */
            // 해당 BoardEntity를 사용할 수 없는 이유는 저장되기 전 이므로 Id가 없다.
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long savedId = boardRepository.save(boardEntity).getId(); // 저장한 후 반환값 Id 추출
            BoardEntity board = boardRepository.findById(savedId).get();

            for(MultipartFile boardFile : boardDTO.getBoardFiles()) {
//                MultipartFile boardFile = boardDTO.getBoardFiles(); // 1
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                String savePath = "E:/springboot_img/" + storedFileName;
                boardFile.transferTo(new File(savePath));

                BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFileName, storedFileName);
                boardFileRepository.save(boardFileEntity);
            }
        }
    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public BoardDTO findById(Long id){
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return BoardDTO.toBoardDTO(boardEntity);
        } else {
            return null;
        }
    }

    /* 조회수 1 증가 */
    @Transactional
    public void updateHits(Long id){
        boardRepository.updateHits(id);
    }

    public BoardDTO update(BoardDTO boardDTO) {
        boardRepository.save(BoardEntity.toUpdateEntity(boardDTO));
        return findById(boardDTO.getId());
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 개수
        // page 위치에 있는 값은 0부터 시작
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page,pageLimit, Sort.by(Sort.Direction.DESC,"id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
    }
}
