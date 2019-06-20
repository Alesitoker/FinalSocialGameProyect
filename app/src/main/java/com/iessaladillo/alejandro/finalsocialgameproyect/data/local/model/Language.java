package com.iessaladillo.alejandro.finalsocialgameproyect.data.local.model;

public class Language {

    String cod_language;
    String language;

    public Language(String cod_language, String language) {
        this.cod_language = cod_language;
        this.language = language;
    }

    public String getCod_language() {
        return cod_language;
    }

    public String getLanguage() {
        return language;
    }
}
