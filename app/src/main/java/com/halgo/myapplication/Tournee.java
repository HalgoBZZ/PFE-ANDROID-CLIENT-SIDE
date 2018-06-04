package com.halgo.myapplication;

/**
 * Created by Halgo on 28/11/2017.
 */

public class Tournee {
        private String name, secteur;
        private Long id;

        public Tournee() {
        }

        public Tournee(Long id, String name, String secteur) {
           this.id=id;
           this.name=name;
           this.secteur=secteur;
        }

        public Long getId(){return  id;}

        public void setId(Long id){this.id=id;}
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name=name;
        }

        public String getSecteur() {
            return secteur;
        }

        public void setSecteur(String secteur) {
            this.secteur=secteur;
        }

    }

