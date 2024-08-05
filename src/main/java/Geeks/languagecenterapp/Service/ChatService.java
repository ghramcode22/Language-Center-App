package Geeks.languagecenterapp.Service;

import Geeks.languagecenterapp.Model.ChatRoomEntity;
import Geeks.languagecenterapp.Model.MessageEntity;
import Geeks.languagecenterapp.Model.UserEntity;
import Geeks.languagecenterapp.Repository.ChatRoomRepository;
import Geeks.languagecenterapp.Repository.MessageRepository;
import Geeks.languagecenterapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public ChatRoomEntity createChatRoom(String name, List<Integer> users) {
        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setName(name);
        List<UserEntity> userEntities = userRepository.findAllById(users);
        chatRoom.setUsers(userEntities);
        return chatRoomRepository.save(chatRoom);
    }

    public MessageEntity sendMessage(int userId, int chatRoomId, String content) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("Chat room not found"));

        MessageEntity message = new MessageEntity();
        message.setUser(user);
        message.setChatRoom(chatRoom);
        message.setContent(content);
        message.setDate(LocalDateTime.now());
        message.setIsRead(false);

        return messageRepository.save(message);
    }

    public List<MessageEntity> getMessages(int chatRoomId) {
        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("Chat room not found"));
        return chatRoom.getMessages();
    }
}
