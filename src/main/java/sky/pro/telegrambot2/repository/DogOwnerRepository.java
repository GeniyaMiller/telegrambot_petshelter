package sky.pro.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.telegrambot2.model.DogOwner;


@Repository
public interface DogOwnerRepository extends JpaRepository<DogOwner, Integer> {
    DogOwner getOwnerByChatId(Long chatID);
    DogOwner findOwnerById(int id);
}
