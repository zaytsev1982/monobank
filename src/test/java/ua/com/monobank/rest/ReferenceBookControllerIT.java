package ua.com.monobank.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.monobank.BasicTestControllerHead;


public class ReferenceBookControllerIT extends BasicTestControllerHead {

    @Autowired
    private ReferenceBookController controller;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldBeGetAllReferences() throws Exception {
        mockMvc.perform(get("/api/reference-book"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.*").isNotEmpty())
            .andExpect(jsonPath("$.*").isArray())
            .andExpect(status().isOk());
    }




}