package com.example.apptest

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class MovieList(
    val page: Int = 0,
    val results: List<Movie> = listOf(),
)

interface CanBeCarded {
    fun getLinkToToDetails() : String = ""
    fun getTitleName() : String;
    fun getPosterPath() : String;
    fun getDate() : String;

}

interface CanBeDetailed : CanBeCarded {
    fun getGenresNames() : List<String> = listOf()
    fun getSynopsis() : String = ""
    fun getCastProfilPath() : List<String> = listOf()
    fun getBackdropPath() : String = ""
}

val baseUrl = "https://api.themoviedb.org/3/"
val apiKey = "?api_key=b57151d36fecd1b693da830a2bc5766f"

//MOVIE
data class Movie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: Any = Any(),
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val credits: Credits = Credits()
) : CanBeDetailed {
    override fun getTitleName() = title
    override fun getPosterPath() = poster_path
    override fun getDate() = formatDate(release_date)
    override fun getLinkToToDetails() = id.toString()
    override fun getGenresNames(): List<String> = genres.map { it.name }
    override fun getSynopsis() = overview
    override fun getCastProfilPath() = credits.cast.map { it.profile_path }
    override fun getBackdropPath() = backdrop_path
}


data class Genre(
    val id: Int = 0,
    val name: String = ""
)

data class ProductionCompany(
    val id: Int = 0,
    val logo_path: String = "",
    val name: String = "",
    val origin_country: String = ""
)

data class ProductionCountry(
    val iso_3166_1: String = "",
    val name: String = ""
)

data class SpokenLanguage(
    val english_name: String = "",
    val iso_639_1: String = "",
    val name: String = ""
)

data class Credits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf()
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

//SERIE LIST
data class SerieList(
    val page: Int = 0,
    val results: List<Serie> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

//SERIES
data class Serie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val created_by: List<Any> = listOf(),
    val episode_run_time: List<Int> = listOf(),
    val first_air_date: String = "",
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val in_production: Boolean = false,
    val languages: List<String> = listOf(),
    val last_air_date: Any = Any(),
    val last_episode_to_air: Any = Any(),
    val name: String = "",
    val networks: List<Network> = listOf(),
    val next_episode_to_air: Any = Any(),
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String> = listOf(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<Any> = listOf(),
    val production_countries: List<Any> = listOf(),
    val seasons: List<Any> = listOf(),
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val credits: Credits = Credits()
) : CanBeCarded, CanBeDetailed {
    override fun getTitleName() = title
    override fun getPosterPath() = poster_path
    override fun getDate() = formatDate(first_air_date)

    //override fun getLinkToToDetails() = baseUrl + "serie/" + id + apiKey
    override fun getLinkToToDetails() = id.toString()

    override fun getGenresNames(): List<String> = genres.map { it.name }
    override fun getSynopsis() = overview
    override fun getCastProfilPath() = credits.cast.map { it.profile_path }
    override fun getBackdropPath() = backdrop_path

}

data class Network(
    val id: Int = 0,
    val logo_path: String = "",
    val name: String = "",
    val origin_country: String = ""
)

//Actor List
data class ActorList(
    val page: Int = 0,
    val results: List<Actor> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

//Actors
data class Actor(
    val adult: Boolean = false,
    val gender: Int = 0,
    val id: Int = 0,
    val known_for: List<KnownFor> = listOf(),
    val known_for_department: String = "",
    val media_type: String = "",
    val name: String = "",
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = "",
    val biography: String = "",
    val birthday: String = ""
) : CanBeCarded {
    override fun getTitleName() = name
    override fun getPosterPath() = profile_path
    override fun getDate() = ""
    override fun getLinkToToDetails() = baseUrl + "actor/" + id + apiKey

}

data class KnownFor(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = listOf(),
    val id: Int = 0,
    val media_type: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

@SuppressLint("NewApi")
fun formatDate(input: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH)
    val date = LocalDate.parse(input, inputFormatter)
    return date.format(outputFormatter)
}