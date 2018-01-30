package l2k.trivia.unit.server.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import l2k.trivia.server.controllers.StaticContentController;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.SessionUtil;
import l2k.trivia.server.services.UserService;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppControllerTest {
	
	private StaticContentController appController;
	
	@Mock
	private HttpServletResponse response;
	
	private UUID sessionId;
	@Mock 
	private UserService userService;
	@Mock
	private User user;
	@Mock 
	private SessionUtil cookieUtil;

	@BeforeEach
	public void setupSessionId() {
		sessionId = UUID.randomUUID();
	}
	
	@BeforeEach
	public void setupCookieUtil() {
		when(cookieUtil.stringToUUID(sessionId.toString())).thenReturn(sessionId);		
	}
	
	@BeforeEach
	public void setupUserService() {
		when(userService.registerUser(any(UUID.class))).thenReturn(user);
	}
	
	@BeforeEach
	public void setupAppController() {
		appController = new StaticContentController(userService, cookieUtil);
	}
	
	@Nested
	class EnterSite {
		
		@Test
		public void registersUserEnteringSite() {
			ArgumentCaptor<UUID> sessionIdCaptor = ArgumentCaptor.forClass(UUID.class);
			when(userService.registerUser(sessionIdCaptor.capture())).thenReturn(user);
			
			appController.enterSite(sessionId.toString(), response);
			
			UUID registeredSessionId = sessionIdCaptor.getValue();
			assertThat(registeredSessionId, equalTo(sessionId));
		}
		
		@Test
		public void sendsTriviaSessionCookieInResponse() {
			when(user.getSessionId()).thenReturn(sessionId);
			appController.enterSite(sessionId.toString(), response);
			verify(cookieUtil).returnSessionCookie(sessionId, response);
		}
		
		@Test
		public void returnsTriviaClient() {
			ModelAndView modelAndView = appController.enterSite(sessionId.toString(), response);
			assertThat(modelAndView.getViewName(), equalTo("index.html"));
		}
	}	

}
