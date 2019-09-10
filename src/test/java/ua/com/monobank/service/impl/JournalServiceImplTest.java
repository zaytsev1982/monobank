package ua.com.monobank.service.impl;

import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.monobank.model.Journal;
import ua.com.monobank.repository.ReferenceBookRepository;
import ua.com.monobank.service.JournalService;

@RunWith(SpringRunner.class)
@SpringBootTest
class JournalServiceImplTest {

    @Autowired
    private JournalService service;
    @Mock
    private ReferenceBookRepository referenceBookRepository;

    @Test
    void getAll() {
        List<Journal> all = service.getAll();
        Assert.assertEquals(all.size(), 4);
    }

    @Test
    void findByMnemonicTest() {
        Integer currencyCode = 840;
        Journal byMnemonic = service.findByMnemonic("usd");
        Assert.assertNotNull(byMnemonic);
        Assert.assertEquals(byMnemonic.getCurrencyCode(), currencyCode);
    }
}