package com.squad.squad_be.dto;

public class Traits {
    private Float extraversion;
    private Float agreeableness;
    private Float conscientiousness;
    private Float neuroticism;
    private Float openness;

    public Traits() {
    }

    public Traits(Float extraversion, Float agreeableness, Float conscientiousness, Float neuroticism, Float openness) {
        this.extraversion = extraversion;
        this.agreeableness = agreeableness;
        this.conscientiousness = conscientiousness;
        this.neuroticism = neuroticism;
        this.openness = openness;
    }

    public Float getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(Float extraversion) {
        this.extraversion = extraversion;
    }

    public Float getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(Float agreeableness) {
        this.agreeableness = agreeableness;
    }

    public Float getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(Float conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public Float getNeuroticism() {
        return neuroticism;
    }

    public void setNeuroticism(Float neuroticism) {
        this.neuroticism = neuroticism;
    }

    public Float getOpenness() {
        return openness;
    }

    public void setOpenness(Float openness) {
        this.openness = openness;
    }

    @Override
    public String toString() {
        return "Traits{" +
                "extraversion=" + extraversion +
                ", agreeableness=" + agreeableness +
                ", conscientiousness=" + conscientiousness +
                ", neuroticism=" + neuroticism +
                ", openness=" + openness +
                '}';
    }
}
