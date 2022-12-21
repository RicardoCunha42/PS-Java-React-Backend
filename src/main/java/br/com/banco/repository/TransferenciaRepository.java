package br.com.banco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{
    
    @Query("select t from Transferencia t join fetch t.conta c where c.idConta = :idConta and t.nomeOperador = :nome" +
    " and t.dataTransferencia like concat(:date,'%')")
    List<Transferencia> findByIdContaDataNome(Long idConta, LocalDate date, String nome, Pageable paginacao);
    
    @Query("select t from Transferencia t join fetch t.conta c where c.idConta = :idConta" + 
    " and t.dataTransferencia like concat(:date,'%')")
    List<Transferencia> findByIdContaData(Long idConta, LocalDate date, Pageable paginacao);

    @Query("select t from Transferencia t join fetch t.conta c where c.idConta = :idConta and t.nomeOperador = :nome")
    List<Transferencia> findByIdContaNome(Long idConta, String nome, Pageable paginacao);
    
    @Query("select t from Transferencia t join fetch t.conta c where c.idConta = :idConta")
    List<Transferencia> findByIdConta(Long idConta, Pageable paginacao);
    
}
