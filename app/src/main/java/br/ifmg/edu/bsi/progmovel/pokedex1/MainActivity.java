package br.ifmg.edu.bsi.progmovel.pokedex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.ifmg.edu.bsi.progmovel.pokedex1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onClickTextView(View view) {
        Intent intent = new Intent(this, VerPokemonActivity.class);
        intent.putExtra(VerPokemonActivity.EXTRA_NOME_POKEMON, binding.editText.getText().toString());
        startActivity(intent);
    }
}