package Geeks.languagecenterapp.DTO.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage {
    private String sender;
    private String content;


}

