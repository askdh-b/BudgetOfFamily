package rustam.urazov.budgetoffamily.screen

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import rustam.urazov.budgetoffamily.screen.profile.ProfileScreenViewModel

class ConfirmationDialog(
    private val title: String,
    private val message: String,
    private val viewModel: ProfileScreenViewModel
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Да") { _, _ ->
                viewModel.leave()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
}

fun showConfirmationDialog(
    fragmentManager: FragmentManager,
    title: String,
    message: String,
    viewModel: ProfileScreenViewModel
) {
    val confirmationDialog = ConfirmationDialog(title, message, viewModel)
    confirmationDialog.show(fragmentManager, "")
}