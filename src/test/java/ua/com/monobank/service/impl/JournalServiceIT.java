package ua.com.monobank.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.monobank.BasicTestClassHead;
import ua.com.monobank.model.Journal;
import ua.com.monobank.service.JournalService;


public class JournalServiceIT extends BasicTestClassHead {

    @Autowired
    private JournalService service;

    @Test
    public void shouldBeGetAll() {
        List<Journal> all = service.getAll();
        assertNotNull(all);
        assertEquals(all.size(), 3);
    }

    @Test
    public void shouldBeFindByMnemonicTest() {
        Integer currencyCode = 840;
        Journal byMnemonic = service.findByMnemonic("usd");
        assertNotNull(null, byMnemonic);
        assertEquals(byMnemonic.getCurrencyCode(), currencyCode);
    }

    @Test
    public void shouldBeGetOne() {
        Journal serviceOne = service.findByCodeAndDAte(840, LocalDate.now());
        assertNotNull(null, serviceOne);
    }
}