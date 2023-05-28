package sky.pro.telegrambot2.service;

import org.springframework.stereotype.Service;
import sky.pro.telegrambot2.model.DogShelterUser;
import sky.pro.telegrambot2.repository.DogShelterUsersRepository;

@Service
public class DogShelterUserService {
    private final DogShelterUsersRepository dogShelterUsersRepository;

    public DogShelterUserService(DogShelterUsersRepository dogShelterUsersRepository) {
        this.dogShelterUsersRepository = dogShelterUsersRepository;
    }

    public DogShelterUser addUser(String phoneNumber, String name) {
        DogShelterUser dogShelterUser = new DogShelterUser();
        dogShelterUser.setPhoneNumber(phoneNumber);
        dogShelterUser.setName(name);
        return dogShelterUsersRepository.save(dogShelterUser);
    }
}
