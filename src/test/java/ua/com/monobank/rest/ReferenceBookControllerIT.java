package ua.com.monobank.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.monobank.BasicTestControllerHead;
import ua.com.monobank.model.ReferenceBook;


public class ReferenceBookControllerIT extends BasicTestControllerHead {

    @Autowired
    private MockMvc mockMvc;


    private final static String GetAllReferences = "/api/reference-book";
    private final static String CreateReference = "/api/add";


    @Test
    public void shouldBeGetAllReferences() throws Exception {

        mockMvc.perform(get(GetAllReferences))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.*").isNotEmpty())
            .andExpect(jsonPath("$.*").isArray())
            .andExpect(status().isOk());
    }

    @Test
    public void shouldBeCreateReference() throws Exception {
        ReferenceBook book = ReferenceBook.builder().mnemonic("MY").currencyCode(777)
            .description("my currency").build();

        mockMvc.perform(post(CreateReference)
            .content(new ObjectMapper().writeValueAsString(book))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.mnemonic", is("MY")))
            .andExpect(jsonPath("$.currencyCode", is(777)))
            .andExpect(jsonPath("$.description", is("my currency")));

    }

}