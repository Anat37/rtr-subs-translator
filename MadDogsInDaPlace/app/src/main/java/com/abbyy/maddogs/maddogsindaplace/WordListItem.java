package com.abbyy.maddogs.maddogsindaplace;


public class WordListItem {

    private String Heading;
    private String Translation;
    private String DictionaryName;
    private String SoundName;
    private Integer Type;
    private String OriginalWord;

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String Heading) {
        this.Heading = Heading;
    }

    public String getTranslation() {
        return Translation;
    }

    public void setTranslation(String Translation) {
        this.Translation = Translation;
    }

    public String getDictionaryName() {
        return DictionaryName;
    }

    public void setDictionaryName(String DictionaryName) {
        this.DictionaryName = DictionaryName;
    }

    public String getSoundName() {
        return SoundName;
    }

    public void setSoundName(String SoundName) {
        this.SoundName = SoundName;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type) {
        this.Type = Type;
    }

    public String getOriginalWord() {
        return OriginalWord;
    }

    public void setOriginalWord(String OriginalWord) {
        this.OriginalWord = OriginalWord;
    }

}