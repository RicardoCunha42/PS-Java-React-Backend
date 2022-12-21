package br.com.banco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;

@Controller
@RequestMapping("/transferencias")
public class TransferenciaController {
    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    TransferenciaService transferenciaService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Transferencia>> getTransferencias(@RequestParam Long idConta,
        @RequestParam Optional<String> dataTransferencia, @RequestParam Optional<String> nome) {
            
        List<Transferencia> transferencias = this.transferenciaService
                                            .getTransferenciasPaginadas(idConta, dataTransferencia, nome);

        return new ResponseEntity<List<Transferencia>>(transferencias, HttpStatus.OK);
    }
}
