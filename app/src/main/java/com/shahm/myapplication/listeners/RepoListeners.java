package com.shahm.myapplication.listeners;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.model.MedicinesHave;

public  class RepoListeners  {
    private static RepoListeners listeners;
    public void getMedicines (OnMedClick onMedClick, Medicines model){
        onMedClick.onItemClick(model);
    }
   public void getMedicinesHave(OnMedHaveClick onMedHaveClick, MedicinesHave model){
        onMedHaveClick.onMedHavClick(model);
   }
   public static RepoListeners getInstance(){
        if (listeners==null){
            listeners = new RepoListeners();
        }
        return listeners;
   }


}
