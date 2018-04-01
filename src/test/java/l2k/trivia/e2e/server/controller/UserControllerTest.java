package l2k.trivia.e2e.server.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import l2k.trivia.App;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.NameGenerator;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@SpringBootTest
@ContextConfiguration(classes = { App.class })
@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

	@Autowired
	private WebApplicationContext webAppContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();		
	}

	@Nested
	class GetUser {

		@Test
		public void returns404WhenSessionIdNotRegistered() throws Exception {
			Cookie sessionCookie = new Cookie("TRIVIA_SESSION_COOKIE", UUID.randomUUID().toString());

			mockMvc.perform(get("/user").cookie(sessionCookie))
					.andExpect(status().isNotFound())
					.andExpect(content().json("{ message: 'User not found.' }"));
		}

		@Test
		public void returnsUserWhenSessionIdRegistered() throws Exception {
			MvcResult enterSiteResult = mockMvc.perform(get("/")).andReturn();
			Cookie sessionCookie = enterSiteResult.getResponse().getCookie("TRIVIA_SESSION_COOKIE");

			MvcResult getUserResult = mockMvc.perform(get("/user").cookie(sessionCookie))
					.andExpect(status().isOk())
					.andReturn();
			
			User user = ServerTestUtil.parseJson(getUserResult.getResponse().getContentAsString(), User.class);
			assertNotNull(user);
			assertNotNull(user.getName());
			assertFalse(user.getName().isEmpty());
		}

	}

}
