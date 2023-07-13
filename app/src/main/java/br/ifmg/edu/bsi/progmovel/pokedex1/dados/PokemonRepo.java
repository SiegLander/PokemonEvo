package br.ifmg.edu.bsi.progmovel.pokedex1.dados;

import java.io.IOException;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Evolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokeapi;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokemon;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Species;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRepo {

    private Pokeapi api;

    public PokemonRepo(Pokeapi api) {
        this.api = api;
    }

    public Pokemon buscar(String nome) throws IOException {
        Pokemon p = api.fetch(nome).execute().body();
        return p;
    }

    public Species pegarEspecie(int id) throws IOException {
        Species s = api.fetchSpecies(id).execute().body();
        return s;
    }

    public Evolution pegarCadeiaEvolutiva(int id) throws IOException {
        Evolution e = api.fetchEvo(id).execute().body();
        return e;
    }
}
