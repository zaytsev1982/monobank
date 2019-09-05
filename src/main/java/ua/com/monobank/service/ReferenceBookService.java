package ua.com.monobank.service;

import java.util.List;
import ua.com.monobank.model.ReferenceBook;

public interface ReferenceBookService {

    ReferenceBook create(ReferenceBook referenceBook);

    List<ReferenceBook> getAll();

    ReferenceBook findByMnemonic(String mnemonic);

}
