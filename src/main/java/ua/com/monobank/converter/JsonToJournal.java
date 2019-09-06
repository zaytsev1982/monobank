package ua.com.monobank.converter;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.com.monobank.model.Journal;
import ua.com.monobank.service.JournalService;
import ua.com.monobank.transfer.JournalJson;

@Component
public class JsonToJournal implements Converter<JournalJson, Journal> {

    private final JournalService journalService;

    @Autowired
    public JsonToJournal(JournalService journalService) {
        this.journalService = journalService;
    }

    @Override
    public Journal convert(JournalJson journalJson) {
        Journal journal = new Journal();
        if (journalJson.getCode() != null && journalJson.getBuy() != null
            && journalJson.getSale() != null) {
            journal.setCurrencyCode(Integer.valueOf(journalJson.getCode()));
            journal.setBuy(Double.valueOf(journalJson.getBuy()));
            journal.setSale(Double.valueOf(journalJson.getSale()));
            journal.setDate(LocalDate.now());
            return journalService.create(journal);
        }
        return null;

    }



}
