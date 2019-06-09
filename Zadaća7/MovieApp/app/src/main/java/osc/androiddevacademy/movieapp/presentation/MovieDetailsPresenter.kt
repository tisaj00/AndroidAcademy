package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.Movie.contracts.MoviePagerContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsPresenter (private val apiInteractor: MovieInteractor):MoviePagerContract.Presenter{

    private lateinit var view: MoviePagerContract.View

    override fun setView(view: MoviePagerContract.View) {
       this.view = view
    }

    override fun returnMovie(movie: Movie) {
       apiInteractor.getReviewsForMovie(movie.id, reviewCallBack())
    }

    private fun reviewCallBack(): Callback<ReviewsResponse> = object : Callback<ReviewsResponse> {
        override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
            if (response.isSuccessful){
                response.body()?.reviews?.run {
                    view.onReturnSuccess(this)
                }
            }
        }
    }
}