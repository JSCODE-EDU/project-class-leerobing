package com.example.boardproject;

import com.example.boardproject.domain.Board;
import com.example.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class BoardProjectApplicationTests {


    @Autowired
    private BoardRepository boardRepository;

    @Nested
    class CrudTest{
        @Test
        public void readTest() {
            long mono = 15L;

            Optional<Board> result = boardRepository.findById(mono);
            if (result.isPresent()) {
                Board board = result.get();
                System.out.println(board);
            }
        }
    }
}
