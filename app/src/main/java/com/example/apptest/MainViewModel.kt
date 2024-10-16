package com.example.apptest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptestpackage.FetchInformations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
class MainViewModel : ViewModel() {
    val apiKey = "b57151d36fecd1b693da830a2bc5766f"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(FetchInformations::class.java)

    val movies  = MutableStateFlow<List<Movie>>(listOf())

    val movie = MutableStateFlow(Movie())

    val series = MutableStateFlow<List<Serie>>(listOf())

    val serie = MutableStateFlow(Serie())

    val actors = MutableStateFlow<List<Actor>>(listOf())

    val actor = MutableStateFlow(Actor())

    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.getMovieList(apiKey, "fr").results
        }
    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            movie.value = api.getMovieDetails(id, apiKey, "fr")
        }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            movies.value = api.searchMovie(apiKey, query, "fr").results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            series.value = api.getSerieList(apiKey, "fr").results
        }
    }

    fun getSerieDetails(id: String) {
        viewModelScope.launch {
            serie.value = api.getSerieDetails(id, apiKey, "fr")
        }
    }

    fun searchSerie(query: String) {
        viewModelScope.launch {
            series.value = api.searchSerie(apiKey, query, "fr").results
        }
    }

    fun getActors() {
        viewModelScope.launch {
            actors.value = api.getActorList(apiKey, "fr").results
        }
    }

    fun getActorDetails(id: String) {
        viewModelScope.launch {
            actor.value = api.getActorDetails(id, apiKey, "fr")
        }
    }

    fun searchActor(query: String) {
        viewModelScope.launch {
            actors.value = api.searchActor(apiKey, query, "fr").results
        }
    }

}