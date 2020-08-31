package ru.zharnitskiy.voting.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DishTO {
    private Integer id;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    public DishTO(Integer id, String description, LocalDate date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }
}
