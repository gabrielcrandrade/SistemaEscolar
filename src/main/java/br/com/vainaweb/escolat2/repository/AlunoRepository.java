package br.com.vainaweb.escolat2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vainaweb.escolat2.model.AlunoModel;
@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long>{
	
	Optional<AlunoModel> findByCpf(String string);
}
