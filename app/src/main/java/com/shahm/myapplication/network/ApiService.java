package com.shahm.myapplication.network;

import com.shahm.myapplication.model.AnalyzeDepot;
import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.model.SalesPharmacy;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
//    https://www.blacktools.io/sapi/?adddrug&name=1&scientific=1&concentration=1&dosageform=1&notes=1&store=1&sachet=1&slocation=1&squantity=1
//    https://www.blacktools.io/sapi/?adddrug&name=test&scientific=test&concentration=test&dosageform=test&notes=test&store=test&sachet=test&slocation=1&squantity=1
//    {{name=name},{scientific=scientific},{concentration=concentration},{dosageform=dosageform},{notes=notes},{store=store},{sachet=sachet},{slocation=slocation},{squantity=squantity}}
//    @Headers("{Accept: application/json}")

    //    ?adddrug=name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity
//&name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity
    @POST("?adddrug/")
    Call<Void> postData(@Body Medicines model);

    /* @POST("?adddrug&name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity")
     Call<Object> postData(@Body Medicines model);*/
    @FormUrlEncoded
    @POST("?adddrug=modelMap")
    Call<Void> postMap(@FieldMap HashMap<String, String> modelMap);

   /* @POST("?adddrug&name=name&scientific=scientific&concentration=concentration&dosageform=dosageform&notes=notes&store=store&sachet=sachet&slocation=slocation&squantity=squantity")
    Call<Medicines> postData(@Query("name")             String name,
                             @Query("scientific")       String scientific,
                             @Query("concentration")     String concentration,
                             @Query("dosageform")         String dosageform,
                             @Query("notes")              String notes,
                             @Query("store")             String store,
                             @Query("sachet")           String sachet,
                             @Query("slocation")        String slocation,
                             @Query("squantity")         String squantity           );*/


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

    @PUT("?selldrug&itemid=itemid&price=price&issachet=issachet")
    Call<Void> saleDrug(@Query("itemid") String sachet, @Query("price") String slocation, @Query("issachet") String squantity);

    @PUT("?addpruch&itemid=itemid&number=number")
    Call<Void> postAddSales(@Query("itemid") String sachet, @Query("number") String slocation);

    @PUT("?deletesales&salesid=salesid")
    Call<Void> postDeleteSales(@Query("salesid") String salesId);


}
//https://www.blacktools.io/api/?drug=3