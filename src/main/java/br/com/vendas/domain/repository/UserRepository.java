package br.com.vendas.domain.repository;

import br.com.vendas.domain.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AuthUser, Integer> {

    Optional<AuthUser> findByLogin(String login);

}
