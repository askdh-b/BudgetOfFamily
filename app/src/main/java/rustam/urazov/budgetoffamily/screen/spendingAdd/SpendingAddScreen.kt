package rustam.urazov.budgetoffamily.screen.spendingAdd

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.Spending

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

        ibAddSpending.setOnClickListener {
            viewModel.addSpending(
                Spending(
                    name = etName.text.toString(),
                    sum = etSum.text.toString().toFloat()
                )
            )
        }

        viewModel.success.observe(activity) {
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