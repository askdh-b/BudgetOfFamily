package rustam.urazov.budgetoffamily.screen.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.user.NewUser

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var viewModel: SignUpScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            SignUpScreenFactory(requireContext())
        )[SignUpScreenViewModel::class.java]

        val etFirstName: EditText = view.findViewById(R.id.etFirstName)
        val etLastName: EditText = view.findViewById(R.id.etLastName)
        val etEmail: EditText = view.findViewById(R.id.etUsername)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val etPasswordAgain: EditText = view.findViewById(R.id.etPasswordAgain)
        val bSignIn: Button = view.findViewById(R.id.bSignIn)
        val bSignUp: Button = view.findViewById(R.id.bSignUp)

        viewModel.token.observe(requireActivity()) {
            findNavController().navigate(
                R.id.action_signUpFragment_to_mainActivity,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph) {
                        inclusive = true
                    }
                })
        }

        bSignIn.setOnClickListener {
            findNavController().navigate(
                R.id.action_signUpFragment_to_signInScreen,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph) {
                        inclusive = true
                    }
                }
            )
        }

        bSignUp.setOnClickListener {
            viewModel.register(
                NewUser(
                    etFirstName.text.toString(),
                    etLastName.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etPasswordAgain.text.toString()
                )
            )
        }
    }
}