package ua.com.monobank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "journal")
@Data
@ToString
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "current")
    private String date;
    @Column(name = "currency_code")
    private Integer currencyCode;
    @Column(name = "buy")
    private Double buy;
    @Column(name = "sale")
    private Double sale;

}
