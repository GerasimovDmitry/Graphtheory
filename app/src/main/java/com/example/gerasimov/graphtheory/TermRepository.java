package com.example.gerasimov.graphtheory;

public class TermRepository {
    private long id;
    private String term;
    private String det;

    TermRepository(long id, String term, String det){
        this.id = id;
        this.term = term;
        this.det= det;
    }
    public long getId() {
        return id;
    }
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDet() {
        return det;
    }

    public void setDet(String det) {
        this.det = det;
    }

    @Override
    public String toString() {
        return this.term;
    }
}

