package ua.com.monobank.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.service.ReferenceBookService;
import ua.com.monobank.transfer.BookJson;

@Component
public class JsonToBook implements Converter<BookJson, ReferenceBook> {

    private final ReferenceBookService bookService;

    @Autowired
    public JsonToBook(ReferenceBookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ReferenceBook convert(BookJson bookJson) {
        ReferenceBook referenceBook = new ReferenceBook();
        referenceBook.setMnemonic(bookJson.getMnemonic());
        referenceBook.setCurrencyCode(Integer.valueOf(bookJson.getCode()));
        referenceBook.setDescription(bookJson.getDescription());
        return bookService.create(referenceBook);
    }
}
