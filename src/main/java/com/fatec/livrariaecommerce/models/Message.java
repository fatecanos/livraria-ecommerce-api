package com.fatec.livrariaecommerce.models;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data @Service
public class Message {
    private String description;
    private String title;
}
