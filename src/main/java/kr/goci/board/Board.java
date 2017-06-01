package kr.goci.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by kiost on 2017-06-01.
 */
@Data
public class Board {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String title;
    private String password;
    private String memo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date wrote;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    private int hit;
    private int deleteFlag;
    private int fileCnt;
    private List<MultipartFile> uploadFiles;
}
