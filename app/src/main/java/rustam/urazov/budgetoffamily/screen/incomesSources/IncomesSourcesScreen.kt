package rustam.urazov.budgetoffamily.screen.incomesSources

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
import rustam.urazov.budgetoffamily.adapter.IncomesSourceAdapter
import rustam.urazov.budgetoffamily.observer.Observer
import rustam.urazov.budgetoffamily.screen.incomesSourceEdit.IncomesSourceEditScreen

class IncomesSourcesScreen : Fragment(R.layout.fragment_incomes_sources), Observer {

    private lateinit var adapter: IncomesSourceAdapter
    private var flag = 0
    lateinit var viewModel: IncomesSourcesScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val rvIncomesSources: RecyclerView = view.findViewById(R.id.rvIncomesSources)
        val ibAddIncomesSource: ImageButton = view.findViewById(R.id.ibEditIncomesSource)

        viewModel = ViewModelProvider(
            activity,
            IncomesSourcesScreenFactory(context)
        )[IncomesSourcesScreenViewModel::class.java]
        
        rvIncomesSources.layoutManager = LinearLayoutManager(context)

        viewModel.incomesSources.observe(activity) {
            if (flag == 0) {
                adapter = IncomesSourceAdapter(context, it)
                adapter.attach(this)
            }

            rvIncomesSources.adapter = adapter
        }

        ibAddIncomesSource.setOnClickListener {
            findNavController().navigate(
                R.id.action_incomesSourcesFragment_to_incomesSourceAddFragment,
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

        viewModel.getIncomesSources()
    }

    override fun updatePositive() {
        findNavController().navigate(
            R.id.action_incomesSourcesFragment_to_incomesSourceEditFragment,
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
        viewModel.deleteIncomesSource(adapter.noId)
    }
}