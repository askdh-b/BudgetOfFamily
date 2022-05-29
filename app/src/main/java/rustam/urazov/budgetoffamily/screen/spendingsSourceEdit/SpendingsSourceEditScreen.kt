package rustam.urazov.budgetoffamily.screen.spendingsSourceEdit

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.SpendingsSource
import java.io.IOException
import java.lang.NumberFormatException

class SpendingsSourceEditScreen : Fragment(R.layout.fragment_spendings_source_edit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etName: EditText = view.findViewById(R.id.etName)
        val etSum: EditText = view.findViewById(R.id.etSum)
        val etMonthDay: EditText = view.findViewById(R.id.etMonthDay)
        val ibSave: ImageButton = view.findViewById(R.id.ibEditSpendingsSource)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        val id = arguments?.getInt(ID) ?: 0

        val viewModel = ViewModelProvider(
            activity,
            SpendingsSourceEditScreenFactory(context)
        )[SpendingsSourceEditScreenViewModel::class.java]

        viewModel.spendingsSource.observe(activity) {
            etName.setText(it.name)
            etSum.setText(it.sum.toString())
            etMonthDay.setText(it.monthDay.toString())
        }

        viewModel.success.observe(activity) {
            findNavController().navigate(
                R.id.action_spendingsSourceEditFragment_to_spendingsSourcesFragment,
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
                viewModel.editSpendingsSource(
                    SpendingsSource(
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
                R.id.action_spendingsSourceEditFragment_to_spendingsSourcesFragment,
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

        viewModel.getSpendingsSource(id)
    }

    companion object {
        const val ID = "ID"
    }
}