package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.team6.routineapp.dto.RoutineDTO
import com.team6.routineapp.fitness.*
import com.team6.routineapp.service.RetrofitClient
import com.team6.routineapp.utility.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RoutineActivity : AppCompatActivity() {
    companion object {
        var routines: Array<Routine> = arrayOf() // 저장된 Routine
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine)

        val intentToCreateRoutineActivity = Intent(this, CreateRoutineActivity::class.java);

        val searchView: SearchView = findViewById(R.id.activity_routine_searchview)
        val routinesLayout = findViewById<LinearLayout>(R.id.activity_routine_layout)

        /* 처리 */
        RetrofitClient.routineService.getAllRoutinesByUser(userDTO.userId!!)
            .enqueue(object : Callback<List<RoutineDTO>> {
                override fun onResponse(call: Call<List<RoutineDTO>>, response: Response<List<RoutineDTO>>) {
                    if (response.isSuccessful && response.body() != null) {
                        response.body()!!.forEach {
                            var trainings: Array<Training?> = arrayOf()
                            it.routineDetails!!.forEach {
                                val exerciseName = it.exerciseName
                                var exercise: Exercise? = null
                                exercises.forEach {
                                    if (it.name == exerciseName) exercise = it
                                }

                                trainings += if (it.weight == null) {
                                    Training(exercise!!, it.sets!!, it.reps!!)
                                } else {
                                    WeightTraining(exercise!!, it.sets!!, it.reps!!, it.weight!!)
                                }
                            }

                            routines+= Routine(it.routineName!!, trainings)
                        }
                    }
                }

                override fun onFailure(call: Call<List<RoutineDTO>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null) return false

                for (routineView in routinesLayout.children) routineView.visibility = View.GONE
                for (routineView in routinesLayout.children) {
                    val routine = routineView.tag as Routine

                    if (routine.name.contains(query)) {
                        routineView.visibility = View.VISIBLE
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        }) // 검색 기능

        findViewById<Button>(R.id.activity_exercise_button).setOnClickListener {
            startActivity(intentToCreateRoutineActivity)
        } // 루틴 추가하기 버튼을 누르면, CreateRoutine Actiivty로 이동

        var routine = intent.getClassExtra("routine", Routine::class.java) // 다른 Activity에서 전달한 Routine을 받아 옴

        if (routine != null) {
            routines += (routine)
        } // 받아 온 Routine을 List에 추가

        for (routine in routines) {
            routinesLayout.addView(generateRoutineView(routine))
        } // Routine List에 대응하는 View를 만듦
    }

    /* Training에 대응하는 View 만듦 */
    private fun generateTrainingView(training: Training): TextView {
        val routineViewTrainingsTextView: TextView = TextView(this)

        routineViewTrainingsTextView.text = training!!.exercise.name

        return routineViewTrainingsTextView
    }

    /* Training List에 대응하는 View 만듦 */
    private fun generateTrainingsView(trainings: Array<Training?>, parent: LinearLayout) {
        for (training in trainings) {
            parent.addView(generateTrainingView(training!!))
        }
    }

    /* Routine에 대응 하는 View 만듦 */
    private fun generateRoutineView(routine: Routine): View {
        val routineName = routine.name
        val layoutParameters = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        val intentToRoutineInformationActivity = Intent(this, RoutineInformationActivity::class.java)
        intentToRoutineInformationActivity.putExtra("routine", routine)

        val routineView = layoutInflater.inflate(R.layout.view_routine, null)
        val routineViewTrainingsLayout = routineView.findViewById<LinearLayout>(R.id.view_routine_layout_trainings)

        layoutParameters.setMargins(0, 0, 0, convertFromDpToPx(20))

        routineView.layoutParams = layoutParameters
        routineView.tag = routine
        routineView.findViewById<TextView>(R.id.view_routine_textview_name).text = routineName
        routineView.findViewById<TextView>(R.id.view_routine_textview_part).text = routine.trainings[0]!!.exercise.part
        routineView.findViewById<ImageView>(R.id.view_routine_imageview).setImageResource(R.drawable.benchpress_image)
        routineView.setOnClickListener {
            startActivity(intentToRoutineInformationActivity)
        }

        generateTrainingsView(routine.trainings, routineViewTrainingsLayout)

        return routineView
    }
}