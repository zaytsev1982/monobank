package ua.com.monobank.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.monobank.BasicTestControllerHead;

public class JournalControllerIT extends BasicTestControllerHead {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldBeGetAllJournal() throws Exception {
        mockMvc.perform(get("/api/journal"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.*").isNotEmpty())
            .andExpect(jsonPath("$.*").isArray())
            .andExpect(status().isOk());
    }

    @Test
    public void shouldBeGetMnemonic() throws Exception {
        String mnemonic = "USD";
        mockMvc.perform(get("/api/{mnemonic}", mnemonic))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andReturn();

        mockMvc.perform(get("/api/{mnemonic}", mnemonic + 1))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound())
            .andReturn();
    }

    @Test
    public void shouldBeFindByCodeAndDate() throws Exception {
        String format = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String correctCode = "840";
        String incorrectCode = "1234";
//        correct data
        mockMvc.perform(get("/api/")
            .param("code", correctCode)
            .param("date", format))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk());
//        incorrect data
        mockMvc.perform(get("/api/")
            .param("code", incorrectCode)
            .param("date", format))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isNotFound());

    }

}