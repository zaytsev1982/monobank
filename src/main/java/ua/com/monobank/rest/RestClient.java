package ua.com.monobank.rest;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.monobank.converter.JsonToBook;
import ua.com.monobank.converter.JsonToJournal;
import ua.com.monobank.exception.ClientConnectException;
import ua.com.monobank.model.Journal;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.service.JournalService;
import ua.com.monobank.service.ReferenceBookService;
import ua.com.monobank.transfer.BookJson;
import ua.com.monobank.transfer.JournalJson;

@Component
@PropertySource("classpath:/json/uri.properties")
public class RestClient {

    @Value("${spring.connect.url}")
    private String reference;
    @Value("${MONOBANK_CURRENCY_IN_CURRENT_DATE}")
    private String exchangeRate;

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
    private List<ReferenceBook> addBook() {


        BookJson[] list = restTemplate.getForObject(reference, BookJson[].class);
        if (list.length == 0) {
            throw new ClientConnectException("does not connect to server");
        }
        for (BookJson bookJson : list) {
            ReferenceBook book = jsonToBook.convert(bookJson);
        }
        return referenceBookService.getAll();
    }

    @PostConstruct
    @Scheduled(cron = "0 00 17 ? * MON-FRI")
    public List<Journal> addCourse() {
        JournalJson[] journalJsons = restTemplate
            .getForObject(exchangeRate, JournalJson[].class);
        if (journalJsons.length == 0) {
            throw new ClientConnectException("does not connect to server");
        }

        for (JournalJson json : journalJsons) {
            Journal journal = jsonToJournal.convert(json);
        }
        return journalService.getAll();
    }

}
