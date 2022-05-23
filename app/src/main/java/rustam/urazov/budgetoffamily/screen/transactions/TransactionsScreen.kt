package rustam.urazov.budgetoffamily.screen.transactions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import rustam.urazov.budgetoffamily.R

class TransactionsScreen : Fragment(R.layout.fragment_transactions) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bnvTransactions: BottomNavigationView = view.findViewById(R.id.bnvTransactions)
        val navController = (childFragmentManager.findFragmentById(R.id.fcvTransactions) as NavHostFragment)
            .navController
        bnvTransactions.setupWithNavController(navController)
    }
}