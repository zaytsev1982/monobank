package ua.com.monobank.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookJson {

    @JsonProperty(value = "cc")
    private String mnemonic;
    @JsonProperty(value = "r030")
    private String code;
    @JsonProperty(value = "txt")
    private String description;


}
