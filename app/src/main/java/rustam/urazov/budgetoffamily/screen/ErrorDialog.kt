package rustam.urazov.budgetoffamily.screen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class ErrorDialog(private val message: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("Ок") { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
    } ?: throw IllegalStateException("Activity cannot be null")
}

fun showErrorDialog(fragmentManager: FragmentManager, message: String) {
    val errorDialog = ErrorDialog(message)
    errorDialog.show(fragmentManager, "")
}