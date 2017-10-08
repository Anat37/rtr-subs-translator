package com.abbyy.maddogs.maddogsindaplace;

import java.util.List;

public class Minicard {

    private Integer SourceLanguage;
    private Integer TargetLanguage;
    private String Heading;
    private WordListItem Translation;
    private List<String> SeeAlso = null;

    public Integer getSourceLanguage() {
        return SourceLanguage;
    }

    public void setSourceLanguage(Integer sourceLanguage) {
        this.SourceLanguage = sourceLanguage;
    }

    public Integer getTargetLanguage() {
        return TargetLanguage;
    }

    public void setTargetLanguage(Integer targetLanguage) {
        this.TargetLanguage = targetLanguage;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        this.Heading = heading;
    }

    public WordListItem getTranslation() {
        return Translation;
    }

    public void setTranslation(WordListItem Translation) {
        this.Translation = Translation;
    }

    public List<String> getSeeAlso() {
        return SeeAlso;
    }

    public void setSeeAlso(List<String> seeAlso) {
        this.SeeAlso = seeAlso;
    }

}