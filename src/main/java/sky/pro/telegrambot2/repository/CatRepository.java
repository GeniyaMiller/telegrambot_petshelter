package sky.pro.telegrambot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sky.pro.telegrambot2.model.Cat;
import sky.pro.telegrambot2.model.DogOwnerReport;

import java.time.LocalDate;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    Cat findCatById(Integer id);

    Cat findByNameAndBirthDateAndBreed(String name,
                                       LocalDate birthDate,
                                       String breed);
}
