package osc.androiddevacademy.movieapp.ui.Favorite.contract

import osc.androiddevacademy.movieapp.model.Movie

interface FavoriteContract {

    interface View{

        fun onReturnSuccess(movies:ArrayList<Movie>)

        fun onReturnFailed()

    }

    interface Presenter{

        fun setView(view:View)

        fun getFavoriteMovie()

        fun returnFavoriteMovie():ArrayList<Movie>
    }
}