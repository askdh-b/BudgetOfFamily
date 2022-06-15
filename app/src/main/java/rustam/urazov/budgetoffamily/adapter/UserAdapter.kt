package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.UserData
import rustam.urazov.budgetoffamily.observer.Observable
import rustam.urazov.budgetoffamily.observer.Observer

class UserAdapter(
    private val context: Context,
    private val users: List<UserData>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), Observable {

    private val observables = mutableListOf<Observer>()
    var yesId: Int = 0

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        val ibAdd: ImageButton = itemView.findViewById(R.id.ibAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        "${users[position].firstName} ${users[position].lastName}(${users[position].username})".also {
            holder.tvFullName.text = it
        }

        holder.ibAdd.setOnClickListener {
            yesId = users[position].id
            noticePositive()
        }
    }

    override fun getItemCount(): Int = users.size

    override fun attach(observer: Observer) {
        observables.add(observer)
    }

    override fun detach(observer: Observer) {
        observables.remove(observer)
    }

    override fun noticePositive() {
        for (o in observables) {
            o.updatePositive()
        }
    }

    override fun noticeNegative() {
        for (o in observables) {
            o.updateNegative()
        }
    }
}