package rustam.urazov.budgetoffamily.screen.invitations

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.adapter.InvitationAdapter
import rustam.urazov.budgetoffamily.observer.Observer

class InvitationsScreen : Fragment(R.layout.fragment_invitations), Observer {
    private lateinit var adapter: InvitationAdapter
    private var flag = 0
    lateinit var viewModel: InvitationsScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val activity = requireActivity()

        val ibBack: ImageButton = view.findViewById(R.id.ibBack)
        val rvInvitations: RecyclerView = view.findViewById(R.id.rvInvitations)

        rvInvitations.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(
            activity,
            InvitationsScreenFactory(context)
        )[InvitationsScreenViewModel::class.java]

        viewModel.invitations.observe(activity) {
            if (flag == 0) {
                adapter = InvitationAdapter(requireContext(), it)
                adapter.attach(this)
            }

            rvInvitations.adapter = adapter
        }

        viewModel.getInvitations()

        ibBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_invitationsFragment_to_profileFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph_content) {
                        inclusive = true
                    }
                }
            )
        }
    }

    override fun updatePositive() {
        viewModel.acceptInvitation(adapter.yesId)
    }

    override fun updateNegative() {
        viewModel.rejectInvitation(adapter.noId)
    }
}