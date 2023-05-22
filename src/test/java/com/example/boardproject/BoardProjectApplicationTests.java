package com.example.boardproject;

import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.BoardResponseDto;
import com.example.boardproject.dto.SaveRequestDto;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static  org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class BoardProjectApplicationTests {


    @Autowired
    private BoardRepository boardRepository;


    @AfterEach
    public void cleanUp() {
        boardRepository.deleteAll();
    }
    @Test
    void writeTest() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        boardRepository.save(Board.builder().title(title).content(content).build());
        List<Board> boardList = boardRepository.findAll();
        Board board = boardList.get(0);
        if (board.getTitle() == title) {
            System.out.println("sueccss");
        }

        }

}
