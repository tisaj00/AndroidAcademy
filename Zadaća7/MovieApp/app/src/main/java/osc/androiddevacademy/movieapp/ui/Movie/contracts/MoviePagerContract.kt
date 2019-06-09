package osc.androiddevacademy.movieapp.ui.Movie.contracts

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.Review

interface MoviePagerContract {

    interface View{

        fun onReturnSuccess(rewiews:List<Review>)

        fun onReturnFailed()

    }

    interface Presenter{

        fun setView(view:View)

        fun returnMovie(movie:Movie)
    }
}