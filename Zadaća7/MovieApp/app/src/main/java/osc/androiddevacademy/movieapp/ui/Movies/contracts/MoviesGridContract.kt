package osc.androiddevacademy.movieapp.ui.Movies.contracts

import osc.androiddevacademy.movieapp.model.Movie

interface MoviesGridContract {

    interface View{

        fun onReturnMoviesSuccess(movies:ArrayList<Movie>)

        fun onReturnMoviesFailed()
    }

    interface Presenter{

        fun setView(view: View)

        fun getPopularMovies()

        fun returnPopularMovies():ArrayList<Movie>

        fun favoriteMovie(movie: Movie)
    }
}