package sky.pro.telegrambot2.service;

import org.springframework.stereotype.Service;
import sky.pro.telegrambot2.model.CatShelterUser;
import sky.pro.telegrambot2.repository.CatShelterUsersRepository;

@Service
public class CatShelterUserService {
    private final CatShelterUsersRepository catShelterUsersRepository;

    public CatShelterUserService(CatShelterUsersRepository catShelterUsersRepository) {
        this.catShelterUsersRepository = catShelterUsersRepository;
    }

    public CatShelterUser addUser(String phoneNumber, String name) {
        CatShelterUser catShelterUser = new CatShelterUser();
        catShelterUser.setPhoneNumber(phoneNumber);
        catShelterUser.setName(name);
        return catShelterUsersRepository.save(catShelterUser);
    }
}