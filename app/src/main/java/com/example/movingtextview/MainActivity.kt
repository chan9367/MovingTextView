package com.example.movingtextview

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.example.movingtextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView_Progress = findViewById<TextView>(R.id.textView_progress)
        val initialTextViewTranslationY = textView_Progress.translationY
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val button_reset = findViewById<Button>(R.id.button_reset)

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView_Progress.text = progress.toString()

                val translationDistance = (initialTextViewTranslationY + progress * resources.getDimension(R.dimen.text_step)*-1)

                textView_Progress.animate().translationY(translationDistance)

                if(!fromUser)
                    textView_Progress.animate().setDuration(500).rotationBy(360f)

                if(textView_Progress.rotation.rem(360f) != 0f){
                    val correction = textView_Progress.rotation.rem(360f)

                    textView_Progress.animate().setDuration(500).rotationBy(
                        if (correction > 180f) 360 - correction
                        else -correction
                    )
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        button_reset.setOnClickListener { v ->
            seekBar.progress = 0
        }
    }
}