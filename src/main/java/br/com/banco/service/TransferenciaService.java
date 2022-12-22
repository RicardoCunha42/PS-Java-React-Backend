package br.com.banco.service;

import java.time.OffsetDateTime;
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
    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public List<Transferencia> getTransferenciasPaginadas(Long idConta, 
        Optional<String> dataInicio, Optional<String> dataFim, Optional<String> nome) {

            boolean datasPresentes = false;
            OffsetDateTime dataInicial = null;
            OffsetDateTime dataFinal = null;
            if(dataInicio.isPresent() && dataFim.isPresent()){
                String data1 = dataInicio.get() + "T00:00:00+03:00";
                String data2 = dataFim.get() + "T23:59:59+03:00";
                dataInicial = OffsetDateTime.parse(data1, formatter);
                dataFinal = OffsetDateTime.parse(data2, formatter);
                datasPresentes = true;
            }

            String nomeOperador = null;
            if(nome.isPresent()) {
                nomeOperador = nome.get();
            }      

            Sort sort = Sort.by("dataTransferencia").descending();
            PageRequest paginacao = PageRequest.of(0, 5, sort);

            List<Transferencia> transferencias = null;
            if(datasPresentes && nome.isPresent()) {
                transferencias = this.transferenciaRepository.findByIdContaDataNome(idConta, dataInicial, dataFinal, nomeOperador, paginacao);

            } else if (datasPresentes && nome.isEmpty()){
                transferencias = this.transferenciaRepository.findByIdContaData(idConta, dataInicial, dataFinal, paginacao);

            } else if (!datasPresentes && nome.isPresent()){
                transferencias = this.transferenciaRepository.findByIdContaNome(idConta, nomeOperador, paginacao);
                
            } else {
                transferencias = this.transferenciaRepository.findByIdConta(idConta, paginacao);
            }

            return transferencias;
            
        }
}
