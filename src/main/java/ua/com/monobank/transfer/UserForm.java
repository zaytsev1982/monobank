package ua.com.monobank.transfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserForm {

    private String login;
    private String password;

}
