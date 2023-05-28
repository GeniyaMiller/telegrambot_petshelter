package sky.pro.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.telegrambot2.model.UserContext;

import java.util.Optional;

@Repository
public interface UserContextRepository extends JpaRepository<UserContext, Integer> {
    Optional <UserContext> findByChatId(Long chatId);
}