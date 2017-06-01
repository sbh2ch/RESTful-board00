package kr.goci.board;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by kiost on 2017-06-01.
 */
public class BoardDto {
    @Data
    public static class Create {
        @NotEmpty
        @Size(min = 5)
        private String username;
        @NotEmpty
        private String title;
        @NotEmpty
        private String memo;
    }

    @Data
    public static class Summary {
        private Long id;
        private String username;
        private String title;
        private int hit;
        private Date updated;
    }

    @Data
    public static class Detail {
        private Long id;
        private String username;
        private String title;
        private String memo;
        private int hit;
        private Date updated;
    }

    @Data
    public static class Update {
        @NotEmpty
        @Size(min = 5)
        private Long id;
        @NotEmpty
        private String title;
        @NotEmpty
        private String memo;
    }
}
