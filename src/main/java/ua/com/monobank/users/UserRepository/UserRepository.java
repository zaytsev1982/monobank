package ua.com.monobank.users.UserRepository;

import java.util.Optional;
import javax.jws.soap.SOAPBinding.Use;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.monobank.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.login=:login")
    Optional<User> findByLogin(@Param("login") String login);
}
