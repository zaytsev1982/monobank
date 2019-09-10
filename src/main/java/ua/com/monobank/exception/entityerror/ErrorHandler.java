package ua.com.monobank.exception.entityerror;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorHandler {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localDateTime;
    private String message;
    private List<String> details = new ArrayList<>();

    public void addDetails(String detail) {
        details.add("path : " + detail + " not found");
    }


}
