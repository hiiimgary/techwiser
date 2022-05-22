package com.hiimgary.techwiser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.ui.favorites.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.RuntimeException

@AndroidEntryPoint
class NewActivity: AppCompatActivity() {
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private var quote: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity)

        quote = intent.getStringExtra("QUOTE").toString()

        val editText = findViewById<EditText>(R.id.input_field)
        editText.setText(quote)

        val closeBtn = findViewById<Button>(R.id.close)
        closeBtn.setOnClickListener {
            finish()
        }

        val crashBtn = findViewById<Button>(R.id.crash)
        crashBtn.setOnClickListener {
            throw RuntimeException("TEST CRASH")
        }

        val saveBtn = findViewById<Button>(R.id.save)
        saveBtn.setOnClickListener {
            val text = editText.text.toString();
            if (text != "") {
                if (quote == "") {
                    favoritesViewModel.addFavorite(Techy(editText.text.toString()))
                } else {
                    favoritesViewModel.updateFavorite(quote, text)
                }
                finish()
            } else {
                Toast.makeText(this@NewActivity, "Quote Required", Toast.LENGTH_LONG).show()
            }
        }
    }
}