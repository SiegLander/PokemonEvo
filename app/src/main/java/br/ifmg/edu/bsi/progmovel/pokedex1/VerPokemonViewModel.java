package br.ifmg.edu.bsi.progmovel.pokedex1;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Evolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.EvolvesTo;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokeapi;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokemon;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Species;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerPokemonViewModel extends ViewModel {
    private PokedexApplication app;
    private MutableLiveData<Integer> loading;
    private MutableLiveData<String> nome;
    private MutableLiveData<String> urlImagem;
    private MutableLiveData<Integer> height;
    private MutableLiveData<Integer> weight;
    private ArrayList<String> evoNome;


    public static ViewModelInitializer<VerPokemonViewModel> initializer = new ViewModelInitializer<>(
            VerPokemonViewModel.class,
            creationExtras -> new VerPokemonViewModel((PokedexApplication) creationExtras.get(APPLICATION_KEY)));

    public VerPokemonViewModel(PokedexApplication app) {
        this.app = app;
        loading = new MutableLiveData<>(View.GONE);
        nome = new MutableLiveData<>();
        urlImagem = new MutableLiveData<>();
        height = new MutableLiveData<>();
        weight = new MutableLiveData<>();
        evoNome = new ArrayList<>();
    }

    public void loadPokemon(String nomePokemon) {
        loading.setValue(View.VISIBLE);
        app.getExecutor().execute(() -> {

            try {
                Pokemon p = app.getPokemonRepo().buscar(nomePokemon);
                Log.d("id pokemon", String.valueOf(p.id));
                Species s = app.getPokemonRepo().pegarEspecie(p.id);
                String[] sSperated = s.evolution_chain.url.split("/");
                Log.d("evo id", sSperated[6]);
                Evolution e = app.getPokemonRepo().pegarCadeiaEvolutiva(Integer.valueOf(sSperated[6]));
                for (EvolvesTo evolucao : e.chain.evolves_to) {
                    evoNome.add(evolucao.species.name);
                    for (EvolvesTo evolucao2 : evolucao.evolves_to) {
                        evoNome.add(evolucao2.species.name);
                    }
                }
                Log.d("POKEMON EVO 2", String.valueOf(e.chain.evolves_to[0].evolves_to[0].species.name));
                nome.postValue(p.name);
                height.postValue(p.height);
                weight.postValue(p.weight);
                urlImagem.postValue(p.sprites.other.officialArtwork.front_default);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                loading.postValue(View.GONE);
            }
        });
    }

    public LiveData<Integer> getLoading() {
        return loading;
    }

    public LiveData<String> getNome() {
        return nome;
    }

    public LiveData<String> getUrlImagem() {
        return urlImagem;
    }

    public LiveData<Integer> getHeight() {
        return height;
    }

    public LiveData<Integer> getWeight() {
        return weight;
    }

    public ArrayList<String> getEvos() {
        return evoNome;
    }
}
