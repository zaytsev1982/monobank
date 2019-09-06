package ua.com.monobank.service.impl;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.monobank.exception.JournalNotFoundException;
import ua.com.monobank.exception.MnemonicBadRequest;
import ua.com.monobank.exception.MnemonicNotFoundException;
import ua.com.monobank.model.Journal;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.repository.JournalRepository;
import ua.com.monobank.repository.ReferenceBookRepository;
import ua.com.monobank.service.JournalService;

@Slf4j
@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    private final ReferenceBookRepository bookRepository;

    @Autowired
    public JournalServiceImpl(JournalRepository journalRepository,
        ReferenceBookRepository bookRepository) {
        this.journalRepository = journalRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Journal> getAll() {
        List<Journal> journalList = journalRepository.findAll();
        if (journalList.isEmpty()) {
            log.info("IN JournalServiceImpl METHOD getAll,journal failed to save ");
            throw new JournalNotFoundException("journal currency empty");
        }
        for (Journal journal : journalList) {
            log.info("IN JournalServiceImpl METHOD getAll {}", journal);
        }
        return journalList;
    }

    @Override
    public Journal create(Journal journal) {
        if (journal == null) {
            log.info("IN JournalServiceImpl METHOD create : Journal is empty ");
            throw new JournalNotFoundException("Journal is empty");
        }
        log.info("IN JournalServiceImpl METHOD create {}", journal);
        return journalRepository.save(journal);
    }

    @Override
    public Journal findByMnemonic(String mnemonic) {
        ReferenceBook byMnemonic = bookRepository.findByMnemonic(mnemonic);
        if (byMnemonic.getMnemonic() == null) {
            throw new MnemonicNotFoundException("mnemonic " + mnemonic + " not found");
        }
        if (!mnemonic.equals(byMnemonic.getMnemonic())) {
            log.info("there is no such value in the database {}", mnemonic);
            throw new MnemonicBadRequest(
                "bed request: " + mnemonic + " there is no such value in the database ");
        }
        List<Journal> journals = journalRepository.findAll();
        for (Journal journal : journals) {
            boolean equals = journal.getCurrencyCode().equals(byMnemonic.getCurrencyCode());
            if (equals) {
                return journal;
            }
        }
        return null;
    }

    @Override
    public Journal getOne(Integer code, LocalDate date) {
        Journal journal = getCode(code);

        Journal byCurrencyCodeAndDate = journalRepository
            .findByCurrencyCodeAndDate(journal.getCurrencyCode(), date);

        if (byCurrencyCodeAndDate == null) {
            log.info("IN JournalServiceImpl METHOD getOne record by {} and {} not found ", code,
                date);
            throw new JournalNotFoundException(
                "record by code -" + code + " and date " + date + " not found");
        }
        log.info("IN JournalServiceImpl METHOD getOne record {} found successfully ",
            byCurrencyCodeAndDate);

        return byCurrencyCodeAndDate;
    }

    @Override
    public Journal getCode(Integer code) {
        return journalRepository.findByCurrencyCode(code);
    }

    @Override
    public List<Journal> getByDate(LocalDate localDate) {
        return journalRepository.findAllByDate(localDate);
    }



}
