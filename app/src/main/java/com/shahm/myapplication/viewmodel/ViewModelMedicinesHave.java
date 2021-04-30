package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.MedicinesHave;
import com.shahm.myapplication.repositories.RepoMedicinesHave;

import java.util.List;

public class ViewModelMedicinesHave extends ViewModel {
    private RepoMedicinesHave repoMedicinesHave;

    public ViewModelMedicinesHave(){
        repoMedicinesHave = new RepoMedicinesHave();
    }
    public LiveData<List<MedicinesHave>> getMedicinesHave(int page){
        return repoMedicinesHave.getMedicinesHave(page);
    }
}
