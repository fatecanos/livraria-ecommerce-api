package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.Cidade;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.Estado;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.dto.CidadeDTO;
import com.fatec.livrariaecommerce.models.dto.EnderecoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/cidades")
public class CidadeController {

    private final IFacade facade;

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> getAllCitiesFromDatabase(@Param("estadoID") int estadoID) {
        try {

            Estado estado = new Estado();
            estado.setId(estadoID);

            Cidade cidade = new Cidade();
            cidade.setEstado(estado);

            List<CidadeDTO> cidades = this.facade.consultar(cidade).getEntidades().stream().map(cid -> {
                return CidadeDTO.from((Cidade) cid);
            }).collect(Collectors.toList());

            return ResponseEntity.ok(cidades);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
