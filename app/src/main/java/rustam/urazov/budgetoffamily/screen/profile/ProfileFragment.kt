package rustam.urazov.budgetoffamily.screen.profile

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import rustam.urazov.budgetoffamily.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ibNotifications: ImageButton = view.findViewById(R.id.ibSignOut)
        val ibSignOut: AppCompatImageButton = view.findViewById(R.id.ibSignOut)

        ibNotifications.setOnClickListener {

        }
        ibSignOut.setOnClickListener {

        }
    }
}