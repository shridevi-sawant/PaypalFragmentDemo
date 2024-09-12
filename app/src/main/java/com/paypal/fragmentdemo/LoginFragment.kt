package com.paypal.fragmentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    lateinit var okButton: Button
    lateinit var emailEditText: EditText

    var emailAddr = ""

    // layout rendering
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailAddr = arguments?.getString("emailAddress") ?: ""

        okButton = view.findViewById(R.id.okB)
        emailEditText = view.findViewById(R.id.emailE)
        emailEditText.setText(emailAddr)

        okButton.setOnClickListener {
            val email = emailEditText.text.toString()

            Snackbar.make(okButton, "Login for: $email", 5000).show()
        }
    }

}