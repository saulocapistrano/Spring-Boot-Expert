package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

Optional<User> findByLogin(String login);

}
