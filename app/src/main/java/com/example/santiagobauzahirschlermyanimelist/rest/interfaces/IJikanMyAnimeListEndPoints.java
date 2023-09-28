package com.example.santiagobauzahirschlermyanimelist.rest.interfaces;

import com.example.santiagobauzahirschlermyanimelist.rest.models.Animes;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IJikanMyAnimeListEndPoints {

    // nomenclaturas
    //https://jikan.docs.apiary.io/#reference/0/search/manga-request-example+schema?console=1

    //Rtrofit

    //Mostrar animes por nombre
    @GET("{tipo}")
    Call<Animes> mostrarAnimesNombre(@Path("tipo") String tipo,
                                     @Query("q") String nombre);

    //Retrofit + Rxjava
    //Mostrar el top de animes
    @GET("top/anime")
    Observable<Animes> mostrarTopAnimesNombre();

}
