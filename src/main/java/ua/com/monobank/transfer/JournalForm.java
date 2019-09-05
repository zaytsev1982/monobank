package ua.com.monobank.transfer;

import lombok.Builder;
import lombok.Data;
import ua.com.monobank.model.Journal;

@Data
@Builder
public class JournalForm {

    private String buy;
    private String sale;

    public static JournalForm from(Journal journal) {
        return JournalForm.builder()
            .buy(String.valueOf(journal.getBuy()))
            .sale(String.valueOf(journal.getSale()))
            .build();
    }

}
