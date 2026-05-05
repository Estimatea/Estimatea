package com.example.estimatea.model;

public class Ressource {
    int ressourceId;
    String ressourceName;
    int ressourceRate;

    public Ressource(int ressourceId, String ressourceName, int ressourceRate) {
        this.ressourceId = ressourceId;
        this.ressourceName = ressourceName;
        this.ressourceRate = ressourceRate;
    }

    public Ressource() {

    }

    public int getRessourceId() {
        return ressourceId;
    }

    public void setRessourceId(int ressourceId) {
        this.ressourceId = ressourceId;
    }

    public String getRessourceName() {
        return ressourceName;
    }

    public void setRessourceName(String ressourceName) {
        this.ressourceName = ressourceName;
    }

    public int getRessourceRate() {
        return ressourceRate;
    }

    public void setRessourceRate(int ressourceRate) {
        this.ressourceRate = ressourceRate;
    }
}
