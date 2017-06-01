package kr.goci.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kiost on 2017-06-01.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class BoardControllerTest {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private BoardService service;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private BoardDto.Create boardCreaterFixture() {
        BoardDto.Create createDto = new BoardDto.Create();
        createDto.setUsername("sbh2ch");
        createDto.setTitle("hello");
        createDto.setMemo("world");

        return createDto;
    }

    @Test
    public void createAccount() throws Exception {
        mockMvc.perform(post("/api/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardCreaterFixture())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createAccount_ErrorByShortUserName() throws Exception {
        BoardDto.Create errorDto = new BoardDto.Create();
        errorDto.setTitle("a");
        errorDto.setUsername("123");
        errorDto.setMemo("err");
        mockMvc.perform(post("/api/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(errorDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAccount_ErrorByEmpty() throws Exception {
        BoardDto.Create errorDto = new BoardDto.Create();
        errorDto.setUsername("12345");
        errorDto.setMemo("err");

        mockMvc.perform(post("/api/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(errorDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}