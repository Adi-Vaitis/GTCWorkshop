package com.auto.gtcworkshop.model;

import java.util.HashMap;
import java.util.Map;

public class Reservation {
    private String id;
    private String feel;
    private String model;
    private String problem;
    private String millage;

    public Reservation() {
    }

    public Reservation(String feel, String model, String millage, String problem) {
        this.feel = feel;
        this.model = model;
        this.millage = millage;
        this.problem = problem;

    }

    public String getId() {
        return id;
    }

    public String getFeel() {
        return feel;
    }

    public String getModel() {
        return model;
    }

    public String getProblem() {
        return problem;
    }

    public String getMillage() {
        return millage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setMillage(String millage) {
        this.millage = millage;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> output = new HashMap<>();
        output.put("feel", feel);
        output.put("model", model);
        output.put("problem", problem);
        output.put("millage", millage);


        return output;
    }


}
