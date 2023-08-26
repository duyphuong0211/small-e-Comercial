package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {

    private List<SearchRequestDto> searchRequestDto;

    private Operator operator;

    private PageRequestDto pageDto;

    public enum Operator {
        AND, OR;
    }
}
