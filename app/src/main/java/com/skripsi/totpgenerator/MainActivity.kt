package com.skripsi.totpgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.atlassian.onetime.core.TOTPGenerator
import com.atlassian.onetime.model.TOTPSecret
import com.skripsi.totpgenerator.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputValues()
    }

    // key
    //EREPPCIQKV2FVHOOIZVZWTMMN2T7ANWJ

    private fun inputValues(){

        binding.btnSimpan.setOnClickListener {
            val namaAkun =  binding.isiNamaAkun.text
            val key = binding.isiSecretKey.text.toString()

            if (key.isEmpty()){
                Toast.makeText(this, "Please enter a Secret Key", Toast.LENGTH_SHORT).show()
            }else{
                try {
                    val secret = TOTPSecret.fromBase32EncodedString(key)
                    val totpGenerator = TOTPGenerator()
                    val totp = totpGenerator.generateCurrent(secret)
                    val number = totp.value

                    binding.tvKodeGenerated.text = number
                }catch (e: IllegalArgumentException){
                    Toast.makeText(this, "Invalid Secret Key format", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

}