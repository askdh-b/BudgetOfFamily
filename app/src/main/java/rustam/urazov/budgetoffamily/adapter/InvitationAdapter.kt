package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.InvitationData
import rustam.urazov.budgetoffamily.observer.Observable
import rustam.urazov.budgetoffamily.observer.Observer

class InvitationAdapter(
    private val context: Context,
    var invitations: List<InvitationData>
) : RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder>(), Observable {
    private val observables = mutableListOf<Observer>()
    var yesId = 0
    var noId = 0
    class InvitationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        val ibYes: ImageButton = itemView.findViewById(R.id.ibYes)
        val ibNo: ImageButton = itemView.findViewById(R.id.ibNo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitationViewHolder =
        InvitationViewHolder(
            LayoutInflater.from(context).inflate(R.layout.invitation, parent, false)
        )

    override fun onBindViewHolder(holder: InvitationViewHolder, position: Int) {
        "${invitations[position].firstName} ${invitations[position].lastName}".also {
            holder.tvFullName.text = it
        }
        holder.ibYes.setOnClickListener {
            this.yesId = invitations[position].id
            this.noticePositive()
        }
        holder.ibNo.setOnClickListener {
            this.noId = invitations[position].id
            this.noticeNegative()
        }
    }

    override fun getItemCount(): Int = invitations.size

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