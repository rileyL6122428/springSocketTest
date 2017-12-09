package l2k.trivia.e2e.controller;

import static l2k.trivia.utils.AdditionalAssertions.assertIsUUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

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
	
	
	@Nested
	class getRequestToBasePath {
		
		@Test
		public void returnsTriviaClient() throws Exception {
			mockMvc.perform(get("/")).andDo(print())
			.andExpect(view().name("index.html"));
		}
		
		@Test
		public void addsAuthTokenToCookies() throws Exception {
			MvcResult result = mockMvc.perform(get("/")).andReturn();
			
			Cookie triviaSessionCookie = result.getResponse().getCookie("TRIVIA_SESSION_COOKIE");
			assertNotNull(triviaSessionCookie);
			assertIsUUID(triviaSessionCookie.getValue());
		}
		
		@Test
		public void generatesDifferentAuthTokensForDifferentRequests() throws Exception {
			MvcResult resultOne = mockMvc.perform(get("/")).andReturn();
			MvcResult resultTwo = mockMvc.perform(get("/")).andReturn();
			
			Cookie authTokenOne = resultOne.getResponse().getCookie("TRIVIA_SESSION_COOKIE");
			Cookie authTokenTwo = resultTwo.getResponse().getCookie("TRIVIA_SESSION_COOKIE");
			assertThat(authTokenOne.getValue(), is(not(equalTo(authTokenTwo.getValue()))));
		}
	}
	
}
