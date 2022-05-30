package rustam.urazov.budgetoffamily.screen.goals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import rustam.urazov.budgetoffamily.R

class GoalsScreen : Fragment(R.layout.fragment_goals) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bnvGoals: BottomNavigationView = view.findViewById(R.id.bnvGoals)
        val navController =
            (childFragmentManager.findFragmentById(R.id.fcvGoals) as NavHostFragment)
                .navController
        bnvGoals.setupWithNavController(navController)
    }
}