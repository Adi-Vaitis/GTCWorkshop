package com.auto.gtcworkshop.model;

import java.util.HashMap;
import java.util.Map;

public class Reservation {
    public String userId;
    private String id;
    private String feel;
    private String model;
    private String problem;
    private String millage;

    public Reservation() {
    }

    public Reservation(String feel, String model, String problem, String millage) {
        this.feel = feel;
        this.model = model;
        this.problem = problem;
        this.millage = millage;
    }

    public Reservation(String userId, String feel, String model, String problem, String millage) {
        this.userId = userId;
        this.feel = feel;
        this.model = model;
        this.problem = problem;
        this.millage = millage;
    }

    public String getUserId() {
        return userId;
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
