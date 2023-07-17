package br.com.projeto.regpasweb.repositories;

import br.com.projeto.regpasweb.models.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

}
