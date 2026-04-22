package com.example.yogi;
import static org.junit.jupiter.api.Assertions.*;

import com.example.yogi.board.entity.Board;
import com.example.yogi.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class YogiApplicationTests {
	@MockBean
	BoardService boardService;
	@Test
	void YogiApplicationTests() {
		String str = "abcde";
		assertEquals("cd", str.substring(2,4));

		Board board = boardService.getBoardDetail(1);
		assertEquals(1, board.getReadcount());
	}

}
