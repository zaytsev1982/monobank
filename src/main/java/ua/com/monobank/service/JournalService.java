package ua.com.monobank.service;

import java.time.LocalDate;
import java.util.List;
import ua.com.monobank.model.Journal;

public interface JournalService {

    List<Journal> getAll();

    Journal create(Journal journal);

    Journal findByMnemonic(String mnemonic);

    Journal findByCodeAndDAte(Integer code, LocalDate date);

}
