package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.SpendingsSourceData
import rustam.urazov.budgetoffamily.observer.Observable
import rustam.urazov.budgetoffamily.observer.Observer

class SpendingsSourceAdapter(
    private val context: Context,
    private val spendingsSource: List<SpendingsSourceData>
) : RecyclerView.Adapter<SpendingsSourceAdapter.SpendingsSourceViewHolder>(), Observable {

    private val observables = mutableListOf<Observer>()
    var yesId: Int = 0
    var noId: Int = 0

    class SpendingsSourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMonthDay: TextView = itemView.findViewById(R.id.tvMonthDay)
        val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        val ibSpendingsSourceEdit: ImageButton = itemView.findViewById(R.id.ibSpendingsSourceEdit)
        val ibIncomesSourceDelete: ImageButton = itemView.findViewById(R.id.ibSpendingsSourceDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpendingsSourceViewHolder = SpendingsSourceViewHolder(
        LayoutInflater.from(context).inflate(R.layout.spendings_source, parent, false)
    )

    override fun onBindViewHolder(holder: SpendingsSourceViewHolder, position: Int) {
        holder.tvName.text = spendingsSource[position].name
        holder.tvMonthDay.text = spendingsSource[position].monthDay.toString()
        holder.tvSum.text = spendingsSource[position].sum.toString()

        holder.ibSpendingsSourceEdit.setOnClickListener {
            this.yesId = spendingsSource[position].id
            this.noticePositive()
        }

        holder.ibIncomesSourceDelete.setOnClickListener {
            this.noId = spendingsSource[position].id
            this.noticeNegative()
        }
    }

    override fun getItemCount(): Int = spendingsSource.size

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