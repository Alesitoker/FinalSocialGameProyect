package com.iessaladillo.alejandro.finalsocialgameproyect.data;

import androidx.lifecycle.LiveData;

import com.iessaladillo.alejandro.finalsocialgameproyect.data.local.model.Game;
import com.iessaladillo.alejandro.finalsocialgameproyect.data.local.model.Group;
import com.iessaladillo.alejandro.finalsocialgameproyect.data.local.model.Language;

import java.util.List;

public interface Repository {

    //Group
    void createGroup();
    LiveData<List<Group>> queryGroups();

    //Language
    LiveData<List<Language>> queryLanguage();

    //Game
    LiveData<List<Game>> queryGame();

    //

}
