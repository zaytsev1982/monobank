package ua.com.monobank.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.monobank.model.Journal;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.service.JournalService;
import ua.com.monobank.service.ReferenceBookService;
import ua.com.monobank.transfer.JournalForm;

@Slf4j
@RestController
@RequestMapping("/api/")
public class JournalController {

    private final JournalService journalService;
    private final ReferenceBookService bookService;

    @Autowired
    public JournalController(JournalService journalService,
        ReferenceBookService bookService) {
        this.journalService = journalService;
        this.bookService = bookService;
    }

    @GetMapping(value = "journal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Journal>> getAll() {
        List<Journal> journals = journalService.getAll();
        if (journals.isEmpty()) {
            log.info("IN JournalController METHOD getAll, List is Empty");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("IN JournalController METHOD getAll, item count - {}", journals.size());
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(value = "{mnemonic}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, String>> getMnemonic(
        @PathVariable("mnemonic") String mnemonic) {
        ReferenceBook book = bookService.findByMnemonic(mnemonic);
        Journal journal = journalService.findByMnemonic(mnemonic);
        if (!book.getMnemonic().equals(mnemonic)) {
            log.info("IN JournalController METHOD getMnemonic, {} bed request", mnemonic);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (journal == null) {
            log.info("IN JournalController METHOD getMnemonic, {} not found", mnemonic);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JournalForm journalForm = JournalForm.from(journal);
        Map<String, String> map = new HashMap<>();
        map.put("buy", journalForm.getBuy());
        map.put("sale", journalForm.getSale());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("code/date")
    public ResponseEntity<Journal> findByCodeAndDate(@RequestParam("code") Integer code,
        @RequestParam("date")
        @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        Journal journal = journalService.getOne(code, date);
        if (journal == null) {
            log.info(
                "IN JournalController METHOD findByCodeAndDate, record by code {} and date {} not found",
                journal.getCurrencyCode(), journal.getDate());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info(
            "IN JournalController METHOD findByCodeAndDate, record by code {} and date {} not found",
            journal.getCurrencyCode(), journal.getDate());
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

}
