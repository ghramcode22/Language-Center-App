package Geeks.languagecenterapp.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageRequest {
    private int userId;
    private int chatRoomId;
    private String content;



    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatRoomId() {
        return this.chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageRequest userId(int userId) {
        setUserId(userId);
        return this;
    }

    public MessageRequest chatRoomId(int chatRoomId) {
        setChatRoomId(chatRoomId);
        return this;
    }

    public MessageRequest content(String content) {
        setContent(content);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", chatRoomId='" + getChatRoomId() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
    
}