package com.normal.anmpweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.set
import androidx.navigation.Navigation
import com.normal.anmpweek1.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment() {
    private lateinit var bind: FragmentGameBinding
    private var score = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentGameBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var numA = Random.nextInt(0, 100)
        var numB = Random.nextInt(0, 100)
        var sum = numA + numB
        bind.txtQuestion.text = "$numA + $numB"

        if(arguments != null){
            val name = GameFragmentArgs.fromBundle(requireArguments()).playerName
            bind.txtTurn.text = "$name's turn"
        }

        bind.btnAnswer.setOnClickListener {
            if(sum.toString() == bind.txtAnswer.text.toString()){
                score += 1
                numA = Random.nextInt(0, 100)
                numB = Random.nextInt(0, 100)
                sum = numA + numB
                bind.txtQuestion.text = "$numA + $numB"
                bind.txtAnswer.setText("")
            }
            else{
                val action = GameFragmentDirections.actionGameoverFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}