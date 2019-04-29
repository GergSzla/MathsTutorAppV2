package org.wit.mathstutorappv2.activities

/*
Adds functionality to the activity_mta_youtube.xml page.
This gives the user 4 different youtube players, with 4 different
playlists assigned to them.
This gives the user an opportunity to learn more about the different
types of maths available
 */

import android.os.Bundle

import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_mta_youtube.*
import org.jetbrains.anko.startActivity
import org.wit.mathstutorappv2.R

class MTAYoutubeActivity: YouTubeBaseActivity() {

    companion object {

        /*
            Each Playlist val takes in the link of the playlist.
         */
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
                //If the youtube player is initialized successfully, the playlist is loaded into the player.
                youtubePlayer?.loadPlaylist(PLAYLIST_ADD_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        youtubePlayerInitSUB = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                //If the youtube player is initialized successfully, the playlist is loaded into the player.
                youtubePlayer?.loadPlaylist(PLAYLIST_SUB_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        youtubePlayerInitDIV = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                //If the youtube player is initialized successfully, the playlist is loaded into the player.
                youtubePlayer?.loadPlaylist(PLAYLIST_DIV_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        youtubePlayerInitMUL = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                //If the youtube player is initialized successfully, the playlist is loaded into the player.
                youtubePlayer?.loadPlaylist(PLAYLIST_MUL_ID)
            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        btnPlayAddition.setOnClickListener(View.OnClickListener { v ->
            /*
            if the addition button is pressed, the player on the res/layout for addition is initialized using the API
            key and initilized youtube player with the playlist assigned
            */
            youtubePlayerAddition.initialize(YOUTUBE_API_KEY,youtubePlayerInitADD)
        })
        btnStopADD.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()      //to play other playlists
        }


        btnPlaySubtraction.setOnClickListener(View.OnClickListener { v ->
            /*
            if the subtraction button is pressed, the player on the res/layout for subtraction is initialized using the API
            key and initilized youtube player with the playlist assigned
            */
            youtubePlayerSubtraction.initialize(YOUTUBE_API_KEY,youtubePlayerInitSUB)
        })
        btnStopSUB.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()      //to play other playlists
        }


        btnPlayDivision.setOnClickListener(View.OnClickListener { v ->
            /*
            if the Division button is pressed, the player on the res/layout for Division is initialized using the API
            key and initilized youtube player with the playlist assigned
            */
            youtubePlayerDivision.initialize(YOUTUBE_API_KEY,youtubePlayerInitDIV)
        })
        btnStopDIV.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()      //to play other playlists
        }


        btnPlayMultiplication.setOnClickListener(View.OnClickListener { v ->
            /*
            if the Multiplication button is pressed, the player on the res/layout for Multiplication is initialized using the API
            key and initilized youtube player with the playlist assigned
            */
            youtubePlayerMultiplication.initialize(YOUTUBE_API_KEY,youtubePlayerInitMUL)
        })
        btnStopMUL.setOnClickListener {                ///Pressing stop buttons before moving onto a different video: reloads the activity and allows
            startActivity<MTAYoutubeActivity>()         //to play other playlists
        }

    }
}