package com.candenizgumus.dto;

import org.springframework.web.bind.annotation.RequestParam;

public record FindAllByPageRequestDto( int page,  int size,  String sortParameter , String sortDirection
)
{
}
