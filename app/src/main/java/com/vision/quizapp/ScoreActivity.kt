package com.vision.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vision.quizapp.databinding.ActivityScoreBinding

 class ScoreActivity : AppCompatActivity() {
     private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.score.text = "Congrats !!! Your Score is ${intent.getIntExtra("SCORE",0)}"
    }
}