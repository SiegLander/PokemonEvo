package br.ifmg.edu.bsi.progmovel.pokedex1;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokeapi;
import br.ifmg.edu.bsi.progmovel.pokedex1.dados.PokemonRepo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexApplication extends Application {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private Pokeapi pokeapi;
    private PokemonRepo pokemonRepo;

    public Executor getExecutor() {
        return executorService;
    }

    public Pokeapi getApi() {
        if (pokeapi != null) return pokeapi;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeapi = retrofit.create(Pokeapi.class);

        return pokeapi;
    }

    public PokemonRepo getPokemonRepo() {
        if (pokemonRepo != null) return pokemonRepo;

        pokemonRepo = new PokemonRepo(getApi());
        return pokemonRepo;
    }
}
