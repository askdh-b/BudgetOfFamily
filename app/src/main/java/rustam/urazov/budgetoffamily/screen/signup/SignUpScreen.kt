package rustam.urazov.budgetoffamily.screen.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.budgetoffamily.R

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(
            this,
            SignUpScreenFactory(requireContext())
        )[SignUpScreenViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        return view
    }
}