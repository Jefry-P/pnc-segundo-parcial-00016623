package com.uca.pncsegundoparcialbiblioteca.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralResponse {
    Object data;
    String message;
}