package com.example.boardproject.service;

import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.BoardRequestDto;
import com.example.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        PageRequest pageRequest =
                PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC,("createdDate")));
        return boardRepository.findFirst100ByBoard(pageRequest);
    }

    public List<Board> searchFind(String keyword) {
        PageRequest pageRequest =
                PageRequest.of(0,100,Sort.by(Sort.Direction.DESC,("createdDate")));
        return boardRepository.findByTitleKeyword(keyword, pageRequest);
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
