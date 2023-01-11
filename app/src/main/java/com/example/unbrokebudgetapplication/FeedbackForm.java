package com.example.unbrokebudgetapplication;

public class FeedbackForm {

    private String opinion;
    private String point;
    public FeedbackForm(){}

    public FeedbackForm(String opinion, String point) {
        this.opinion = opinion;
        this.point = point;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
