package rustam.urazov.budgetoffamily.screen.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.UserAuthData


class SignInScreen : Fragment() {

    private lateinit var viewModel: SignInScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(
            this,
            SignInScreenFactory(requireContext())
        )[SignInScreenViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val etEmail: EditText = view.findViewById(R.id.etEmail)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val bSignIn: Button = view.findViewById(R.id.bSignIn)
        val bSignUp: Button = view.findViewById(R.id.bSignUp)

        bSignIn.setOnClickListener {
            val userAuthData = UserAuthData(etEmail.toString(), etPassword.toString())
            viewModel.authorize(userAuthData)
        }

        bSignUp.setOnClickListener {

        }

        return view
    }
}