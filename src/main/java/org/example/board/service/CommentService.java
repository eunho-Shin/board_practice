package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.Repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;


}
