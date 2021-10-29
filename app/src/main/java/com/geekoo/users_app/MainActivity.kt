package com.geekoo.users_app

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekoo.users_app.databinding.ActivityMainBinding
import com.geekoo.users_app.databinding.ItemAnimeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.FieldPosition

class MainActivity : AppCompatActivity() , OnClickListener{

    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences  =  getPreferences(Context.MODE_PRIVATE)
        val firstTime =  preferences.getBoolean(getString(R.string.sp_firts_time), true)
        Log.i("SP", "${getString(R.string.sp_firts_time)} = $firstTime")

        if(firstTime){
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_confirm, { dialogInterface, i ->
                    preferences.edit().putBoolean(getString(R.string.sp_firts_time), false).commit()
                })
                .setNegativeButton("Cancelar",null)
                .show()

        }


        animeAdapter =  AnimeAdapter(getAnime(), this )
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = animeAdapter
        }
    }

    private  fun getAnime():MutableList<Anime> {
        val anime = mutableListOf<Anime>()

        val anime1 =  Anime(1, "naruto", "", "https://www.formulatv.com/images/series/posters/100/135/dest_3.jpg")
        val anime2 =  Anime(2, "one piece", "", "https://images-na.ssl-images-amazon.com/images/I/61RbnZ1FlJL._SX331_BO1,204,203,200_.jpg")
        val anime3 =  Anime(3, "avatar aang", "", "https://kamite.com.mx/1871-large_default/avatar-the-last-airbender-la-promesa-1.jpg")
        val anime4 =  Anime(4, "daymon slayer", "", "https://areajugones.sport.es/wp-content/uploads/2021/09/kimetsu-no-yaiba-tren-infinito-570x800.jpg")
        anime.add(anime1)
        anime.add(anime2)
        anime.add(anime3)
        anime.add(anime4)
        anime.add(anime1)
        anime.add(anime2)
        anime.add(anime3)
        anime.add(anime4)
        return anime
    }

    override fun onClick(anime: Anime, position: Int) {
        Toast.makeText(this, "$position: ${anime.name}" , Toast.LENGTH_SHORT).show()
    }
}