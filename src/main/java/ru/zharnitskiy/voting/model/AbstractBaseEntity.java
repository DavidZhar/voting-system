package ru.zharnitskiy.voting.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@MappedSuperclass
public class AbstractBaseEntity implements Persistable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Integer id;

    protected AbstractBaseEntity() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
