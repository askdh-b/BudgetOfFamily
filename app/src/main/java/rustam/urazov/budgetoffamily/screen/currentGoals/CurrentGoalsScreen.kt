package rustam.urazov.budgetoffamily.screen.currentGoals

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
import rustam.urazov.budgetoffamily.adapter.CurrentGoalAdapter
import rustam.urazov.budgetoffamily.observer.Observer
import rustam.urazov.budgetoffamily.screen.goalEdit.GoalEditScreen

class CurrentGoalsScreen : Fragment(R.layout.fragment_current_goals), Observer {

    private lateinit var adapterCurrent: CurrentGoalAdapter
    lateinit var viewModel: CurrentGoalsScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        viewModel =
            ViewModelProvider(
                this,
                CurrentGoalsScreenFactory(context)
            )[CurrentGoalsScreenViewModel::class.java]

        val rvCurrentGoals: RecyclerView = view.findViewById(R.id.rvCurrentGoals)
        val ibAddGoal: ImageButton = view.findViewById(R.id.ibAddGoal)

        viewModel.currentGoals.observe(activity) { goals ->
            if (goals.isNotEmpty()) {
                adapterCurrent = CurrentGoalAdapter(context, goals)
                this.adapterCurrent.attach(this)

                rvCurrentGoals.adapter = adapterCurrent
            }
        }

        rvCurrentGoals.layoutManager = LinearLayoutManager(context)

        ibAddGoal.setOnClickListener {
            findNavController().navigate(
                R.id.action_currentGoalsFragment_to_goalAddFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph_goals) {
                        inclusive = true
                    }
                }
            )
        }

        viewModel.getGoals()
    }

    override fun updatePositive() {
        findNavController().navigate(
            R.id.action_currentGoalsFragment_to_goalEditFragment,
            bundleOf(GoalEditScreen.ID to adapterCurrent.yesId),
            navOptions {
                anim {
                    enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                }
                launchSingleTop = true
                popUpTo(R.id.nav_graph_goals) {
                    inclusive = true
                }
            }
        )
    }

    override fun updateNegative() {
        viewModel.deleteGoal(adapterCurrent.noId)
    }
}