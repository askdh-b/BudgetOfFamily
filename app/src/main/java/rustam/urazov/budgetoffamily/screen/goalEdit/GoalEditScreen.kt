package rustam.urazov.budgetoffamily.screen.goalEdit

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.GoalForEdit

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

        }

        ibBack.setOnClickListener {

        }

        ibEditGoal.setOnClickListener {
            viewModel.editGoal(
                id, GoalForEdit(
                    name = etName.text.toString(),
                    incomePercentile = etIncomePercentile.text.toString().toFloat(),
                    progress = etActualSum.text.toString().toFloat(),
                    sum = etNecessarySum.text.toString().toFloat()
                )
            )
        }

        viewModel.getGoal(id)
    }

    companion object {
        const val ID = "ID"
    }
}