package com.example.home_work_lection_7

import android.animation.ArgbEvaluator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.home_work_lection_7.databinding.FragmentAnimateBinding

class AnimateFragment : Fragment() {
    lateinit var binding: FragmentAnimateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAnimateBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.clickMe.setOnClickListener {
            val resources = requireActivity().resources
            val firstViewColorHolder = PropertyValuesHolder.ofObject("firstBackground", ArgbEvaluator(), (binding.firstView.background as ColorDrawable).color, resources.getColor(R.color.squareDestinationBack))
            val firstViewScaleHolder = PropertyValuesHolder.ofFloat("firstScale", binding.firstView.layoutParams.height.toFloat(), resources.getDimension(R.dimen.squareDestinationSize))

            val secondViewHeightHolder = PropertyValuesHolder.ofFloat("secondHeight", binding.secondView.layoutParams.height.toFloat(), resources.getDimension(R.dimen.rectangleDestinationHeight))

            ValueAnimator.ofPropertyValuesHolder(firstViewColorHolder, firstViewScaleHolder, secondViewHeightHolder).apply {
                duration = 400L
                interpolator = AccelerateDecelerateInterpolator()
                addUpdateListener {
                    val firstColor = getAnimatedValue("firstBackground") as Int
                    val firstScale = getAnimatedValue("firstScale") as Float
                    binding.firstView.setBackgroundColor(firstColor)
                    val firstLayoutParams = binding.firstView.layoutParams
                    firstLayoutParams.width = firstScale.toInt()
                    firstLayoutParams.height= firstScale.toInt()
                    binding.firstView.layoutParams = firstLayoutParams
                    binding.firstView.setBackgroundColor(firstColor)

                    val secondHeight = getAnimatedValue("secondHeight") as Float
                    val secondLayoutParams = binding.secondView.layoutParams
                    secondLayoutParams.height = secondHeight.toInt()
                    binding.secondView.layoutParams = secondLayoutParams
                }
                this.setupStartValues()
                start()
            }
        }
    }
}