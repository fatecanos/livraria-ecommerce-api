package com.fatec.livrariaecommerce.models.utils;

import com.fatec.livrariaecommerce.models.domain.EntidadeDominio;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data @Service
public class Message {
    private String description;
    private String title;

    //TODO: modeling to receive generic DTO
    private EntidadeDominio dominio;
}
