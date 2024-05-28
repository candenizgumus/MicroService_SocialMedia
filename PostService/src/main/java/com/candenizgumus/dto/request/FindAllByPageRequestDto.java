package com.candenizgumus.dto.request;

public record FindAllByPageRequestDto(int page, int size, String sortParameter , String sortDirection
)
{
}
