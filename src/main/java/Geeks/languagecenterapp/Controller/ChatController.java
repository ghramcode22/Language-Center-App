package Geeks.languagecenterapp.Controller;

import Geeks.languagecenterapp.DTO.Request.ChatRoomRequest;
import Geeks.languagecenterapp.DTO.Request.MessageRequest;
import Geeks.languagecenterapp.Model.ChatRoomEntity;
import Geeks.languagecenterapp.Model.MessageEntity;
import Geeks.languagecenterapp.Model.UserEntity;
import Geeks.languagecenterapp.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/create-room")
    public ChatRoomEntity createChatRoom(@RequestBody ChatRoomRequest request) {
        return chatService.createChatRoom(request.getName(), request.getUsers());
    }

    @PostMapping("/send-message")
    public MessageEntity sendMessage(@RequestBody MessageRequest request) {
        return chatService.sendMessage(request.getUserId(), request.getChatRoomId(), request.getContent());
    }

    @GetMapping("/messages/{chatRoomId}")
    public List<MessageEntity> getMessages(@PathVariable int chatRoomId) {
        return chatService.getMessages(chatRoomId);
    }
}



