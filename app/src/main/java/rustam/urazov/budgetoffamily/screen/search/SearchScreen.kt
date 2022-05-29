package rustam.urazov.budgetoffamily.screen.search

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.UserAdapter
import rustam.urazov.budgetoffamily.models.Invitation
import rustam.urazov.budgetoffamily.observer.Observer

class SearchScreen : Fragment(R.layout.fragment_search), Observer {

    private lateinit var adapter: UserAdapter
    private var flag = 0
    lateinit var viewModel: SearchScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val etSearch: EditText = view.findViewById(R.id.etSearch)
        val rvUsers: RecyclerView = view.findViewById(R.id.rvUsers)

        rvUsers.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(
            activity,
            SearchScreenFactory(context)
        )[SearchScreenViewModel::class.java]

        viewModel.users.observe(activity) {
            if (flag == 0) {
                adapter = UserAdapter(context, it)
                adapter.attach(this)
            }

            rvUsers.adapter = adapter
        }

        etSearch.addTextChangedListener {
            viewModel.getUsers(it.toString())
        }
    }

    override fun updatePositive() {
        viewModel.sendInvitation(
            Invitation(
                recipientId = adapter.yesId
            )
        )
    }

    override fun updateNegative() {

    }
}