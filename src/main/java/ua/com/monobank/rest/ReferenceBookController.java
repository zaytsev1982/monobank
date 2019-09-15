package ua.com.monobank.rest;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.monobank.model.ReferenceBook;
import ua.com.monobank.service.ReferenceBookService;

@RestController
@RequestMapping("/api/")
@Slf4j
public class ReferenceBookController {

    private final ReferenceBookService referenceBookService;

    public ReferenceBookController(
        ReferenceBookService referenceBookService) {
        this.referenceBookService = referenceBookService;
    }

    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReferenceBook> addBook(@RequestBody ReferenceBook referenceBook) {
        if (referenceBook == null) {
            log.info("IN ReferenceBookController addBook bed request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReferenceBook book = referenceBookService.create(referenceBook);
        log.info("IN ReferenceBookController addBook {}", book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping(value = "reference-book", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ReferenceBook>> listBook() {
        List<ReferenceBook> books = referenceBookService.getAll();
        if (books.isEmpty()) {
            log.info("IN ReferenceBookController listBook is empty");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("IN ReferenceBookController listBook, size - {}", books.size());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
