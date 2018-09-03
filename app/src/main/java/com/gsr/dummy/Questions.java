package com.gsr.dummy;

public class Questions {
    String query;
    String answer;

    public Questions() {
    }

    public Questions(String query, String answer) {
        this.query = query;
        this.answer = answer;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
