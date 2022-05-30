package rustam.urazov.budgetoffamily.screen.incomes

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.IncomeAdapter

class IncomesScreen : Fragment(R.layout.fragment_incomes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val rvIncomes: RecyclerView = view.findViewById(R.id.rvIncomes)
        val bAddIncome: Button = view.findViewById(R.id.bAddIncome)

        val viewModel = ViewModelProvider(
            this,
            IncomesScreenFactory(context)
        )[IncomesScreenViewModel::class.java]

        viewModel.incomes.observe(activity) { incomes ->
            viewModel.userId.observe(activity) { userId ->
                rvIncomes.adapter = IncomeAdapter(context, incomes, userId)
                rvIncomes.scrollToPosition(incomes.size - 1)
            }
        }

        rvIncomes.layoutManager = LinearLayoutManager(context)

        bAddIncome.setOnClickListener {
            findNavController().navigate(
                R.id.action_incomesFragment_to_incomeAddFragment,
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

        viewModel.getIncomes()
    }
}