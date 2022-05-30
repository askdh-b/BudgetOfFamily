package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.GoalData

class CompletedGoalAdapter(
    private val context: Context,
    private val completedGoals: List<GoalData>
) : RecyclerView.Adapter<CompletedGoalAdapter.CompletedGoalViewHolder>() {

    class CompletedGoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCurrentSum: TextView = itemView.findViewById(R.id.tvCurrentSum)
        val tvProgress: TextView = itemView.findViewById(R.id.tvProgress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedGoalViewHolder =
        CompletedGoalViewHolder(
            LayoutInflater.from(context).inflate(R.layout.completed_goal, parent, false)
        )

    override fun onBindViewHolder(holder: CompletedGoalViewHolder, position: Int) {
        holder.tvName.text = completedGoals[position].name
        holder.tvCurrentSum.text = completedGoals[position].necessarySum.toString()

        var percentage = ((completedGoals[position].actualSum / completedGoals[position].necessarySum) * 100).toInt()

        if (percentage > 100) percentage = 100

        val color = (percentage * 1.33).toFloat()
        val hsl = ColorUtils.HSLToColor(floatArrayOf(color, 1.0f, 0.6f))

        holder.tvProgress.text = percentage.toString()
        holder.tvProgress.setTextColor(hsl)
    }

    override fun getItemCount(): Int = completedGoals.size
}