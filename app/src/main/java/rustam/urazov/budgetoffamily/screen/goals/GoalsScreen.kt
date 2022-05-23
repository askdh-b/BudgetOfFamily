package rustam.urazov.budgetoffamily.screen.goals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.GoalAdapter

class GoalsScreen : Fragment(R.layout.fragment_goals) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            ViewModelProvider(
                this,
                GoalsScreenFactory(requireContext())
            )[GoalsScreenViewModel::class.java]

        val rvCurrentGoals: RecyclerView = view.findViewById(R.id.rvCurrentGoals)
        val rvCompletedGoals: RecyclerView = view.findViewById(R.id.rvCompletedGoals)

        viewModel.currentGoals.observe(requireActivity()) { goals ->
            rvCurrentGoals.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = GoalAdapter(requireContext(), goals)
            }
        }
        
        viewModel.completedGoals.observe(requireActivity()) { goals ->
            rvCompletedGoals.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = GoalAdapter(requireContext(), goals)
            }
        }

        viewModel.getGoals()
    }
}