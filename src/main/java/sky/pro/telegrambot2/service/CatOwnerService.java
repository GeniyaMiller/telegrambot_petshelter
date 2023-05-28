package sky.pro.telegrambot2.service;

import org.springframework.stereotype.Service;
import sky.pro.telegrambot2.enam.ProbationaryStatus;
import sky.pro.telegrambot2.exception.AlreadyExistException;
import sky.pro.telegrambot2.exception.NotFoundException;
import sky.pro.telegrambot2.exception.WrongInputDataException;
import sky.pro.telegrambot2.model.CatOwner;
import sky.pro.telegrambot2.repository.CatOwnerRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CatOwnerService {
    private final CatOwnerRepository catOwnerRepository;


    public CatOwnerService(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }

    public void saveOwner(CatOwner owner) {
        catOwnerRepository.save(owner);
    }

    public Optional <CatOwner> findCatOwnerByChatId(Long chatId) {
        return Optional.ofNullable(catOwnerRepository.getOwnerByChatId(chatId));
    }

    public List<CatOwner> findAllOwners() {
        return catOwnerRepository.findAll();
    }

    public CatOwner saveOwnerByNameAndChatId(String name,
                                             long chatId) {
        CatOwner owner = new CatOwner();
        owner.setName(name);
        owner.setChatId(chatId);
        owner.setDateOfStartProbation(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        owner.setDateOfEndProbation(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(30));
        owner.setProbationaryStatus(ProbationaryStatus.ACTIVE);

        catOwnerRepository.findAll()
                .forEach(owners -> {
                    if (owners.getName().equals(owner.getName()) &&
                            owners.getChatId().equals(owner.getChatId())) {
                        throw new AlreadyExistException();
                    }
                });
        return catOwnerRepository.save(owner);
    }

    public CatOwner findOwnerById(Integer id) {
        CatOwner owner = catOwnerRepository.findOwnerById(id);
        if (owner == null) {
            throw new NotFoundException();
        }
        return catOwnerRepository.findOwnerById(id);
    }

    public CatOwner extendProbationaryPeriod(Integer id, Integer days) {
        CatOwner owner = findOwnerById(id);
        if (days < 0 || days > 15) {
            throw new WrongInputDataException();
        }
        owner.setPeriodExtend(days);
        owner.setDateOfEndProbation(owner.getDateOfEndProbation().plusDays(days));
        owner.setProbationaryStatus(ProbationaryStatus.EXTENDED);
        catOwnerRepository.save(owner);
        return owner;
    }

    public CatOwner changeProbationaryStatus(Integer id, ProbationaryStatus status) {
        CatOwner owner = findOwnerById(id);
        owner.setProbationaryStatus(status);
        catOwnerRepository.save(owner);
        return owner;
    }
}