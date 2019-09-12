package ua.com.monobank.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.monobank.BasicTestControllerHead;

class JournalControllerIT extends BasicTestControllerHead {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldBeGetAllJournal() throws Exception {
        mockMvc.perform(get("/api/journal"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.*").isNotEmpty())
            .andExpect(jsonPath("$.*").isArray())
            .andExpect(status().isOk());
    }

    @Test
    void shouldBeGetMnemonic() throws Exception {
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
    void shouldBeFindByCodeAndDate() throws Exception {
        mockMvc.perform(get("/api/")
            .param("code", "840")
            .param("date", String.valueOf(LocalDate.now())))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk());


    }

}