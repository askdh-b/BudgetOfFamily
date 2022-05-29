package rustam.urazov.budgetoffamily.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import rustam.urazov.budgetoffamily.R

class MainScreen : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bnvMain: BottomNavigationView = view.findViewById(R.id.bnvMain)
        val navController =
            (childFragmentManager.findFragmentById(R.id.fcvContent) as NavHostFragment)
                .navController
        bnvMain.setupWithNavController(navController)
    }
}