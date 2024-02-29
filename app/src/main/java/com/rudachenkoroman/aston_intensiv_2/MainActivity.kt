package com.rudachenkoroman.aston_intensiv_2

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import com.rudachenkoroman.aston_intensiv_2.databinding.ActivityMainBinding
import com.rudachenkoroman.aston_intensiv_2.util.Action
import com.rudachenkoroman.aston_intensiv_2.util.SelectColor
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var color: SelectColor
    private lateinit var binding: ActivityMainBinding
    private var currentProgress = 50
    private var customTextView = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateWheelSize(currentProgress)

        binding.apply {
            buttonRotate.setOnClickListener {
                val animator = ObjectAnimator.ofFloat(circleView, ROTATION_PROPERTY, STARTING_ROTATION_VALUE,getRandomRotation())
                animator.interpolator = LinearInterpolator()
                animator.duration = DURATION_VALUE
                animationResources()
                animator.setFloatValues(circleView.rotation, circleView.rotation + getRandomRotation())
                animator.start()
            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    progressSeekBar.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    updateWheelSize(seekBar.progress)
                }

            })

            buttonReset.setOnClickListener {
                downloadImage.visibility = View.INVISIBLE
                customText.visibility = View.INVISIBLE
            }
        }

        if (savedInstanceState != null) {
            currentProgress = savedInstanceState.getInt("progress")
            customTextView = savedInstanceState.getString("text", "")
        }
    }

    fun updateWheelSize(progress: Int) {
        var size = progress / MAX_SCALE_VALUE
        if (progress < MIN_SCALE_VALUE) size = MIN_SCALE_VALUE / MAX_SCALE_VALUE
        val scaleDownX =
            ObjectAnimator.ofFloat(binding.circleView, SCALE_X_PROPERTY, size)
        val scaleDownY =
            ObjectAnimator.ofFloat(binding.circleView, SCALE_Y_PROPERTY, size)
        val scaleDown = AnimatorSet()
        scaleDown.play(scaleDownX).with(scaleDownY)
        scaleDown.start()
    }

    private fun showText() {
        binding.apply {
            customText.visibility = View.VISIBLE
            downloadImage.visibility = View.INVISIBLE
            customText.invalidate()
            customText.setColor(color)
            customText.text = color.name
        }
    }

    private fun showImage() {
        binding.apply {
            downloadImage.visibility = View.VISIBLE
            customText.visibility = View.INVISIBLE
            downloadImage()
        }
    }

    private fun downloadImage() {
        Picasso.get()
            .load(URL)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(binding.downloadImage)
    }

    private fun getRandomRotation(): Float =
        (MIN_ROTATION_VALUE..MAX_ROTATION_VALUE).random().toFloat()

    private fun animationResources() {
        color = binding.circleView.getColor()
        when (color.action) {
            Action.SHOW_TEXT -> showText()
            Action.SHOW_IMAGE -> showImage()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("progress", binding.seekBar.progress)
        outState.putString("text", binding.customText.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.seekBar.progress = currentProgress
        updateWheelSize(currentProgress)
        binding.customText.text = customTextView
    }

    companion object {
        private const val URL = "https://loremflickr.com/640/360"
        private const val MIN_ROTATION_VALUE = 200
        private const val MAX_ROTATION_VALUE = 720
        private const val DURATION_VALUE = 1000L
        private const val STARTING_ROTATION_VALUE = 0f
        private const val MIN_SCALE_VALUE = 10f
        private const val MAX_SCALE_VALUE = 100f
        private const val SCALE_X_PROPERTY = "scaleX"
        private const val SCALE_Y_PROPERTY = "scaleY"
        private const val ROTATION_PROPERTY = "rotation"
    }
}