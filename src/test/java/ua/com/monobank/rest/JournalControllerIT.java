package ua.com.monobank.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    }

    @Test
    void shouldBeFindByCodeAndDate() throws Exception {

    }
}