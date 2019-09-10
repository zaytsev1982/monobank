package ua.com.monobank.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private JournalService journalService;
    @Autowired
    private ReferenceBookService bookService;

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
        ReferenceBook byMnemonic = bookService.findByMnemonic(mnemonic);
        Journal journal = journalService.findByMnemonic(mnemonic);


        if (!byMnemonic.getMnemonic().equalsIgnoreCase(mnemonic) || journal == null) {
            log.info("IN JournalController METHOD getMnemonic, {} not found", mnemonic);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        JournalForm journalForm = JournalForm.from(journal);

        Map<String, String> map = new HashMap<>();
        map.put("buy", journalForm.getBuy());
        map.put("sale", journalForm.getSale());
        log.info("IN JournalController METHOD getMnemonic,  mnemonic - '{}' return successfully {}",
            mnemonic, map);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Journal> findByCodeAndDate(@RequestParam("code") Integer code,
        @RequestParam("date")
        @DateTimeFormat(iso = ISO.DATE) LocalDate date) {

        Journal journal = journalService.getOne(code, date);
        if (journal == null) {
            log.info(
                "IN JournalController METHOD findByCodeAndDate, record by code {} and date {} not found",
                code, date);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info(
            "IN JournalController METHOD findByCodeAndDate, record by code {} and date {} not found",
            code, date);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }


}
