package org.wit.mathstutorappv2.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_mta.*
import kotlinx.android.synthetic.main.activity_mta_youtube.*
import org.jetbrains.anko.startActivity
import org.wit.mathstutorappv2.R

class MTAYoutubeActivity: YouTubeBaseActivity() {

    companion object {
        val PLAYLIST_ADD_ID:String = "PLONzo8sziNKTgXcujq-Pql033Z_Tvyk7n"
        val PLAYLIST_SUB_ID:String = "PLONzo8sziNKTUrm5BsqjuRgc1knThPrpl"
        val PLAYLIST_DIV_ID:String = "PLONzo8sziNKSLo4hMQP38LXmjaj-rJnG4"
        val PLAYLIST_MUL_ID:String = "PLONzo8sziNKSTUae97yvIWa-JNxh4gU9U"

        val YOUTUBE_API_KEY:String = "AIzaSyBeEXdWItLm-p3Xy5ydpRCXRh62uOfbBNE"
    }


    lateinit var youtubePlayerInitADD : YouTubePlayer.OnInitializedListener
    lateinit var youtubePlayerInitSUB : YouTubePlayer.OnInitializedListener
    lateinit var youtubePlayerInitDIV : YouTubePlayer.OnInitializedListener
    lateinit var youtubePlayerInitMUL : YouTubePlayer.OnInitializedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mta_youtube)


        initUI()
    }



    private fun initUI() {
        youtubePlayerInitADD = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                youtubePlayer?.loadPlaylist(PLAYLIST_ADD_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        youtubePlayerInitSUB = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                youtubePlayer?.loadPlaylist(PLAYLIST_SUB_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        youtubePlayerInitDIV = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                youtubePlayer?.loadPlaylist(PLAYLIST_DIV_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        youtubePlayerInitMUL = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                youtubePlayer?.loadPlaylist(PLAYLIST_MUL_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        btnPlayAddition.setOnClickListener(View.OnClickListener { v ->
            youtubePlayerAddition.initialize(YOUTUBE_API_KEY,youtubePlayerInitADD)
        })
        btnStopADD.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()      //to play other playlists
        }

        btnPlaySubtraction.setOnClickListener(View.OnClickListener { v ->
            youtubePlayerSubtraction.initialize(YOUTUBE_API_KEY,youtubePlayerInitSUB)
        })
        btnStopSUB.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()      //to play other playlists
        }

        btnPlayDivision.setOnClickListener(View.OnClickListener { v ->
            youtubePlayerDivision.initialize(YOUTUBE_API_KEY,youtubePlayerInitDIV)
        })
        btnStopDIV.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()      //to play other playlists
        }

        btnPlayMultiplication.setOnClickListener(View.OnClickListener { v ->
            youtubePlayerMultiplication.initialize(YOUTUBE_API_KEY,youtubePlayerInitMUL)
        })
        btnStopMUL.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()      //to play other playlists
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mta, menu)
        if (menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}