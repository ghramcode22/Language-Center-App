package Geeks.languagecenterapp.Repository;

import Geeks.languagecenterapp.Model.UserChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRoomEntity, Integer> {
}
