package rustam.urazov.budgetoffamily.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.IncomeData
import java.text.SimpleDateFormat

class IncomeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class SelfViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvUser: TextView = itemView.findViewById(R.id.tvUser)

        fun bind(incomeData: IncomeData) {
            tvName.text = incomeData.name
            tvSum.text = incomeData.sum.toString()
            val datetime = incomeData.creationDate
            val formatter = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")
            tvTime.text = formatter.format(datetime)
            tvUser.text = incomeData.userId.toString()
        }
    }

    class EnemyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvUser: TextView = itemView.findViewById(R.id.tvUser)

        fun bind(incomeData: IncomeData) {
            tvName.text = incomeData.name
            tvSum.text = incomeData.sum.toString()
            val datetime = incomeData.creationDate
            val formatter = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy")
            tvTime.text = formatter.format(datetime)
            tvUser.text = incomeData.userId.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int = this::class.java.name.hashCode()

}