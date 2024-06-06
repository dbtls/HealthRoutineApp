package com.team6.routineapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.utility.getClassExtra
import com.team6.routineapp.utility.getImageResource


class ExerciseInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_information) //xml과 연결
        activityStack.push(this)

        val exercise = intent.getClassExtra("exercise", Exercise::class.java) // 다른 Activity로부터 전달받은 Exercise를 받아 옴

        val exerciseDTO = exerciseSingleton.getExerciseByName(exercise!!.name)

        findViewById<TextView>(R.id.activity_exercise_information_textview_exercise_name).text = exercise!!.name // 제목을 Exercise의 name으로 설정
        findViewById<TextView>(R.id.activity_exercise_information_textview_exercise_part).text =
            String.format("%s 자극을 위한 운동", exercise.part) // 부제목을 Exercise의 part로 설정
        findViewById<ImageView>(R.id.activity_exercise_information_imageview).setImageResource(getImageResource(exercise))


        if (exerciseDTO != null) {
            findViewById<TextView>(R.id.activity_exercise_information_description_body).text = exerciseDTO.descriptions
        }

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = exerciseDTO?.videoLink
                youTubePlayer.cueVideo(videoId!!, 0f)
            }
        })
    }
}