package com.sber.filmlibrary.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends GenericDTO{
    private Long userId;
    private Long filmId;
    private LocalDateTime rentDate;
    private Integer rentPeriod;
    private Boolean purchase;
}
