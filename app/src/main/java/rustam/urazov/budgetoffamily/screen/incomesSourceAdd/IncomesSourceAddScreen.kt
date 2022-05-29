package rustam.urazov.budgetoffamily.screen.incomesSourceAdd

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
import rustam.urazov.budgetoffamily.models.IncomesSource
import java.lang.NumberFormatException

class IncomesSourceAddScreen : Fragment(R.layout.fragment_incomes_source_add) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etName: EditText = view.findViewById(R.id.etName)
        val etSum: EditText = view.findViewById(R.id.etSum)
        val etMonthDay: EditText = view.findViewById(R.id.etMonthDay)
        val ibSave: ImageButton = view.findViewById(R.id.ibAddIncomesSource)
        val ibBack: ImageButton = view.findViewById(R.id.ibBack)

        val viewModel = ViewModelProvider(
            activity,
            IncomesSourceAddScreenFactory(context)
        )[IncomesSourceAddScreenViewModel::class.java]

        viewModel.success.observe(activity) {
            if (it) {
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(
                        R.id.action_incomesSourceAddFragment_to_incomesSourcesFragment,
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
                    viewModel.success.value = false
                }
            }
        }

        ibSave.setOnClickListener {
            try {
                viewModel.addIncomesSource(
                    IncomesSource(
                        name = etName.text.toString(),
                        sum = etSum.text.toString().toFloat(),
                        monthDay = etMonthDay.text.toString().toInt()
                    )
                )
            } catch (e: NumberFormatException) {
                viewModel.showError()
            }
        }

        ibBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_incomesSourceAddFragment_to_incomesSourcesFragment,
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
    }
}