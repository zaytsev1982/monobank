package ua.com.monobank.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.monobank.exception.JournalNotFoundException;
import ua.com.monobank.exception.MnemonicNotFoundException;
import ua.com.monobank.exception.ReferenceBookNotFoundException;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.repository.ReferenceBookRepository;
import ua.com.monobank.service.ReferenceBookService;

@Service
@Slf4j
@Transactional
public class ReferenceBookServiceImpl implements ReferenceBookService {

    @Autowired
    private ReferenceBookRepository bookRepository;

    @Override
    public ReferenceBook create(ReferenceBook referenceBook) {
        ReferenceBook book = bookRepository.findByMnemonic(referenceBook.getMnemonic());

        if (book == null) {
            log.info("IN ReferenceBookServiceImpl METHOD create {}", referenceBook);
            return bookRepository.save(referenceBook);
        }
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReferenceBook> getAll() {
        List<ReferenceBook> referenceBooks = bookRepository.findAll();
        if (referenceBooks.isEmpty()) {
            log.info("IN ReferenceBookServiceImpl METHOD getAll, referenceBooks empty");
            throw new JournalNotFoundException("referenceBooks empty");
        }
        referenceBooks.forEach(referenceBook -> log
            .info("IN ReferenceBookServiceImpl METHOD getAll {} ", referenceBook));
        return referenceBooks;
    }

    @Override
    public ReferenceBook findByMnemonic(String mnemonic) {
        ReferenceBook candidate = bookRepository.findAll().stream()
            .filter(m -> m.getMnemonic().equalsIgnoreCase(mnemonic))
            .findFirst().orElse(null);


        if (candidate == null) {
            log.info("IN ReferenceBookServiceImpl METHOD findByMnemonic '{}' not found ", mnemonic);
            throw new MnemonicNotFoundException("mnemonic '" + mnemonic + "' not found");
        }
        log.info("IN ReferenceBookServiceImpl METHOD findByMnemonic {} found successfully ",
            candidate.getMnemonic());
        return candidate;
    }

    @Override
    public Integer findByCode(Integer code) {
        Integer byCurrencyCode = bookRepository.findByCurrencyCode(code);
        if (byCurrencyCode == null) {
            log.info("code {} not found", code);
            throw new ReferenceBookNotFoundException("code " + code + " not found");
        }
        log.info("IN ReferenceBookServiceImpl method findByCode, code {} found successfully ",
            byCurrencyCode);
        return byCurrencyCode;
    }

}
