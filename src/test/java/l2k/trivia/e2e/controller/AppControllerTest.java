package l2k.trivia.e2e.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import l2k.trivia.App;
import l2k.trivia.server.config.WebSocketConfiguration;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@ContextConfiguration(classes = { App.class })
@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class AppControllerTest {
	
	@Autowired
	private WebApplicationContext webAppContext;
	
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@Test
	public void getRequestToBasePathReturnsTriviaClient() throws Exception {
		mockMvc.perform(get("/")).andDo(print())
		.andExpect(view().name("index.html"));
	}
	
	@Test
	public void getRequestToBasePathAddsAuthTokenToCookies() throws Exception {
		MvcResult result = mockMvc.perform(get("/")).andReturn();
		
		Cookie triviaSessionCookie = result.getResponse().getCookie("TRIVIA_SESSION_COOKIE");
		assertNotNull(triviaSessionCookie);
		assertIsUUID(triviaSessionCookie.getValue());
	}
	
	private void assertIsUUID(String string) {
		try {
			UUID.fromString(string);			
		} catch (Exception exception) {
			fail("trivia session cookie value is not an instance of UUID");
		}
	}

}
