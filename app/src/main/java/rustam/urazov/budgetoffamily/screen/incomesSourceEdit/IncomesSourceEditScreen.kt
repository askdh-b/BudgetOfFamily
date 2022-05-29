package rustam.urazov.budgetoffamily.screen.incomesSourceEdit

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.IncomesSource

class IncomesSourceEditScreen : Fragment(R.layout.fragment_incomes_source_edit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etName: EditText = view.findViewById(R.id.etName)
        val etSum: EditText = view.findViewById(R.id.etSum)
        val etMonthDay: EditText = view.findViewById(R.id.etMonthDay)
        val ibSave: ImageButton = view.findViewById(R.id.ibEditIncomesSource)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        val id = arguments?.getInt(ID) ?: 0

        val viewModel = ViewModelProvider(
            activity,
            IncomesSourceEditScreenFactory(context)
        )[IncomesSourceEditScreenViewModel::class.java]

        viewModel.incomesSource.observe(activity) {
            etName.setText(it.name)
            etSum.setText(it.sum.toString())
            etMonthDay.setText(it.monthDay.toString())
        }

        viewModel.success.observe(activity) {
            findNavController().navigate(
                R.id.action_incomesSourceEditFragment_to_incomesSourcesFragment,
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

        ibSave.setOnClickListener {
            try {
                viewModel.editIncomesSource(
                    IncomesSource(
                        name = etName.text.toString(),
                        sum = etSum.text.toString().toFloat(),
                        monthDay = etMonthDay.text.toString().toInt()
                    ), id
                )
            } catch (e: NumberFormatException) {
                viewModel.showError()
            }
        }

        ibBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_incomesSourceEditFragment_to_incomesSourcesFragment,
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

        viewModel.getIncomesSource(id)
    }

    companion object {
        const val ID = "ID"
    }
}