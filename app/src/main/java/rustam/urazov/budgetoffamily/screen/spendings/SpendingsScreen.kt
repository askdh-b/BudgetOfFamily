package rustam.urazov.budgetoffamily.screen.spendings

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
import rustam.urazov.budgetoffamily.adapter.SpendingAdapter

class SpendingsScreen : Fragment(R.layout.fragment_spendings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val rvSpendings: RecyclerView = view.findViewById(R.id.rvSpendings)
        val bAddSpending: Button = view.findViewById(R.id.bAddSpending)

        val viewModel = ViewModelProvider(
            this,
            SpendingsScreenFactory(context)
        )[SpendingsScreenViewModel::class.java]

        viewModel.spendings.observe(activity) { spendings ->
            viewModel.userId.observe(activity) { userId ->
                rvSpendings.adapter = SpendingAdapter(context, spendings.reversed(), userId)
            }
        }

        rvSpendings.layoutManager = LinearLayoutManager(context)

        bAddSpending.setOnClickListener {
            findNavController().navigate(
                R.id.action_spendingsFragment_to_spendingAddFragment,
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

        viewModel.getSpendings()
    }
}