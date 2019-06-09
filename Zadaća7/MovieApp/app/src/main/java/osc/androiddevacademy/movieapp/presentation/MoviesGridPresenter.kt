package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.Movies.contracts.MoviesGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridPresenter(private val apiInteractor: MovieInteractor):MoviesGridContract.Presenter {

    private lateinit var view: MoviesGridContract.View
    private val movieList:ArrayList<Movie> = arrayListOf()
    private val appDB:MoviesDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }


    override fun setView(view: MoviesGridContract.View) {
       this.view = view
    }

    override fun getPopularMovies() {
       apiInteractor.getPopularMovies(popularMoviesCallback())
    }

    override fun returnPopularMovies(): ArrayList<Movie> {
       return movieList
    }

    override fun favoriteMovie(movie: Movie) {
        movie.isFavorite =!movie.isFavorite
        if(movie.isFavorite == true){
            appDB.moviesDao().addFavoriteMovie(movie)

        }
        else{
            appDB.moviesDao().deleteFavoriteMovie(movie)
        }
    }

    private fun popularMoviesCallback(): Callback<MoviesResponse> =
        object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                //Some ERROR
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.movies?.run {
                        movieList.clear()
                        movieList.addAll(this)
                        mergeListWithFavorites()
                        view.onReturnMoviesSuccess(this)

                    }
                }
            }
        }
    private fun mergeListWithFavorites(){
        val favoritesList = appDB.moviesDao().getFavoriteMovies()
        favoritesList.forEach{
            for(movie in movieList){
                if(it.id==movie.id){
                    movie.isFavorite = !movie.isFavorite
                }
            }
        }
    }


}