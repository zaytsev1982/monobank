package ua.com.monobank.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalJson {

    @JsonProperty(value = "currencyCodeA")
    private String code;
    @JsonProperty(value = "rateBuy")
    private String buy;
    @JsonProperty(value = "rateSell")
    private String sale;
    @JsonProperty(value = "date")
    private String currentDate;
}
