package com.fatec.livrariaecommerce.controllers;

import com.fatec.livrariaecommerce.facade.IFacade;
import com.fatec.livrariaecommerce.models.domain.ItensPedido;
import com.fatec.livrariaecommerce.models.domain.Resultado;
import com.fatec.livrariaecommerce.models.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/itenspedidos")
public class ItensPedidoController {

    private final IFacade facade;

    // ***********************************************************************

    @PutMapping(path = "{idItemPedido}")
    public ResponseEntity<Message> solicitarTrocaItensPedido(@PathVariable int idItemPedido) {
        try {
            ItensPedido itensPedido = new ItensPedido();
            itensPedido.setId(idItemPedido);
            itensPedido = (ItensPedido) this.facade.consultar(itensPedido).getEntidades().get(0);

gi            Resultado resultado = this.facade.alterar(itensPedido);



            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
