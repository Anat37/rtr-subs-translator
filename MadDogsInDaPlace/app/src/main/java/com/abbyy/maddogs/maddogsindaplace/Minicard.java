package com.abbyy.maddogs.maddogsindaplace;

import java.util.List;

public class Minicard {

    private Integer sourceLanguage;
    private Integer targetLanguage;
    private String heading;
    private Translation translation;
    private List<Object> seeAlso = null;

    public Integer getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(Integer sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public Integer getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(Integer targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public List<Object> getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(List<Object> seeAlso) {
        this.seeAlso = seeAlso;
    }

}