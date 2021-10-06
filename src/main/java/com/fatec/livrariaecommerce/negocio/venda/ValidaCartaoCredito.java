package com.fatec.livrariaecommerce.negocio.venda;

import com.fatec.livrariaecommerce.dao.CartaoCreditoDao;
import com.fatec.livrariaecommerce.dao.VendaDao;
import com.fatec.livrariaecommerce.models.domain.*;
import com.fatec.livrariaecommerce.negocio.IStrategy;

import java.util.List;

public class ValidaCartaoCredito implements IStrategy {

    public final CartaoCreditoDao cartaoCreditoDao;

    public ValidaCartaoCredito(CartaoCreditoDao cartaoCreditoDao) {
        this.cartaoCreditoDao = cartaoCreditoDao;
    }


    @Override
    public String processar(EntidadeDominio dominio) {
        Venda venda = (Venda) dominio;

        for (FormaPagamento formaPagamento : venda.getFormaPagamentoList()) {
            CartaoCredito cartaoCredito = new CartaoCredito();
            cartaoCredito.setId(formaPagamento.getIdCartao());

            CartaoCredito cartao = (CartaoCredito) this.cartaoCreditoDao.consultar(cartaoCredito).get(0);
            System.out.println("Me diz esse cartao: " + cartao.getNumeroCartao());

            //TODO: ALTERAR O STATUS DA VENDA PARA PAGAMENTO_REALIZADO

            if(!validateCreditCardNumber(cartao.getNumeroCartao())){
                return "Cartão inválido";
            }
        }

        return "";
    }

    private boolean validateCreditCardNumber(String str) {

        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            //Valid credit card
            return true;
        } else {
            //Invalid credit card
            return false;
        }
    }

}
