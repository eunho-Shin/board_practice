# 스프링부트 게시판 프로젝트
코딩 레시피 : https://youtu.be/YshcPPHClR4?feature=shared

## (1) 개발 환경
1. IDE: IntelliJ IDEA Community
2. Spring Boot 3.3.4
3. JDK 17
4. mysql
5. Spring Data JPA
6. Thymeleaf

## 게시판 주요기능
0. 메인 페이지
1. 글쓰기(/board/save)
2. 글목록(/board/)
3. 글조회(/board/{id})
4. 글수정(/board/update/{id})
    - 상세화면에서 수정 버튼 클릭 
    - 서버에서 해당 게시글의 정보를 가지고 수정 화면 출력 
    - 제목, 내용 수정 입력 받아서 서버로 요청 
    - 수정 처리 
5. 글삭제(/board/delete/{id})
6. 페이징처리(/board/paging)
    - /board/paging?page=2
    - /board/paging/2
    - 게시글 14
      - 한페이지에 5개씩 => 3개
      - 한페이지에 3개씩 => 5개
7. 파일(이미지)첨부하기 
   - 단일 파일 첨부
   - 다중 파일 첨부
   - 파일 첨부와 관련하여 추가될 부분들  
     - save.html  
     - BoardDTO  
     - BoardService.save()  
     - BoardEntity
     - BoardFileEntity, BoardFileRepository 추가
     - detail.html
8. 댓글 처리하기
    - 글 상세 페이지에서 댓글 입력
    - 상세조회 할 때 기존에 작성된 댓글 목록이 보임
    - 댓글을 입력하면 기존 댓글 목록에 새로 작성한 댓글 추가
    - 댓글용 테이블 필요!

## 게시판 결과

0. 메인페이지 </br>
<img width="295" alt="K-001" src="https://github.com/user-attachments/assets/a1bd0f4e-55f5-45b1-98c7-d7d94cbf58d2"> </br>
1. 글쓰기(/board/save) </br>
<img width="295" alt="K-001" src="https://github.com/user-attachments/assets/360ecb89-1b34-4cd5-b929-f29898130dd0"> </br>
2. 글목록(페이징X) </br>
<img width="295" alt="K-001" src="https://github.com/user-attachments/assets/076a9085-0a55-4bac-ad7d-a6867cffa64a"> </br>
3. 글조회 </br>
<img width="400" alt="K-001" src="https://github.com/user-attachments/assets/ea2e6517-9fce-417d-80f6-71950c4237bd"> </br>
4. 글수정 </br>
<img width="599" alt="K-010" src="https://github.com/user-attachments/assets/c50ec9cc-7063-400d-ae38-b673ec08f00b"> </br>
5. 글삭제 </br>
<img width="700" alt="K-013" src="https://github.com/user-attachments/assets/e767af39-7b5b-48fe-84af-26efd469d2da"> </br>
6. 페이징처리 </br>
<img width="295" alt="K-003" src="https://github.com/user-attachments/assets/09551b13-8c79-4760-b5ca-a37a1c38467f"> </br>
7. 파일(이미지)첨부하기 </br> 
<img width="400" alt="K-001" src="https://github.com/user-attachments/assets/ea2e6517-9fce-417d-80f6-71950c4237bd"> </br>
8. 댓글 처리하기 </br>
<img width="600" alt="K-008" src="https://github.com/user-attachments/assets/511ccdfa-df2b-43f3-afb6-47d18dfcf54b">
