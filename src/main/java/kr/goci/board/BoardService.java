package kr.goci.board;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kiost on 2017-06-01.
 */
@Service
@Transactional
public class BoardService {
    @Autowired
    private ModelMapper modelMapper;


}
