package kr.goci.board;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kiost on 2017-06-01.
 */
public interface BoardRepository extends JpaRepository<Board, Long>{

}
