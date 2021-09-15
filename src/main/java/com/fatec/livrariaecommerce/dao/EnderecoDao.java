package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import com.fatec.livrariaecommerce.models.domain.Cliente;
import com.fatec.livrariaecommerce.models.domain.Endereco;
import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Service
public interface EnderecoDao extends JpaRepository<Endereco, Integer>, IDAO {

    @Override
    default EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Endereco) entidadeDominio);
    }

    @Override
    default EntidadeDominio alterar(EntidadeDominio entidadeDominio) {
        return this.saveAndFlush((Endereco) entidadeDominio);
    }

    @Override
    @Transactional
    @Modifying
    @Query("UPDATE " +
            "  #{#entityName} obj " +
            "SET " +
            "   obj.ativo = false " +
            "WHERE " +
            "   obj.id = ?#{[0].id}")
    void excluir(@Param("dominio") EntidadeDominio entidadeDominio);

    @Override
    @Query("SELECT " +
            "   obj " +
            "FROM " +
            "   #{#entityName} obj " +
            "WHERE " +
            "   (?#{[0].id} IS NOT NULL AND obj.id = ?#{[0].id}) " +
            "   OR (?#{[0].nome} IS NOT NULL AND obj.nome = ?#{[0].nome}) " +
            "   OR (?#{[0].logradouro} IS NOT NULL AND obj.logradouro = ?#{[0].logradouro}) " +
            "   OR (?#{[0].bairro} IS NOT NULL AND obj.bairro = ?#{[0].bairro}) " +
            "   OR (?#{[0].numero} IS NOT NULL AND obj.numero = ?#{[0].numero}) " +
            "   OR (?#{[0].cep} IS NOT NULL AND obj.cep = ?#{[0].cep}) " +
            "   OR (?#{[0].complemento} IS NOT NULL AND obj.complemento = ?#{[0].complemento}) " +
            "   OR (?#{[0].cliente} IS NOT NULL AND ( " +
            "           (?#{[0].cliente.id} IS NOT NULL AND obj.cliente.id = ?#{[0].cliente.id}) " +
            "       )) " +
            "ORDER BY " +
            "   obj.id" +
            "")
    List<EntidadeDominio> consultar(EntidadeDominio entidadeDominio);
}
