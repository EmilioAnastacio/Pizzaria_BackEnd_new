package br.com.projeto.pizzaria.repository;

import br.com.projeto.pizzaria.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {

    /*@Query(value = "SELECT l.usuario FROM User l WHERE l.usuario.nome = :nomeDeLogin")
    List<User> findUserNameByNomeDeLogin(@Param("nomeDeLogin") String nomeDeLogin);

    @Query(value = "SELECT l.funcionario FROM User l WHERE l.funcionario.nome = :nomeDeLogin")
    List<User> findUserNameByNomeDeLoginFuncionario(@Param("nomeDeLogin") String nomeDeLogin);
    */

    //User findByUsername(String username);

    public Optional<User> findByUsername(String login);
}
