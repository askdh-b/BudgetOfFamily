package rustam.urazov.budgetoffamily.screen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class InfoDialog(private val title: String, private val message: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ок") { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
}

fun showInfoDialog(fragmentManager: FragmentManager, title: String, message: String) {
    val infoDialog = InfoDialog(title, message)
    infoDialog.show(fragmentManager, "")
}