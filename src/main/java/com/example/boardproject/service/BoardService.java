package com.example.boardproject.service;

import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.BoardRequestDto;
import com.example.boardproject.dto.ModifyRequestDto;
import com.example.boardproject.dto.SaveRequestDto;
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
    public Board write(SaveRequestDto saveRequestDto) {

            Board board = Board.builder()
                    .title(saveRequestDto.getTitle())
                    .content(saveRequestDto.getContent())
                    .build();
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public Board find(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }

    @Transactional
    public List<Board> findAll() {
        PageRequest pageRequest =
                PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC,("createdDate")));
        return boardRepository.findFirst100ByBoard(pageRequest);
    }

    @Transactional
    public List<Board> searchFind(String keyword) {
        PageRequest pageRequest =
                PageRequest.of(0,100,Sort.by(Sort.Direction.DESC,("createdDate")));
        return boardRepository.findByTitleKeyword(keyword, pageRequest);
    }

    @Transactional
    public Board modify(Long id, ModifyRequestDto modifyRequestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        board.modify(modifyRequestDto.getTitle(), modifyRequestDto.getContent());
        return board;
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        boardRepository.deleteById(id);
    }

}
