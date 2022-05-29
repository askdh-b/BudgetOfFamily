package rustam.urazov.budgetoffamily.screen.spendingsSource

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.SpendingsSourceAdapter
import rustam.urazov.budgetoffamily.observer.Observer
import rustam.urazov.budgetoffamily.screen.incomesSourceEdit.IncomesSourceEditScreen

class SpendingsSourceScreen : Fragment(R.layout.fragment_spendings_sources), Observer {

    private lateinit var adapter: SpendingsSourceAdapter
    private var flag = 0
    lateinit var viewModel: SpendingsSourceScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val rvSpendingsSources: RecyclerView = view.findViewById(R.id.rvSpendingsSources)
        val ibAddSpendingsSource: ImageButton = view.findViewById(R.id.ibEditSpendingsSource)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        viewModel = ViewModelProvider(
            activity,
            SpendingsSourceScreenFactory(context)
        )[SpendingsSourceScreenViewModel::class.java]

        rvSpendingsSources.layoutManager = LinearLayoutManager(context)

        viewModel.spendingsSources.observe(activity) {
            if (flag == 0) {
                adapter = SpendingsSourceAdapter(context, it)
                adapter.attach(this)
            }

            rvSpendingsSources.adapter = adapter
        }

        ibAddSpendingsSource.setOnClickListener {
            findNavController().navigate(
                R.id.action_spendingsSourcesFragment_to_spendingsSourceAddFragment,
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

        ibBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_spendingsSourcesFragment_to_profileFragment,
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

        viewModel.getSpendingsSources()
    }

    override fun updatePositive() {
        findNavController().navigate(
            R.id.action_spendingsSourcesFragment_to_spendingsSourceEditFragment,
            bundleOf(IncomesSourceEditScreen.ID to adapter.yesId),
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

    override fun updateNegative() {
        viewModel.deleteSpendingsSource(adapter.noId)
    }
}