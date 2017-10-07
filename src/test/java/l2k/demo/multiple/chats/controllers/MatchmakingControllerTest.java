package l2k.demo.multiple.chats.controllers;

import java.security.Principal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.messages.JoinRoomRequest;
import l2k.demo.multiple.chats.messages.JoinRoomResponse;
import l2k.demo.multiple.chats.services.RoomMonitor;

@RunWith(JUnitPlatform.class)
public class MatchmakingControllerTest {
	
	MatchmakingController matchmakingController;
	
	@BeforeEach
	void setup() {
		matchmakingController = new MatchmakingController();
	}
	
	@AfterEach
	void tearDown() {
		matchmakingController = null;
	}
	
	@Nested
	class JoinChatRoomInstanceMethodTest {
		
		JoinRoomRequest joinRoomRequest;
		
		@BeforeEach
		void setup() {
			joinRoomRequest = new JoinRoomRequest();
		}
		
		@AfterEach
		void tearDown() {
			joinRoomRequest = null;
		}
		
		@Nested
		class UserSuccessfullyJoinsRoom {
			
			Room joinableRoom;
			
			@BeforeEach
			void setup() {				
				joinableRoom = new Room();
				joinableRoom.setMaxNumberOfUsers(5);
				joinableRoom.setName("TEST_ROOM");
				
				matchmakingController.setRoomMonitor(new RoomMonitor() {{
					addRoom(joinableRoom);
				}});
				
				joinRoomRequest.setRoomName(joinableRoom.getName());
			}
			
			@AfterEach
			void tearDown() {
				joinableRoom = null;
			}
			
			@Test
			@DisplayName("sets response status to 200")
			void testOne() {
				ResponseEntity<JoinRoomResponse> responseEntity = matchmakingController.joinChatRoom(joinRoomRequest);
				Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
			}
			
			@Test
			@DisplayName("returns a joinChatResponse which includes the joined room")
			void testTwo() {
				ResponseEntity<JoinRoomResponse> responseEntity = matchmakingController.joinChatRoom(joinRoomRequest);
				
				JoinRoomResponse responseBody = responseEntity.getBody();
				Assertions.assertEquals(joinableRoom, responseBody.getRoom());
			}
			
		}
		
		@Nested
		class TargetedRoomIsFull {
			
			Room fullRoom;
			
			@BeforeEach
			void setup() {				
				fullRoom = new Room();
				fullRoom.setMaxNumberOfUsers(1);
				fullRoom.setName("TEST_ROOM");
				fullRoom.addUser(newUserWithName("TEST_USER"));
				
				matchmakingController.setRoomMonitor(new RoomMonitor() {{
					addRoom(fullRoom);
				}});
				
				joinRoomRequest.setRoomName(fullRoom.getName());
			}
			
			@Test
			@DisplayName("sets response status to 403")
			void testOne() {
				ResponseEntity<JoinRoomResponse> response = matchmakingController.joinChatRoom(joinRoomRequest);
				Assertions.assertEquals(403, response.getStatusCodeValue());
			}
			
			@Test
			@DisplayName("returns a joinChatResponse with joinedRoom set to null")
			void testTwo() {
				ResponseEntity<JoinRoomResponse> responseEntity = matchmakingController.joinChatRoom(joinRoomRequest);
				
				JoinRoomResponse responseBody = responseEntity.getBody();
				Assertions.assertNull(responseBody.getRoom());
			}
		}
		
	}
	
	private Principal newUserWithName(String name) {
		return new Principal(){

			@Override
			public String getName() {
				return name;
			}
			
		};
	}
	
}
