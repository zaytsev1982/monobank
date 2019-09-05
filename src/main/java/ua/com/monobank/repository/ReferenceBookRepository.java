package ua.com.monobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.monobank.model.ReferenceBook;

public interface ReferenceBookRepository extends JpaRepository<ReferenceBook, Long> {

    ReferenceBook findByMnemonic(String mnemonic);

}
