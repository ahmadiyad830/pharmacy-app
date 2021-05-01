package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.MedicinesHave;
import com.shahm.myapplication.repositories.RepoMedicinesHave;

import java.util.List;

public class VMMedicinesHave extends ViewModel {
    private RepoMedicinesHave repoMedicinesHave;

    public VMMedicinesHave(){
        repoMedicinesHave = new RepoMedicinesHave();
    }
    public LiveData<List<MedicinesHave>> getMedicinesHave(int page){
        return repoMedicinesHave.getMedicinesHave(page);
    }
}
