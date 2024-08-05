package Geeks.languagecenterapp.DTO.Request;

import Geeks.languagecenterapp.Model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatRoomRequest {
    private String name;
    private List<Integer> users;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getUsers() {
        return this.users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public ChatRoomRequest name(String name) {
        setName(name);
        return this;
    }


    public ChatRoomRequest users(List<Integer> users) {
        setUsers(users);
        return this;
    }
    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", users='" + getUsers() + "'" +
            "}";
    }
    
}