package rustam.urazov.budgetoffamily.screen.profile

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.budgetoffamily.R

class ProfileScreen : Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            ProfileScreenFactory(requireContext())
        )[ProfileScreenViewModel::class.java]

        val ibNotifications: ImageButton = view.findViewById(R.id.ibSignOut)
        val ibSignOut: ImageButton = view.findViewById(R.id.ibSignOut)
        val tvBalance: TextView = view.findViewById(R.id.tvBalance)
        val tvIncomesSource: TextView = view.findViewById(R.id.tvIncomesSources)
        val tvSpendingsSource: TextView = view.findViewById(R.id.tvSpendingsSources)

        viewModel.balance.observe(requireActivity()) {
            tvBalance.text = it.toString()
        }

        viewModel.incomesSourcesValue.observe(requireActivity()) {
            tvIncomesSource.text = it.toString()
        }

        viewModel.spendingsSourcesValue.observe(requireActivity()) {
            tvSpendingsSource.text = it.toString()
        }

        viewModel.getBalance()
        viewModel.getIncomesSources()
        viewModel.getSpendingsSources()

        ibNotifications.setOnClickListener {

        }
        ibSignOut.setOnClickListener {

        }
    }
}