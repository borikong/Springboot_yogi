package com.example.yogi.board.repository;

import com.example.yogi.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board,Integer> {

    @Query("SELECT b FROM Board b WHERE CAST(b.no AS string) LIKE CONCAT('%', :keyword, '%')" +
            "       OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "       OR LOWER(b.writer) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "       OR LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Board> findByAllContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM Board b WHERE CAST(b.no AS string) LIKE CONCAT('%', :keyword, '%')")
    Page<Board> findByNoContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM Board b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Board> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM Board b WHERE LOWER(b.writer) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Board> findByWriterContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM Board b WHERE LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Board> findByContentContaining(@Param("keyword") String keyword, Pageable pageable);
}
