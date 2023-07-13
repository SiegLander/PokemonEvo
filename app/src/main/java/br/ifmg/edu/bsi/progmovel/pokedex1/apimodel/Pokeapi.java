package br.ifmg.edu.bsi.progmovel.pokedex1.apimodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Pokeapi {
    @GET("pokemon/{name}")
    Call<Pokemon> fetch(@Path("name") String name);

    @GET("pokemon-species/{id}")
    Call<Species> fetchSpecies(@Path("id") int id);

    @GET("evolution-chain/{id}")
    Call<Evolution> fetchEvo(@Path("id") int id);
}
