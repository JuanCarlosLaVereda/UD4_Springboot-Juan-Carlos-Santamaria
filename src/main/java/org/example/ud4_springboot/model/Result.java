package org.example.ud4_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

public abstract class Result {

    @Data
    @AllArgsConstructor
    public static  class Success<T>{
        private T data;
    }
    @Data
    @AllArgsConstructor
    public static  class Error<T>{
        private String error;
    }

}
