package rustam.urazov.budgetoffamily.screen.completedGoals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.CompletedGoalAdapter

class CompletedGoalsScreen : Fragment(R.layout.fragment_completed_goals) {

    private lateinit var adapter: CompletedGoalAdapter
    lateinit var viewModel: CompletedGoalsScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        viewModel =
            ViewModelProvider(
                this,
                CompletedGoalsScreenFactory(context)
            )[CompletedGoalsScreenViewModel::class.java]

        val rvCompletedGoals: RecyclerView = view.findViewById(R.id.rvCompletedGoals)

        viewModel.completedGoals.observe(activity) { goals ->
            if (goals.isNotEmpty()) {
                adapter = CompletedGoalAdapter(context, goals)
                rvCompletedGoals.adapter = adapter
            }
        }

        rvCompletedGoals.layoutManager = LinearLayoutManager(context)

        viewModel.getGoals()
    }
}