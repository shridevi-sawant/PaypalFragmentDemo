package com.paypal.fragmentdemo

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginB)
        registerButton = findViewById(R.id.registerB)



        setupUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupUI() {

        loginButton.isEnabled = false

        registerButton.setOnClickListener {
            // fragmentTransaction - add, remove, replace
            loginButton.isEnabled = true
            registerButton.isEnabled = false

            val fManager = supportFragmentManager
            val fTransaction = fManager.beginTransaction()
            val frag = RegisterFragment{ email ->

                loadLoginFragment(email)
            }

            fTransaction.replace(R.id.container, frag)
            fTransaction.addToBackStack(null)
            fTransaction.commit()
        }

        loginButton.setOnClickListener {
            loginButton.isEnabled = false
            registerButton.isEnabled = true

            loadLoginFragment("")
        }
    }

    private fun loadLoginFragment(email: String) {
        supportFragmentManager.beginTransaction().run {
            val frag = LoginFragment()
            frag.arguments = bundleOf("emailAddress" to email, "flag" to 1)

            replace(R.id.container, frag)
            addToBackStack(null)
            commit()
        }
    }
}