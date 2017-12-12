package l2k.trivia.unit.server.controller;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import l2k.trivia.server.controllers.AppController;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.CookieUtil;
import l2k.trivia.server.services.UserService;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(MockitoExtension.class)
class AppControllerTest {
	
	private AppController appController;
	
	@Mock
	private HttpServletResponse response;
	@Mock 
	private UserService userService;
	@Mock
	private User user;
	@Mock 
	private CookieUtil cookieUtil;

	@BeforeEach
	public void setupUserService() {
		when(userService.registerUser(any(String.class))).thenReturn(user);
	}
	
	@BeforeEach
	public void setupAppController() {
		appController = new AppController(userService, cookieUtil);
	}
	
	@Nested
	class EnterSite {
		
		@Test
		public void registersUserEnteringSite() {
			appController.enterSite("EXAMPLE_SESSION_ID", response);
			verify(userService).registerUser("EXAMPLE_SESSION_ID");
		}
		
		@Test
		public void sendsTriviaSessionCookieInResponse() {
			UUID sessionId = UUID.randomUUID();
			when(user.getSessionId()).thenReturn(sessionId);
			appController.enterSite("EXAMPLE_SESSION_ID", response);
			verify(cookieUtil).returnSessionCookie(sessionId, response);
		}
		
		@Test
		public void returnsTriviaClient() {
			ModelAndView modelAndView = appController.enterSite("EXAMPLE_SESSION_ID", response);
			assertThat(modelAndView.getViewName(), equalTo("index.html"));
		}
	}	

}
