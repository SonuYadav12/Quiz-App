package com.vision.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vision.quizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding:ActivityQuizBinding
    private lateinit var list:ArrayList<QuestionModel>
    private var count:Int=0
    private var score:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
            list=ArrayList()
        Firebase.firestore.collection("quiz")
            .get().addOnSuccessListener {
                doct->
                    list.clear()

                   for (i in doct.documents)
                   {

                       val questionModel=i.toObject(QuestionModel::class.java)
                       list.add(questionModel!!)
                   }
                binding.question.text = list[0].question
                binding.option1.text = list[0].option1
                binding.option2.text = list[0].option2
                binding.option3.text = list[0].option3
                binding.option4.text = list[0].option4

            }

        binding.option1.setOnClickListener{
            nextData(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener{
            nextData(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener{
            nextData(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener{
            nextData(binding.option4.text.toString())
        }
    }

    private fun nextData(i: String) {
        if(count<list.size) {
            if (list[count].ans.equals(i)) {
                score++
            }
        }
        count++
        if(count>=list.size)
        {
            val intent=Intent(this,ScoreActivity::class.java)
            intent.putExtra("SCORE",score)
            startActivity(intent)
            finish()
        }
        else {
            binding.question.text = list[count].question
            binding.option1.text = list[count].option1
            binding.option2.text = list[count].option2
            binding.option3.text = list[count].option3
            binding.option4.text = list[count].option4
        }
    }
}