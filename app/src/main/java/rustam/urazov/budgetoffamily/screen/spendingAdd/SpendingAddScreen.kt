package rustam.urazov.budgetoffamily.screen.spendingAdd

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.Spending
import java.lang.NumberFormatException

class SpendingAddScreen : Fragment(R.layout.fragment_spending_add) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etName: EditText = view.findViewById(R.id.etName)
        val etSum: EditText = view.findViewById(R.id.etSum)
        val ibAddSpending: ImageButton = view.findViewById(R.id.ibAddSpending)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        val viewModel = ViewModelProvider(
            activity,
            SpendingAddScreenFactory(context)
        )[SpendingAddScreenViewModel::class.java]

        viewModel.success.observe(activity) {
            if (it) {
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(
                        R.id.action_spendingAddFragment_to_spendingsFragment,
                        null,
                        navOptions {
                            anim {
                                enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                                popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                                popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                                exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                            }
                            launchSingleTop = true
                            popUpTo(R.id.nav_graph_transactions) {
                                inclusive = true
                            }
                        }
                    )
                    viewModel.success.value = false
                }
            }
        }

        etName.addTextChangedListener {
            if (it?.length in 1..30) DrawableCompat.setTint(
                etName.background,
                ContextCompat.getColor(context, R.color.green)
            )
            else DrawableCompat.setTint(
                etName.background,
                ContextCompat.getColor(context, R.color.red)
            )
        }

        etSum.addTextChangedListener {
            try {
                if (etSum.text.toString()
                        .toFloat() in 1.0..200000.0
                ) DrawableCompat.setTint(
                    etSum.background,
                    ContextCompat.getColor(context, R.color.green)
                )
                else DrawableCompat.setTint(
                    etSum.background,
                    ContextCompat.getColor(context, R.color.red)
                )
            } catch (e: NumberFormatException) {
                DrawableCompat.setTint(
                    etSum.background,
                    ContextCompat.getColor(context, R.color.red)
                )
            }
        }

        ibAddSpending.setOnClickListener {
            try {
                if (etName.text.length in 1..30 && etSum.text.toString()
                        .toFloat() >= 1 && etSum.text.toString()
                        .toFloat() <= 200000
                ) {
                    viewModel.addSpending(
                        Spending(
                            name = etName.text.toString(),
                            sum = etSum.text.toString().toFloat()
                        )
                    )
                } else viewModel.showError()
            } catch (e: NumberFormatException) {
                viewModel.showError()
            }
        }

        ibBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_spendingAddFragment_to_spendingsFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph_transactions) {
                        inclusive = true
                    }
                }
            )
        }
    }
}