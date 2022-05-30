package rustam.urazov.budgetoffamily.screen.goalEdit

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
import rustam.urazov.budgetoffamily.models.GoalForEdit
import java.lang.NumberFormatException

class GoalEditScreen : Fragment(R.layout.fragment_goal_edit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etName: EditText = view.findViewById(R.id.etName)
        val etIncomePercentile: EditText = view.findViewById(R.id.etIncomePercentile)
        val etActualSum: EditText = view.findViewById(R.id.etActualSum)
        val etNecessarySum: EditText = view.findViewById(R.id.etNecessarySum)
        val ibEditGoal: ImageButton = view.findViewById(R.id.ibEditGoal)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        val id = arguments?.getInt(ID) ?: 0

        val viewModel = ViewModelProvider(
            activity,
            GoalEditScreenFactory(context)
        )[GoalEditScreenViewModel::class.java]

        viewModel.goal.observe(activity) {
            etName.setText(it.name)
            etIncomePercentile.setText(it.incomePercentile.toString())
            etActualSum.setText(it.actualSum.toString())
            etNecessarySum.setText(it.necessarySum.toString())
        }

        viewModel.success.observe(activity) {
            if (it) {
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(
                        R.id.action_goalEditFragment_to_currentGoalsFragment,
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
                    viewModel.success.value = false
                }
            }
        }

        ibBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_goalEditFragment_to_currentGoalsFragment,
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

        ibEditGoal.setOnClickListener {
            try {
                if (etName.text.length in 1..30 && etIncomePercentile.text.toString()
                        .toFloat() >= 0 && etIncomePercentile.text.toString()
                        .toFloat() <= 25 && etNecessarySum.text.toString()
                        .toFloat() >= 1 && etActualSum.text.toString()
                        .toFloat() >= 0 && etActualSum.text.toString()
                        .toFloat() <= etNecessarySum.text.toString().toFloat()
                ) {
                    viewModel.editGoal(
                        id, GoalForEdit(
                            name = etName.text.toString(),
                            incomePercentile = etIncomePercentile.text.toString().toFloat(),
                            progress = etActualSum.text.toString().toFloat(),
                            sum = etNecessarySum.text.toString().toFloat()
                        )
                    )
                } else viewModel.showError()
            } catch (e: NumberFormatException) {
                viewModel.showError()
            }
        }

        viewModel.getGoal(id)
    }

    companion object {
        const val ID = "ID"
    }
}