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

import l2k.demo.multiple.chats.controllers.request.JoinRoomRequest;
import l2k.demo.multiple.chats.controllers.response.JoinRoomResponse;
import l2k.demo.multiple.chats.customannotations.ImplementationPending;
import l2k.demo.multiple.chats.domain.Room;
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
			@ImplementationPending
			@DisplayName("sets response status to 200")
			void testOne() {
				
			}
			
			@Test
			@ImplementationPending
			@DisplayName("returns a joinChatResponse which includes the joined room")
			void testTwo() {
				
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
			@ImplementationPending
			@DisplayName("sets response status to 403")
			void testOne() {
				
			}
			
			@Test
			@ImplementationPending
			@DisplayName("returns a joinChatResponse with joinedRoom set to null")
			void testTwo() {

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
