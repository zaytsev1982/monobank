package ua.com.monobank.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.monobank.model.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    Journal findByCurrencyCodeAndDate(Integer code, LocalDate date);

    Journal findByCurrencyCode(Integer code);

    List<Journal> findAllByDate(LocalDate localDate);
}
