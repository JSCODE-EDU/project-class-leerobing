package com.example.boardproject.repository;

import com.example.boardproject.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select p from Board p where p.title like %:keyword%")
    List<Board> findByTitleKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("select p from Board  p")
    List<Board> findFirst100ByBoard(Pageable pageable);
}
