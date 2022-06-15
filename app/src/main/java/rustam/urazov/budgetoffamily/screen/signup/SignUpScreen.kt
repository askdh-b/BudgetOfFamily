package rustam.urazov.budgetoffamily.screen.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.NewUser

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var viewModel: SignUpScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()

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
                R.id.action_signUpFragment_to_mainFragment,
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
                    popUpTo(R.id.nav_graph_main) {
                        inclusive = true
                    }
                }
            )
        }

        etFirstName.addTextChangedListener {
            if (it?.length in 1..20) DrawableCompat.setTint(
                etFirstName.background,
                ContextCompat.getColor(context, R.color.green)
            )
            else DrawableCompat.setTint(
                etFirstName.background,
                ContextCompat.getColor(context, R.color.red)
            )
        }

        etLastName.addTextChangedListener {
            if (it?.length in 1..20) DrawableCompat.setTint(
                etLastName.background,
                ContextCompat.getColor(context, R.color.green)
            )
            else DrawableCompat.setTint(
                etLastName.background,
                ContextCompat.getColor(context, R.color.red)
            )
        }

        etEmail.addTextChangedListener {
            if (it?.length in 3..20) DrawableCompat.setTint(
                etEmail.background,
                ContextCompat.getColor(context, R.color.green)
            )
            else DrawableCompat.setTint(
                etEmail.background,
                ContextCompat.getColor(context, R.color.red)
            )
        }

        etPassword.addTextChangedListener {
            if (it?.length in 6..20) DrawableCompat.setTint(
                etPassword.background,
                ContextCompat.getColor(context, R.color.green)
            )
            else DrawableCompat.setTint(
                etPassword.background,
                ContextCompat.getColor(context, R.color.red)
            )
        }

        etPasswordAgain.addTextChangedListener {
            if (it?.length in 6..20 && it?.toString() == etPassword.text.toString()) DrawableCompat.setTint(
                etPasswordAgain.background,
                ContextCompat.getColor(context, R.color.green)
            )
            else DrawableCompat.setTint(
                etPasswordAgain.background,
                ContextCompat.getColor(context, R.color.red)
            )
        }

        bSignUp.setOnClickListener {
            if (etFirstName.text.length in 1..20 && etLastName.text.length in 1..20 && etEmail.text.length in 3..20 && etPassword.text.length in 6..20) {
                viewModel.register(
                    NewUser(
                        etFirstName.text.toString(),
                        etLastName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        etPasswordAgain.text.toString()
                    )
                )
            } else {
                viewModel.showError()
                DrawableCompat.setTint(
                    etEmail.background,
                    ContextCompat.getColor(context, R.color.red)
                )
            }
        }
    }
}