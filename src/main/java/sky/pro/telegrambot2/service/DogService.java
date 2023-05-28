package sky.pro.telegrambot2.service;

import org.springframework.stereotype.Service;
import sky.pro.telegrambot2.exception.AlreadyExistException;
import sky.pro.telegrambot2.exception.NotFoundException;
import sky.pro.telegrambot2.model.Dog;
import sky.pro.telegrambot2.repository.DogRepository;

import java.time.LocalDate;

@Service
public class DogService {
    private final DogRepository dogRepository;
    private final DogOwnerService ownerService;

    public DogService(DogRepository dogRepository, DogOwnerService ownerService) {
        this.dogRepository = dogRepository;
        this.ownerService = ownerService;
    }

    public Dog saveDogWithNameBirthDateAndBreed(String name,
                                                LocalDate birthDate,
                                                String breed) {
        Dog dog = new Dog();
        dog.setName(name);
        dog.setBirthDate(birthDate);
        dog.setBreed(breed);
        if (dog.equals(dogRepository.findByNameAndBirthDateAndBreed(
                name, birthDate, breed))) {
            throw new AlreadyExistException();
        }
        return dogRepository.save(dog);
    }

    public Dog assignDogWithOwner(Integer ownerId, Integer dogId) {
        Dog dog = dogRepository.findDogById(dogId);
        if (dog == null) {
            throw new NotFoundException();
        }
        dog.setOwner(ownerService.findOwnerById(ownerId));
        dogRepository.save(dog);
        return dog;
    }

    public Dog findDogById(Integer id) {
        Dog dog = dogRepository.findDogById(id);
        if (dog == null) {
            throw new NotFoundException();
        }
        return dog;
    }
}

