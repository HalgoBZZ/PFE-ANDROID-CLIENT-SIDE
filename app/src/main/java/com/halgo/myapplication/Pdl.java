package com.halgo.myapplication;

public class Pdl {
    private Long id;
    private String reference;
    private Long ordre;
    private String adresse;
    private String rendezVous;
    private String etat;
    private String access;
    private String tournee;

    public Pdl(){}

    public Pdl(Long id, String reference, Long ordre, String adresse, String rendezVous, String etat, String access, String tournee){
        this.id=id;
        this.reference=reference;
        this.access=access;
        this.adresse=adresse;
        this.ordre=ordre;
        this.rendezVous=rendezVous;
        this.etat=etat;
        this.tournee=tournee;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setOrdre(Long ordre){
        this.ordre=ordre;
    }

    public Long getOrdre() {
        return ordre;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setRendezVous(String rendezVous) {
        this.rendezVous = rendezVous;
    }

    public String getRendezVous() {
        return rendezVous;
    }

    public String getTournee(){
        return tournee;
    }

    public void setTournee(String tournee){
        this.tournee=tournee;
    }
}
