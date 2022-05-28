package rustam.urazov.budgetoffamily.screen.goalAdd

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.Goal

class GoalAddScreen : Fragment(R.layout.fragment_goal_add) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etName: EditText = view.findViewById(R.id.etName)
        val etIncomePercentile: EditText = view.findViewById(R.id.etIncomePercentile)
        val etNecessarySum: EditText = view.findViewById(R.id.etNecessarySum)
        val ibAddGoal: ImageButton = view.findViewById(R.id.ibAddGoal)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        val viewModel = ViewModelProvider(
            activity,
            GoalAddScreenFactory(context)
        )[GoalAddScreenViewModel::class.java]

        viewModel.success.observe(activity) {

        }

        ibBack.setOnClickListener {

        }

        ibAddGoal.setOnClickListener {
            viewModel.addGoal(
                Goal(
                    name = etName.text.toString(),
                    incomePercentile = etIncomePercentile.text.toString().toFloat(),
                    sum = etNecessarySum.text.toString().toFloat()
                )
            )
        }
    }
}