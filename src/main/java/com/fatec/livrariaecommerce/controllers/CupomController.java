package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.models.dto.CupomDTO;
import com.fatec.livrariaecommerce.models.dto.TelefoneDTO;
import com.fatec.livrariaecommerce.models.dto.VendaDTO;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/cupom")
public class CupomController {

    private final IFacade facade;

    @PostMapping(path = "{usuarioID}")
    public ResponseEntity<Message> salvarCupom(@PathVariable int usuarioID, @RequestBody CupomDTO cupomDTO) {
        try {

            Usuario usuario = new Usuario();
            usuario.setId(usuarioID);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            Cupom cupom = new Cupom();
            cupomDTO.fill(cupom, cliente);
            Resultado resultado = this.facade.salvar(cupom);

            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Cupom cadastrado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Erro ao cadastrar cupom!");
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/salvarpromocional")
    public ResponseEntity<Message> salvarCupomPromocional(@RequestBody CupomDTO cupomDTO) {

        try {
            Cupom cupom = new Cupom();
            cupomDTO.fill(cupom, new Cliente());
            Resultado resultado = this.facade.salvar(cupom);

            Message message = new Message();
            if (resultado.getMensagem() == null) {
                message.setTitle("Sucesso!");
                message.setDescription("Cupom promocional cadastrado com sucesso!");
                return ResponseEntity.ok(message);
            } else {
                message.setTitle("Erro!");
                message.setDescription("Erro ao cadastrar cupom!");
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CupomDTO>> consultarCupom() {
        try {
            Cupom cupom = new Cupom();
            List<CupomDTO> cupomDTOList = this.facade.consultar(cupom).getEntidades().stream().map(cp -> {
                return CupomDTO.from((Cupom) cp);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(cupomDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<CupomDTO>> consultarCupomCliente(@PathVariable("idUsuario") int idUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);

            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente = (Cliente) this.facade.consultar(cliente).getEntidades().get(0);

            Cupom cupom = new Cupom(cliente);

            List<CupomDTO> cupomDTOList = this.facade.consultar(cupom).getEntidades().stream().map(cp -> {
                return CupomDTO.from((Cupom) cp);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(cupomDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/pesquisarcodigo/{codigo}")
    public ResponseEntity<CupomDTO> consultarCupomPeloCodigo(@PathVariable("codigo") String codigo) {
        try {

            Cupom cupom = new Cupom();
            cupom.setCodigo(codigo);
            cupom.setTipoCupom(TipoCupom.PROMOCIONAL);
            Resultado resultado = this.facade.consultar(cupom);
            cupom = (Cupom) resultado.getEntidades().get(0);
            return ResponseEntity.ok(CupomDTO.from(cupom));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/consultarpromocionais")
    public ResponseEntity<List<CupomDTO>> consultarCupomPromocional() {
        try {

            Cupom cupom = new Cupom();
            cupom.setTipoCupom(TipoCupom.PROMOCIONAL);
            List<CupomDTO> cupomDTOList = this.facade.consultar(cupom).getEntidades().stream().map(cp -> {
                return CupomDTO.from((Cupom) cp);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(cupomDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
