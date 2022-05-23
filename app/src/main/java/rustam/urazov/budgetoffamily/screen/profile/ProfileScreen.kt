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

        viewModel.balance.observe(requireActivity()) {
            tvBalance.text = it.toString()
        }

        viewModel.getBalance()

        ibNotifications.setOnClickListener {
        }
        ibSignOut.setOnClickListener {

        }
    }
}