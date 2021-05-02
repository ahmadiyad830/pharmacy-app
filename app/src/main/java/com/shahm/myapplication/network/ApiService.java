package com.shahm.myapplication.network;

import com.shahm.myapplication.model.Depot;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.model.MedicinesHave;
import com.shahm.myapplication.model.SalesMed;
import com.shahm.myapplication.model.SalesPharmacy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("?alldrug=alldrug")
    Call<List<Medicines>> getListMedicines(@Query("alldrug") int page);

    @GET("?mydrug=mydrug")
    Call<List<MedicinesHave>> getMedicinesHave(@Query("mydrug") int page);

    @GET("?sales=sales")
    Call<List<SalesMed>> getSales(@Query("sales") int page);

    @GET("?store=name&month=m")
    Call<List<SalesPharmacy>> getSalesPharmacy(@Query("name") String name, @Query("m") int monthNum);

    @GET("?store=store&month=month")
    Call<List<Depot>> getDepot(@Query("store")String storeName,@Query("month") String monthNum);

    @GET("api/?drug=3")
    Call<List<Medicines>> getMed(@Query("drug") int id);
}
//https://www.blacktools.io/api/?drug=3