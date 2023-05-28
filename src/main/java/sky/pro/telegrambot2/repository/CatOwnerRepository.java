package sky.pro.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.telegrambot2.model.CatOwner;


@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Integer> {
    CatOwner getOwnerByChatId(Long chatID);

    CatOwner findOwnerById(int id);
}
