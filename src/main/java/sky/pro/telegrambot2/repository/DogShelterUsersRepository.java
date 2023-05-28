package sky.pro.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.telegrambot2.model.DogShelterUser;

public interface DogShelterUsersRepository extends JpaRepository <DogShelterUser,Integer> {
}