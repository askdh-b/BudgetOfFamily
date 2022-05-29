package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.SpendingData

class SpendingAdapter(
    private val context: Context,
    private val spendings: List<SpendingData>,
    private val userId: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class SelfSpendingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        private val tvUser: TextView = itemView.findViewById(R.id.tvUser)

        fun bind(spendingData: SpendingData) {
            tvName.text = spendingData.name
            tvSum.text = spendingData.sum.toString()
            tvUser.text = spendingData.firstName
        }
    }

    class EnemySpendingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvSum: TextView = itemView.findViewById(R.id.tvSum)
        private val tvUser: TextView = itemView.findViewById(R.id.tvUser)

        fun bind(spendingData: SpendingData) {
            tvName.text = spendingData.name
            tvSum.text = spendingData.sum.toString()
            tvUser.text = spendingData.firstName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.self_transaction, parent, false)
            SelfSpendingsViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(context).inflate(R.layout.enemy_transaction, parent, false)
            EnemySpendingsViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as SelfSpendingsViewHolder).bind(spendings[position])
        } else {
            (holder as EnemySpendingsViewHolder).bind(spendings[position])
        }
    }

    override fun getItemCount(): Int = spendings.size

    override fun getItemViewType(position: Int): Int = when (spendings[position].userId) {
        userId -> 1
        else -> 2
    }
}