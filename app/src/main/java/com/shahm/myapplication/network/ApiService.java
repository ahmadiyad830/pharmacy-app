package com.shahm.myapplication.network;

import com.shahm.myapplication.model.AnalyzeDepot;
import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.model.MedicinesHave;
import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.responses.ResponseMedicines;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
//   all drug

    //    FragmentAllMedicines{@link FragmentAllMedicines}
    @GET("?alldrug=alldrug")
    Call<List<Medicines>> getListMedicines(@Query("alldrug") int page);

    //drug i have FragmentMedicinesHave
    @GET("?mydrug=mydrug")
    Call<List<MedicinesHave>> getMedicinesHave(@Query("mydrug") int page);

    // sales in FragmentSales
    @GET("?sales=sales")
    Call<List<SalesPharmacy>> getSales(@Query("sales") int page);

    // sales on the name and month FragmentSales
//    name drug and number month
//    ?medicine=panadol&month=3
    @GET("?medicine=medicine&month=month")
    Call<List<AnalyzeDrug>> getSalesPharmacy(@Query("medicine") String name, @Query("month") String monthNum);

    // FragmentDepot
    @GET("?store=store&month=month")
    Call<List<AnalyzeDepot>> getDepot(@Query("store") String storeName, @Query("month") String monthNum);

    //    methods post data
//    &name=test&scientific=test&concentration=test&dosageform=test&notes=test&store=test&sachet=test&slocation=1&squantity=11
//
//
//    Call<List<Medicines>> postMed(@Body Medicines medicines);
//    @POST("?adddrug&name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity")

    @POST("?adddrug&name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity")
    Call<ResponseMedicines> postData(@Body Medicines model);



    /*
    * @Query("name") String name,
@Query("scientific") String scientific,
@Query("concentration") String concentration,
@Query("dosageform") String dosageform,
@Query("notes") String notes,
@Query("store") String store,
@Query("sachet") String sachet,
@Query("slocation") String slocation,
@Query("squantity") String squantity
    *
    * */
}
//https://www.blacktools.io/api/?drug=3