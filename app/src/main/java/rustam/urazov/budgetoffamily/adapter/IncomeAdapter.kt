package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.IncomeData

class IncomeAdapter(
    private val context: Context,
    private val incomes: List<IncomeData>,
    private val userId: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class SelfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        private val tvUser: TextView = itemView.findViewById(R.id.tvUser)

        fun bind(incomeData: IncomeData) {
            tvName.text = incomeData.name
            tvSum.text = incomeData.sum.toString()
            tvUser.text = incomeData.firstName
        }
    }

    class EnemyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        private val tvUser: TextView = itemView.findViewById(R.id.tvUser)

        fun bind(incomeData: IncomeData) {
            tvName.text = incomeData.name
            tvSum.text = incomeData.sum.toString()
            tvUser.text = incomeData.firstName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.self_transaction, parent, false)
            SelfViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(context).inflate(R.layout.enemy_transaction, parent, false)
            EnemyViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as SelfViewHolder).bind(incomes[position])
        } else {
            (holder as EnemyViewHolder).bind(incomes[position])
        }
    }

    override fun getItemCount(): Int = incomes.size

    override fun getItemViewType(position: Int): Int = when (incomes[position].userId) {
        userId -> 1
        else -> 2
    }
}