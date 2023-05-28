package sky.pro.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.telegrambot2.model.CatShelterUser;

public interface CatShelterUsersRepository extends JpaRepository<CatShelterUser, Integer> {
}
