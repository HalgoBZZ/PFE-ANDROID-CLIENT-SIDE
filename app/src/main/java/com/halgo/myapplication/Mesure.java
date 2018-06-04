package com.halgo.myapplication;

public class Mesure {

    private Long id;
    private String releve;
    private String code,name;

    public Mesure(){}

    public Mesure(Long id, String releve, String code, String name){
        this.id=id;
        this.releve=releve;
        this.code=code;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleve() {
        return releve;
    }

    public void setReleve(String releve) {
        this.releve = releve;
    }
}
