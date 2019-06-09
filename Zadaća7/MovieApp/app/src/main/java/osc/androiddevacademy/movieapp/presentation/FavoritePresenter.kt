package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.Favorite.contract.FavoriteContract

class FavoritePresenter(private val apiInteractor:MovieInteractor):FavoriteContract.Presenter {

    private lateinit var view:FavoriteContract.View
    private var movieList:ArrayList<Movie> = arrayListOf()
    private val appDB:MoviesDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }


    override fun setView(view: FavoriteContract.View) {
        this.view = view
    }

    override fun getFavoriteMovie() {
        movieList.clear()
        movieList.addAll(appDB.moviesDao().getFavoriteMovies() as ArrayList<Movie>)
        if(movieList.isNotEmpty()){
            view.onReturnSuccess(movieList)
        }
        else {
            view.onReturnFailed()
        }
    }

    override fun returnFavoriteMovie(): ArrayList<Movie> {
        return movieList
    }
}