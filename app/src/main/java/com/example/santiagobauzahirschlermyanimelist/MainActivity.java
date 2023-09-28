package com.example.santiagobauzahirschlermyanimelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;


import com.example.santiagobauzahirschlermyanimelist.rest.adapters.AdapterAnimes;
import com.example.santiagobauzahirschlermyanimelist.rest.models.Animes;
import com.example.santiagobauzahirschlermyanimelist.rest.models.Datum;
import com.example.santiagobauzahirschlermyanimelist.rest.JikanMyAnimeListAPI;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private List<Datum> datos = new ArrayList<Datum>();

    private AdapterAnimes adapter = new AdapterAnimes(datos);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView lista = findViewById(R.id.lista_animes);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);


        downloadData();

    }

    private void downloadData() {

        //Retrofit

        /*JikanMyAnimeListAPI.getInstance().mostrarAnimesNombre("anime", "naruto").enqueue(new Callback<Animes>() {
            @Override
            public void onResponse(Call<Animes> call, Response<Animes> response) {

                Animes animes = response.body();

                for (Datum datum : animes.getData()) {
                    Log.i("TAG", datum.getTitle());

                    try {
                        Log.i("TAG",datum.getSynopsis()); // Pequeño error
                    }catch (Exception e){
                        System.out.println("Error");
                    }


                    Log.i("TAG", datum.getImages().getJpg().getImageUrl());
                    Log.v("TAG", datum.getUrl());
                    datos.add(datum);
                }
                updateUI();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Animes> call, Throwable t) {
                Log.e("TAG", "ERROR");
            }
        });*/

         /*JikanMyAnimeListAPI.getInstance().mostrarTopAnimesNombre().enqueue(new Callback<Animes>() {
            @Override
            public void onResponse(Call<Animes> call, Response<Animes> response) {
                System.out.println("TOP 25 animes");
                System.out.println("......");
                Animes animes = response.body();
                for (Datum datum : animes.getData()) {
                    Log.i("TAG", datum.getTitle());
                    Log.i("TAG","Puntuacion: "+datum.getScore());
                    Log.i("TAG",datum.getSynopsis());
                    Log.i("TAG", datum.getImages().getJpg().getImageUrl());
                    Log.v("TAG", datum.getUrl());
                    datos.add(datum);
                }
                updateUI();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Animes> call, Throwable t) {Log.e("TAG", "ERROR");}

        });*/


        //Retrofit + RxJava

        JikanMyAnimeListAPI.getInstance()
                .mostrarTopAnimesNombre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(x -> x.getData())
                .map(x -> {datos.add(x); return x;})
                .subscribe(x -> {
                    Log.i("TAG", x.getTitle());
                    adapter.notifyDataSetChanged();
                });



    }

    private void updateUI() {
        runOnUiThread(new Thread(() -> adapter.notifyDataSetChanged()));
    }
}