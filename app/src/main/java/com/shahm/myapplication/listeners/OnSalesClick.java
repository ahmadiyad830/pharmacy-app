package com.shahm.myapplication.listeners;

import com.shahm.myapplication.model.SalesPharmacy;

public interface OnSalesClick {
    void onItemClick(SalesPharmacy model);
    void deleteItem(String salesId,int position);
}
