package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.IncomesSourceData
import rustam.urazov.budgetoffamily.observer.Observable
import rustam.urazov.budgetoffamily.observer.Observer

class IncomesSourceAdapter(
    private val context: Context,
    private val incomesSources: List<IncomesSourceData>
) : RecyclerView.Adapter<IncomesSourceAdapter.IncomesSourceViewHolder>(), Observable {

    private val observables = mutableListOf<Observer>()
    var yesId: Int = 0
    var noId: Int = 0

    class IncomesSourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMonthDay: TextView = itemView.findViewById(R.id.tvMonthDay)
        val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        val ibIncomesSourceEdit: ImageButton = itemView.findViewById(R.id.ibIncomesSourceEdit)
        val ibIncomesSourceDelete: ImageButton = itemView.findViewById(R.id.ibIncomesSourceDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomesSourceViewHolder =
        IncomesSourceViewHolder(
            LayoutInflater.from(context).inflate(R.layout.incomes_source, parent, false)
        )

    override fun onBindViewHolder(holder: IncomesSourceViewHolder, position: Int) {
        holder.tvName.text = incomesSources[position].name
        holder.tvMonthDay.text = incomesSources[position].monthDay.toString()
        holder.tvSum.text = incomesSources[position].sum.toString()
        holder.ibIncomesSourceEdit.setOnClickListener {
            this.yesId = incomesSources[position].id
            this.noticePositive()
        }
        holder.ibIncomesSourceDelete.setOnClickListener {
            this.noId = incomesSources[position].id
            this.noticeNegative()
        }
    }

    override fun getItemCount(): Int = incomesSources.size
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