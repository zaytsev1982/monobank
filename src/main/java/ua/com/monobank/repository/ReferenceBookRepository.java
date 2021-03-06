package ua.com.monobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.monobank.model.ReferenceBook;

@Repository
public interface ReferenceBookRepository extends JpaRepository<ReferenceBook, Long> {

    ReferenceBook findByMnemonic(String mnemonic);

    Integer findByCurrencyCode(Integer code);

}
