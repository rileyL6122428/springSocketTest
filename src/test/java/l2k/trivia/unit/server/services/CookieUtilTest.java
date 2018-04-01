package l2k.trivia.unit.server.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import l2k.trivia.server.services.SessionUtil;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CookieUtilTest {
	
	private SessionUtil cookieUtil;
	private ArgumentCaptor<Cookie> cookieCaptor;
	
	@BeforeEach 
	public void setup() {
		cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
		cookieUtil = new SessionUtil();
	}
	
	@Nested
	class AddSessionCookie {
		
		@Test
		public void addsProvidedSessionIdToServletResponse(@Mock Object sessionId, @Mock HttpServletResponse response) {
			doNothing().when(response).addCookie(cookieCaptor.capture());
			when(sessionId.toString()).thenReturn("SESSION_ID_AS_STRING");
			
			cookieUtil.returnSessionCookie(sessionId, response);
			
			Cookie sessionCookie = cookieCaptor.getValue();
			assertThat(sessionCookie.getName(), equalTo("TRIVIA_SESSION_COOKIE"));
			assertThat(sessionCookie.getValue(), equalTo("SESSION_ID_AS_STRING"));
		}
		
	}

}
