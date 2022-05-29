package rustam.urazov.budgetoffamily.screen.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.UserAuthData
import java.lang.IllegalArgumentException

class SignInScreen : Fragment(R.layout.fragment_sign_in) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etUsername: EditText = view.findViewById(R.id.etUsername)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val bSignIn: Button = view.findViewById(R.id.bSignIn)
        val bSignUp: Button = view.findViewById(R.id.bSignUp)

        val viewModel = ViewModelProvider(
            this,
            SignInScreenFactory(context)
        )[SignInScreenViewModel::class.java]

        viewModel.token.observe(activity) {
            try {
                findNavController().navigate(
                    R.id.action_signInScreen_to_mainFragment,
                    null,
                    navOptions {
                        anim {
                            enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                            popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                            popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                            exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                        }
                        launchSingleTop = true
                        popUpTo(R.id.nav_graph_main) {
                            inclusive = true
                        }
                    }
                )
            } catch (e: IllegalArgumentException) {
                viewModel.showError()
            }
        }

        bSignIn.setOnClickListener {
            val userAuthData = UserAuthData(
                username = etUsername.text.toString(),
                password = etPassword.text.toString()
            )
            viewModel.authorize(userAuthData)
        }

        bSignUp.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInScreen_to_signUpFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph_main) {
                        inclusive = true
                    }
                }
            )
        }
    }
}