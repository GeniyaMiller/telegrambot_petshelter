package sky.pro.telegrambot2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestMapping;
import sky.pro.telegrambot2.model.CatOwner;
import sky.pro.telegrambot2.model.CatOwnerReport;
import sky.pro.telegrambot2.model.DogOwner;
import sky.pro.telegrambot2.model.DogOwnerReport;
import sky.pro.telegrambot2.repository.CatOwnerReportRepository;
import sky.pro.telegrambot2.repository.DogOwnerReportRepository;
import sky.pro.telegrambot2.service.CatOwnerReportService;
import sky.pro.telegrambot2.service.DogOwnerReportService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = pro.sky.telegrambot2.controller.CatOwnerReportController.class)

class CatOwnerReportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CatOwnerReportRepository catOwnerReportRepository;

    @SpyBean
    private CatOwnerReportService catOwnerReportService;

    @Test
    void findReportsByOwnerId() throws Exception {
        Integer id1 = 1;
        String stringReport1 = "All is well with the dog";
        CatOwnerReport report1 = new CatOwnerReport();
        report1.setId(id1);
        report1.setStringReport(stringReport1);

        Integer id2 = 1;
        String stringReport2 = "All is well with the cat";
        CatOwnerReport report2 = new CatOwnerReport();
        report2.setId(id2);
        report2.setStringReport(stringReport2);

        CatOwner owner = new CatOwner();
        Integer ownerId = 2;
        String ownerName = "Alex";
        owner.setId(ownerId);
        owner.setName(ownerName);
        report1.setCatOwner(owner);
        report2.setCatOwner(owner);

        when(catOwnerReportRepository.findByCatOwnerId(ownerId)).thenReturn(List.of(report1, report2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cat-reports/{ownerId}", ownerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(
                        List.of(report1, report2))));
    }
}
