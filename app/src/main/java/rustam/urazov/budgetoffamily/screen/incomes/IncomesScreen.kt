package rustam.urazov.budgetoffamily.screen.incomes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.IncomeAdapter

class IncomesScreen : Fragment(R.layout.fragment_incomes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvIncomes: RecyclerView = view.findViewById(R.id.rvIncomes)

        val viewModel = ViewModelProvider(
            this,
            IncomesScreenFactory(requireContext())
        )[IncomesScreenViewModel::class.java]

        val context = requireContext()
        rvIncomes.layoutManager = LinearLayoutManager(context)

        viewModel.incomes.observe(requireActivity()) { incomes ->
            viewModel.userId.observe(requireActivity()) { userId ->
                rvIncomes.adapter = IncomeAdapter(context, incomes.reversed(), userId)
            }
        }

        viewModel.getIncomes()
    }
}