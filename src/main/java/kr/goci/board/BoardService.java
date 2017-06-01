package kr.goci.board;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by kiost on 2017-06-01.
 */
@Service
@Transactional
@Slf4j
public class BoardService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(BoardDto.Create createDto) {
        Board board = modelMapper.map(createDto, Board.class);
        Date now = new Date();
        board.setWrote(now);
        board.setUpdated(now);
        board.setHit(0);
        board.setDeleteFlag(0);
        log.info("created board is {}", board);

        return boardRepository.save(board);
    }
}
