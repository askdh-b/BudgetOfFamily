package rustam.urazov.budgetoffamily.screen.goals

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
import rustam.urazov.budgetoffamily.adapter.GoalAdapter
import rustam.urazov.budgetoffamily.observer.Observer
import rustam.urazov.budgetoffamily.screen.goalEdit.GoalEditScreen

class GoalsScreen : Fragment(R.layout.fragment_goals), Observer {

    private lateinit var adapter: GoalAdapter
    private lateinit var adapter1: GoalAdapter
    lateinit var viewModel: GoalsScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        viewModel =
            ViewModelProvider(
                this,
                GoalsScreenFactory(context)
            )[GoalsScreenViewModel::class.java]

        val rvCurrentGoals: RecyclerView = view.findViewById(R.id.rvCurrentGoals)
        val rvCompletedGoals: RecyclerView = view.findViewById(R.id.rvCompletedGoals)
        val ibAddGoal: ImageButton = view.findViewById(R.id.ibAddGoal)

        viewModel.currentGoals.observe(activity) { goals ->
            if (goals.isNotEmpty()) {
                adapter = GoalAdapter(context, goals)
                this.adapter.attach(this)

                rvCurrentGoals.adapter = adapter
            }
        }

        viewModel.completedGoals.observe(activity) { goals ->
            if (goals.isNotEmpty()) {
                adapter1 = GoalAdapter(context, goals)
                adapter1.attach(this)
                rvCompletedGoals.apply {
                    adapter = adapter1
                }
            }
        }

        rvCurrentGoals.layoutManager = LinearLayoutManager(context)
        rvCompletedGoals.layoutManager = LinearLayoutManager(context)

        ibAddGoal.setOnClickListener {
            findNavController().navigate(
                R.id.action_goalsFragment_to_goalAddFragment,
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

        viewModel.getGoals()
    }

    override fun updatePositive() {
        findNavController().navigate(
            R.id.action_goalsFragment_to_goalEditFragment,
            bundleOf(GoalEditScreen.ID to adapter.yesId),
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
        viewModel.deleteGoal(adapter.noId)
    }
}