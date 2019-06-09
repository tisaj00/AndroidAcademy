package osc.androiddevacademy.movieapp.ui.Movies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.ui.Favorites.fragments.FavoritesFragment
import osc.androiddevacademy.movieapp.ui.Movies.fragments.MoviesGridFragment
import osc.androiddevacademy.movieapp.ui.TopMovies.TopMovieFragment

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesGridFragment()
    }

    private fun initMoviesGridFragment(){
        showFragment(R.id.mainFragmentHolder,
            MoviesGridFragment()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_options,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.showFavorites -> {showFragment(R.id.mainFragmentHolder, FavoritesFragment())}
            R.id.showTopMovies -> {showFragment(R.id.mainFragmentHolder, TopMovieFragment())}
            R.id.returnHome -> {showFragment(R.id.mainFragmentHolder,MoviesGridFragment())}

        }
        return super.onOptionsItemSelected(item)
    }
}
