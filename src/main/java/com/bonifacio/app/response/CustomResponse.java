package com.bonifacio.app.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CustomResponse<T> {
    private String message;
    private boolean success;
    private T data;

}
