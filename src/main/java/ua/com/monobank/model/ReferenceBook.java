package ua.com.monobank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "reference_book")
@Data
@ToString
public class ReferenceBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mnemonic", unique = true)
    private String mnemonic;
    @Column(name = "currency_code")
    private Integer currencyCode;
    @Column(name = "description")
    private String description;


}
