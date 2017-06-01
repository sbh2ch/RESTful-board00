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

    public Board getBoard(Long id) {
        Board board = boardRepository.findOne(id);
        if (board == null) {
            throw new BoardNotFoundException(id);
        }

        return board;
    }

    public Board updateAccount(Long id, BoardDto.Update updateDto) {
        Board board = getBoard(id);
        board.setTitle(updateDto.getTitle());
        board.setMemo(updateDto.getMemo());
        board.setUpdated(new Date());

        return boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.delete(getBoard(id));
    }
}
