package ua.com.monobank.repository;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.monobank.model.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {


    Journal findByCurrencyCodeAndDate(Integer code, Date date);
}
