package rustam.urazov.budgetoffamily.screen.profile

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R

class ProfileScreen : Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val viewModel = ViewModelProvider(
            this,
            ProfileScreenFactory(context)
        )[ProfileScreenViewModel::class.java]

        val ibNotifications: ImageButton = view.findViewById(R.id.ibNotifications)
        val ibSignOut: ImageButton = view.findViewById(R.id.ibSignOut)
        val ibIncomesSourcesEdit: ImageButton = view.findViewById(R.id.ibIncomesSourcesEdit)
        val ibSpendingsSourcesEdit: ImageButton = view.findViewById(R.id.ibSpendingsSourcesEdit)
        val tvBalance: TextView = view.findViewById(R.id.tvBalance)
        val tvIncomesSource: TextView = view.findViewById(R.id.tvIncomesSources)
        val tvSpendingsSource: TextView = view.findViewById(R.id.tvSpendingsSources)
        val tvNotificationsCount: TextView = view.findViewById(R.id.tvNotificationsCount)

        viewModel.balance.observe(activity) {
            tvBalance.text = it.toString()
        }

        viewModel.incomesSourcesValue.observe(activity) {
            tvIncomesSource.text = it.toString()
        }

        viewModel.spendingsSourcesValue.observe(activity) {
            tvSpendingsSource.text = it.toString()
        }

        viewModel.invitationsCount.observe(activity) {
            tvNotificationsCount.text = it.toString()
        }

        viewModel.getBalance()
        viewModel.getIncomesSources()
        viewModel.getSpendingsSources()
        viewModel.getInvitations()

        ibNotifications.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_invitationsFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph_content) {
                        inclusive = true
                    }
                }
            )
        }

        ibSignOut.setOnClickListener {

        }

        ibIncomesSourcesEdit.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_incomesSourcesFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph_content) {
                        inclusive = true
                    }
                }
            )
        }

        ibSpendingsSourcesEdit.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_spendingsSourcesFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph_content) {
                        inclusive = true
                    }
                }
            )
        }
    }
}