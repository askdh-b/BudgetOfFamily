package rustam.urazov.budgetoffamily.screen.incomeAdd

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.Income
import java.lang.NumberFormatException

class IncomeAddScreen : Fragment(R.layout.fragment_income_add) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etName: EditText = view.findViewById(R.id.etName)
        val etSum: EditText = view.findViewById(R.id.etSum)
        val ibAddIncome: ImageButton = view.findViewById(R.id.ibAddIncome)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        val viewModel = ViewModelProvider(
            activity,
            IncomeAddScreenFactory(context)
        )[IncomeAddScreenViewModel::class.java]

        viewModel.success.observe(activity) {
            if (it) {
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(
                        R.id.action_incomeAddFragment_to_incomesFragment,
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

        ibAddIncome.setOnClickListener {
            try {
                if (etName.text.length in 1..30 && etSum.text.toString()
                        .toFloat() >= 1
                ) {
                    viewModel.addIncome(
                        Income(
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
                R.id.action_incomeAddFragment_to_incomesFragment,
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