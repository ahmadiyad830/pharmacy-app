package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.repositories.RepoMedicinesHave;

import java.util.List;

public class VMMedicinesHave extends ViewModel {
    private RepoMedicinesHave repoMedicinesHave;

    public VMMedicinesHave(){
        repoMedicinesHave = new RepoMedicinesHave();
    }
    public LiveData<List<Medicines>> getMedicinesHave(int page){
        return repoMedicinesHave.getMedicinesHave(page);
    }
}
