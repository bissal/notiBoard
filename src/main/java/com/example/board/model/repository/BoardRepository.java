package com.example.board.model.repository;

import com.example.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bissal on 16/01/2020.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
