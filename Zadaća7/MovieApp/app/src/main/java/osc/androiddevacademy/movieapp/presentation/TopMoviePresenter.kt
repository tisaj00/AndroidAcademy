package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.TopMovie.TopMovieContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopMoviePresenter(private val apiInteractor: MovieInteractor):TopMovieContract.Presenter {

    private lateinit var view:TopMovieContract.View
    private var movieList:ArrayList<Movie> = arrayListOf()
    private val appDb:MoviesDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }


    override fun setView(view: TopMovieContract.View) {
        this.view = view
    }

    override fun getTopMovies() {
       apiInteractor.getTopMovies(topMovieCallback())
    }

    override fun favoriteMovie(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        if(movie.isFavorite == true){
            appDb.moviesDao().addFavoriteMovie(movie)
        }
        else{
            appDb.moviesDao().deleteFavoriteMovie(movie)
        }
    }

    override fun returnTopMovies(): ArrayList<Movie> {
        return movieList
    }


    private fun topMovieCallback(): Callback<MoviesResponse> =
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
                        view.onReturnTopMovieSuccess(this)
                    }
                }
            }
        }

    private fun mergeListWithFavorites(){
        val favoritesList = appDb.moviesDao().getFavoriteMovies()
        favoritesList.forEach{
            for(movie in movieList){
                if(it.id==movie.id){
                    movie.isFavorite = !movie.isFavorite
                }
            }
        }
    }
}