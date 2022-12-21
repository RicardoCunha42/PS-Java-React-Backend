package br.com.banco.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaRepository transferenciaRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Transferencia> getTransferenciasPaginadas(Long idConta, 
        Optional<String> dataTransferencia, Optional<String> nome) {

            LocalDate date = null;
            if(dataTransferencia.isPresent()){
                String data = dataTransferencia.get();
                date = LocalDate.parse(data, formatter);
                
            }

            String nomeOperador = null;
            if(nome.isPresent()) {
                nomeOperador = nome.get();
            }      

            Sort sort = Sort.by("dataTransferencia").descending();
            PageRequest paginacao = PageRequest.of(0, 5, sort);

            List<Transferencia> transferencias = null;
            if(dataTransferencia.isPresent() && nome.isPresent()) {
                transferencias = this.transferenciaRepository.findByIdContaDataNome(idConta, date, nomeOperador, paginacao);

            } else if (dataTransferencia.isPresent() && nome.isEmpty()){
                transferencias = this.transferenciaRepository.findByIdContaData(idConta, date, paginacao);

            } else if (dataTransferencia.isEmpty() && nome.isPresent()){
                transferencias = this.transferenciaRepository.findByIdContaNome(idConta, nomeOperador, paginacao);
                
            } else {
                transferencias = this.transferenciaRepository.findByIdConta(idConta, paginacao);
            }

            return transferencias;
            
        }
}
