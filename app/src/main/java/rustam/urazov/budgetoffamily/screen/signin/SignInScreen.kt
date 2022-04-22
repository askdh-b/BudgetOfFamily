package rustam.urazov.budgetoffamily.screen.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.budgetoffamily.R


class SignInScreen : Fragment() {

    private lateinit var viewModel: SignInScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[SignInScreenViewModel::class.java]

        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }
}