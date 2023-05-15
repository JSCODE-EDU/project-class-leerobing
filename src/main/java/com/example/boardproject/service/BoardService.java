package com.example.boardproject.service;

import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.BoardRequestDto;
import com.example.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    @Transactional
    public Board write(BoardRequestDto boardRequestDto) {

        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .build();

        boardRepository.save(board);
        return board;
    }

    public Board find(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }


    @Transactional
    public Board modify(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        board.modify(boardRequestDto.getTitle(), boardRequestDto.getContent());
        return board;
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
