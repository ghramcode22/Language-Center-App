package Geeks.languagecenterapp.Controller;

import Geeks.languagecenterapp.DTO.Request.ChatMessage;
import Geeks.languagecenterapp.DTO.Request.ChatRoomRequest;
import Geeks.languagecenterapp.DTO.Request.MessageRequest;
import Geeks.languagecenterapp.Model.ChatRoomEntity;
import Geeks.languagecenterapp.Model.MessageEntity;
import Geeks.languagecenterapp.Model.UserEntity;
import Geeks.languagecenterapp.Repository.UserRepository;
import Geeks.languagecenterapp.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SysexMessage;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/create-room")
    public ResponseEntity<?> createChatRoom(@Valid @RequestBody ChatRoomRequest request, Authentication authentication) {


        Object principal = authentication.getPrincipal();
        if (principal instanceof UserEntity) {
            UserEntity currentUser = (UserEntity) principal;

            if (!"ADMIN".equals(currentUser.getAccountType().name())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can create chat rooms.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid user authentication.");
        }

        ChatRoomEntity chatRoom = chatService.createChatRoom(request.getName(), request.getUsers());
        return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
    }


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send")
    public void sendMessage(@RequestParam String destination, @RequestParam String message) {
        messagingTemplate.convertAndSend(destination, message);
    }

    @GetMapping("/messages/{chatRoomId}")
    public List<MessageEntity> getMessages(@PathVariable int chatRoomId) {
        return chatService.getMessages(chatRoomId);
    }
}



