package sky.pro.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.telegrambot2.model.Cat;
import sky.pro.telegrambot2.model.Dog;

import java.time.LocalDate;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {
    Dog findDogById(int id);
    Dog findByNameAndBirthDateAndBreed(String name,
                                       LocalDate birthDate,
                                       String breed);
}
