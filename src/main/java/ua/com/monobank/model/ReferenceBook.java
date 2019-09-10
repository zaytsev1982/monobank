package ua.com.monobank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "reference_book")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mnemonic", unique = true)
    private String mnemonic;
    @Column(name = "currency_code", unique = true)
    private Integer currencyCode;
    @Column(name = "description", unique = true)
    private String description;


}
