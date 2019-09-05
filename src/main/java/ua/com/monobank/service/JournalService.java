package ua.com.monobank.service;

import java.util.Date;
import java.util.List;
import ua.com.monobank.model.Journal;

public interface JournalService {

    List<Journal> getAll();

    Journal create(Journal journal);

    Journal findByMnemonic(String mnemonic);

    Journal getOne(Integer code, Date date);
}
