package ua.com.monobank.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.monobank.BasicTestClassHead;
import ua.com.monobank.model.Journal;
import ua.com.monobank.service.JournalService;


class JournalServiceImplTestHead extends BasicTestClassHead {

    @Autowired
    private JournalService service;

    @Test
    void shouldBeGetAll() {
        List<Journal> all = service.getAll();
        Assert.assertEquals(all.size(), 4);
    }

    @Test
    void shouldBeFindByMnemonicTest() {
        Integer currencyCode = 840;
        Journal byMnemonic = service.findByMnemonic("usd");
        Assert.assertNotNull(byMnemonic);
        Assert.assertEquals(byMnemonic.getCurrencyCode(), currencyCode);
    }

    @Test
    void shouldBeGetOne() {
        Journal serviceOne = service.getOne(840, LocalDate.now());
        Assert.assertNotNull(serviceOne);
    }
}