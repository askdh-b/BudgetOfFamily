package rustam.urazov.budgetoffamily.screen.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.user.NewUser

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(
            this,
            SignUpScreenFactory(requireContext())
        )[SignUpScreenViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val etFirstName: EditText = view.findViewById(R.id.etFirstName)
        val etLastName: EditText = view.findViewById(R.id.etLastName)
        val etEmail: EditText = view.findViewById(R.id.etUsername)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val etPasswordAgain: EditText = view.findViewById(R.id.etPasswordAgain)

        viewModel.register(
            NewUser(
                etFirstName.text.toString(),
                etLastName.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        )

        return view
    }
}