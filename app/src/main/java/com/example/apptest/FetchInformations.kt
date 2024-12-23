package com.example.apptestpackage

import com.example.apptest.Actor
import com.example.apptest.ActorList
import com.example.apptest.FilmCollections
import com.example.apptest.Movie
import com.example.apptest.MovieList
import com.example.apptest.Serie
import com.example.apptest.SerieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FetchInformations {

        /*
        val client = OkHttpClient()

        val request = Request.Builder()
          .url("https://api.themoviedb.org/3/search/collection?query=horror&include_adult=false&language=fr&page=1")
          .get()
          .addHeader("accept", "application/json")
          .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjY2ViMGZlNDRmNTAyMTM4ZmEyMWEyZjdkYjg2MTljZiIsIm5iZiI6MTczMTUwNDE1Ni40MTc5MDk5LCJzdWIiOiI2NzI5ZmVmOGQwYzA3MmFkNDhmNTFiYTAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0._Pt68NJHrL2NKUnINT2h8ib6TiBM53-kbmo78rpm_j4")
          .build()

val response = client.newCall(request).execute()
         */
        @GET("search/collection")
        suspend fun getHorrorCollection(
                @Query("api_key") apiKey: String,
                @Query("query") query: String,
                @Query("language") language: String,
        ): FilmCollections

        @GET("trending/movie/week")
        suspend fun getMovieList(
                @Query("api_key") apiKey : String,
                @Query("language") language : String): MovieList

        @GET("movie/{id}?append_to_response=credits")
        suspend fun getMovieDetails(
                @Path("id") id: String,
                @Query("api_key") apiKey: String,
                @Query("language") language: String
        ): Movie

        @GET("search/movie")
        suspend fun searchMovie(
                @Query("api_key") apiKey: String,
                @Query("query") query: String,
                @Query("language") language: String
        ): MovieList

        @GET("trending/tv/week")
        suspend fun getSerieList(
                @Query("api_key") apiKey: String,
                @Query("language") language: String
        ): SerieList

        @GET("tv/{id}?append_to_response=credits")
        suspend fun getSerieDetails(
                @Path("id") id: String,
                @Query("api_key") apiKey: String,
                @Query("language") language: String
        ): Serie

        @GET("search/tv")
        suspend fun searchSerie(
                @Query("api_key") apiKey: String,
                @Query("query") query: String,
                @Query("language") language: String
        ): SerieList

        @GET("trending/person/week")
        suspend fun getActorList(
                @Query("api_key") apiKey: String,
                @Query("language") language: String
        ): ActorList

        @GET("person/{id}?append_to_response=credits")
        suspend fun getActorDetails(
                @Path("id") id: String,
                @Query("api_key") apiKey: String,
                @Query("language") language: String
        ): Actor

        @GET("search/person")
        suspend fun searchActor(
                @Query("api_key") apiKey: String,
                @Query("query") query: String,
                @Query("language") language: String
        ): ActorList

}