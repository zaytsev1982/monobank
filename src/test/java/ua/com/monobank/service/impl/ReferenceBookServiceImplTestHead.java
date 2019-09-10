package ua.com.monobank.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.monobank.BasicTestClassHead;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.service.ReferenceBookService;


class ReferenceBookServiceImplTestHead extends BasicTestClassHead {

    @Autowired
    private ReferenceBookService bookService;

    @Test
    void shouldBeCreate() {
        ReferenceBook book = getNewInstant();
        bookService.create(book);
        ReferenceBook bookFromDataBase = bookService.getAll().stream()
            .filter(c -> c.getCurrencyCode().equals(777)).findFirst()
            .orElse(null);
        assertNotNull(bookFromDataBase);
        assertEquals(book.getCurrencyCode(), bookFromDataBase.getCurrencyCode());
        assertEquals(book.getMnemonic(), bookFromDataBase.getMnemonic());
    }

    @Test
    void shouldBeGetAll() {
        List<ReferenceBook> mayBeList = bookService.getAll();
        assertNotNull(mayBeList);
        assertEquals(mayBeList.size(), 62);
    }

    @Test
    void shouldBeFindByMnemonic() {
        String mnemonic = "EUR";
        ReferenceBook mayBeMnemonic = bookService.findByMnemonic(mnemonic);
        assertNotNull(mayBeMnemonic);
        assertEquals(mayBeMnemonic.getMnemonic(), mnemonic);

    }

    @Test
    void shouldBeFindByCode() {
        Integer code = 840;
        ReferenceBook byBook = bookService.getAll().stream()
            .filter(c -> c.getCurrencyCode().equals(code)).findFirst().orElse(null);

        assertEquals(byBook.getCurrencyCode(), code);

    }

    private static ReferenceBook getNewInstant() {
        return new ReferenceBook()
            .builder()
            .mnemonic("My")
            .currencyCode(777)
            .description("my currency")
            .build();
    }
}