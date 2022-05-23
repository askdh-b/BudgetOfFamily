package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.GoalData

class GoalAdapter(private val context: Context, private val goals: List<GoalData>) : RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCurrentSum: TextView = itemView.findViewById(R.id.tvCurrentSum)
        val tvIncomePercentage: TextView = itemView.findViewById(R.id.tvIncomePercentage)
        val tvProgress: TextView = itemView.findViewById(R.id.tvProgress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.goal, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = goals[position].name
        holder.tvCurrentSum.text = goals[position].necessarySum.toString()
        holder.tvIncomePercentage.text = goals[position].incomePercentile.toString()
        holder.tvProgress.text = ((goals[position].actualSum / goals[position].necessarySum) * 100).toInt().toString()
    }

    override fun getItemCount(): Int = goals.size
}