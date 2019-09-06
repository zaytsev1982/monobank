package ua.com.monobank.rest;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ua.com.monobank.converter.JsonToBook;
import ua.com.monobank.converter.JsonToJournal;
import ua.com.monobank.model.Journal;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.service.JournalService;
import ua.com.monobank.service.ReferenceBookService;
import ua.com.monobank.transfer.BookJson;
import ua.com.monobank.transfer.JournalJson;

@Component
public class RestClient {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=20190904&json";
    private static final String MONOBANK_CURRENCY_IN_CURRENT_DATE = "https://api.monobank.ua/bank/currency";
    private static final String CURRENCY_IN_CURRENT_DATE = "http://bank-ua.com/export/exchange_rate_cash.json";

    private final RestTemplate restTemplate;
    private final ReferenceBookService referenceBookService;
    private final JsonToBook jsonToBook;
    private final JournalService journalService;
    private final JsonToJournal jsonToJournal;

    @Autowired
    public RestClient(RestTemplate restTemplate,
        ReferenceBookService referenceBookService, JsonToBook jsonToBook,
        JournalService journalService, JsonToJournal jsonToJournal) {
        this.restTemplate = restTemplate;
        this.referenceBookService = referenceBookService;
        this.jsonToBook = jsonToBook;
        this.journalService = journalService;
        this.jsonToJournal = jsonToJournal;
    }

    @PostConstruct
    @GetMapping("")
    private List<ReferenceBook> addBook() {
        BookJson[] list = restTemplate.getForObject(URL, BookJson[].class);
        for (BookJson bookJson : list) {
            ReferenceBook book = jsonToBook.convert(bookJson);
        }
        return referenceBookService.getAll();
    }

    @PostConstruct
    @Scheduled(cron = "0 00 17 ? * MON-FRI")
    public List<Journal> addCourse() {
        JournalJson[] journalJsons = restTemplate
            .getForObject(MONOBANK_CURRENCY_IN_CURRENT_DATE, JournalJson[].class);
        for (JournalJson json : journalJsons) {
            Journal journal = jsonToJournal.convert(json);
        }
        return journalService.getAll();
    }

}
