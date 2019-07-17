package com.tw.apistackbase.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Procuratorate {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Prosecutor> prosecutors;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Prosecutor> getProsecutors() {
        return prosecutors;
    }

    public void setProsecutors(List<Prosecutor> prosecutors) {
        this.prosecutors = prosecutors;
    }
}
