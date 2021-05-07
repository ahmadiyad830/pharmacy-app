package com.shahm.myapplication.network;

import com.shahm.myapplication.model.AnalyzeDepot;
import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.model.SalesPharmacy;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
//   all drug

    //    FragmentAllMedicines{@link FragmentAllMedicines}
    @GET("?alldrug=alldrug")
    Call<List<Medicines>> getListMedicines(@Query("alldrug") int page);

    //drug i have FragmentMedicinesHave
    @GET("?mydrug=mydrug")
    Call<List<Medicines>> getMedicinesHave(@Query("mydrug") int page);

    // sales in FragmentSales
    @GET("?sales=sales")
    Call<List<SalesPharmacy>> getSales(@Query("sales") int page);

    // sales on the name and month FragmentSales
//    name drug and number month

    @GET("?medicine=medicine&month=month")
    Call<List<AnalyzeDrug>> getSalesPharmacy(@Query("medicine") String name, @Query("month") String monthNum);

    // FragmentDepot
    @GET("?store=store&month=month")
    Call<List<AnalyzeDepot>> getDepot(@Query("store") String storeName, @Query("month") String monthNum);

    //    methods post data
    @POST("?adddrug/")
    Call<Void> postData(@Body Medicines model);



    @POST("?adddrug=modelMap")
    Call<Void> postMap(@FieldMap HashMap<String, String> modelMap);




    @POST("?adddrug&name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity")
    Call<Void> postNewDrug(@Query("name") String name,
                           @Query("scientific") String scientific,
                           @Query("concentration") String concentration,
                           @Query("dosageform") String dosageform,
                           @Query("notes") String notes,
                           @Query("store") String store,
                           @Query("sachet") String sachet,
                           @Query("slocation") String slocation,
                           @Query("squantity") String squantity);


    @PUT("?editdrug&itemid=itemid&name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity")
    Call<Void> postUpdateDrug(@Query("itemid") String id,
                              @Query("name") String name,
                              @Query("scientific") String scientific,
                              @Query("concentration") String concentration,
                              @Query("dosageform") String dosageform,
                              @Query("notes") String notes,
                              @Query("store") String store,
                              @Query("sachet") String sachet,
                              @Query("slocation") String slocation,
                              @Query("squantity") String squantity);

    // in details
    @PUT("?selldrug&itemid=itemid&price=price&issachet=issachet")
    Call<Void> postSaleDrug(@Query("itemid") String itemId, @Query("price") String price, @Query("issachet") String isSachet);

    // in salary details
    @PUT("?addpruch&itemid=itemid&number=number")
    Call<Void> postAddSales(@Query("itemid") String itemId, @Query("number") String number);

    //in sales pharmacy
    @DELETE("?deletesales&salesid=id")
    Call<Void> postDeleteSales(@Query("id") String salesId);


}
