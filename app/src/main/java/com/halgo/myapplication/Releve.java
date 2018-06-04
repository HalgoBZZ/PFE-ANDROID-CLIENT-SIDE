package com.halgo.myapplication;

public class Releve {

    private long id;
    private String comment;
    private String pdl;
    private Long compteur;


    public Releve(){}

    public Releve(Long id, String comment, String pdl, Long compteur){
        this.id=id;
        this.pdl=pdl;
        this.comment=comment;
        this.compteur=compteur;
    }

    public Long getCompteur() {
        return compteur;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPdl() {
        return pdl;
    }

    public void setPdl(String pdl) {
        this.pdl = pdl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setCompteur(Long compteur) {
        this.compteur = compteur;
    }
}
