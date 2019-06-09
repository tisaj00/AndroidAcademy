package osc.androiddevacademy.movieapp.ui.TopMovie

import osc.androiddevacademy.movieapp.model.Movie

interface TopMovieContract {

    interface View{

        fun onReturnTopMovieSuccess(movie:ArrayList<Movie>)

        fun onReturnTopMovieFailed()
    }

    interface Presenter{

        fun setView(view:View)

        fun getTopMovies()

        fun favoriteMovie(movie:Movie)

        fun returnTopMovies():ArrayList<Movie>

    }
}