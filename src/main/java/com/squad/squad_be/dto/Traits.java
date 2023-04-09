package com.squad.squad_be.dto;

public class Traits {
    private Double agreeableness;
    private Double conscientiousness;
    private Double extraversion;
    private Double neuroticism;
    private Double openness;

    public Traits() {
    }

    public Traits(Double extraversion, Double agreeableness, Double conscientiousness, Double neuroticism, Double openness) {
        this.extraversion = extraversion;
        this.agreeableness = agreeableness;
        this.conscientiousness = conscientiousness;
        this.neuroticism = neuroticism;
        this.openness = openness;
    }

    public Double getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(Double agreeableness) {
        this.agreeableness = agreeableness;
    }

    public Double getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(Double conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public Double getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(Double extraversion) {
        this.extraversion = extraversion;
    }

    public Double getNeuroticism() {
        return neuroticism;
    }

    public void setNeuroticism(Double neuroticism) {
        this.neuroticism = neuroticism;
    }

    public Double getOpenness() {
        return openness;
    }

    public void setOpenness(Double openness) {
        this.openness = openness;
    }

    @Override
    public String toString() {
        return "Traits{" +
                "agreeableness=" + agreeableness +
                ", conscientiousness=" + conscientiousness +
                ", extraversion=" + extraversion +
                ", neuroticism=" + neuroticism +
                ", openness=" + openness +
                '}';
    }
}
