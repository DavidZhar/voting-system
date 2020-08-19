package ru.zharnitskiy.voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="dishes")
public class Dish {
    @Id
    protected Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "description", nullable = false)
    protected String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rest_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    protected Date date = new Date();
}
