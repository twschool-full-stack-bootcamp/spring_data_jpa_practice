package com.tw.apistackbase.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CriminalCase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @NotNull
    private String name;
    @NotNull
    private Long time;

    @OneToOne(cascade = CascadeType.ALL)
    private CriminalElements criminalElements;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Procuratorate procuratorate;

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public CriminalElements getCriminalElements() {
        return criminalElements;
    }

    public void setCriminalElements(CriminalElements criminalElements) {
        this.criminalElements = criminalElements;
    }

    public Procuratorate getProcuratorate() {
        return procuratorate;
    }

    public void setProcuratorate(Procuratorate procuratorate) {
        this.procuratorate = procuratorate;
    }
}
