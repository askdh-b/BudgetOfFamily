package rustam.urazov.budgetoffamily.screen.spendings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.SpendingAdapter

class SpendingsScreen : Fragment(R.layout.fragment_spendings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val rvSpendings: RecyclerView = view.findViewById(R.id.rvSpendings)


        val viewModel = ViewModelProvider(
            this,
            SpendingsScreenFactory(context)
        )[SpendingsScreenViewModel::class.java]

        rvSpendings.layoutManager = LinearLayoutManager(context)

        viewModel.spendings.observe(activity) { spendings ->
            viewModel.userId.observe(activity) { userId ->
                rvSpendings.adapter = SpendingAdapter(context, spendings.reversed(), userId)
            }
        }

        viewModel.getSpendings()
    }
}