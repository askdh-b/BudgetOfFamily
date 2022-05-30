package rustam.urazov.budgetoffamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import rustam.urazov.budgetoffamily.R
import rustam.urazov.budgetoffamily.models.GoalData
import rustam.urazov.budgetoffamily.observer.Observable
import rustam.urazov.budgetoffamily.observer.Observer

class CurrentGoalAdapter(private val context: Context, private val currentGoals: List<GoalData>) :
    RecyclerView.Adapter<CurrentGoalAdapter.CurrentGoalViewHolder>(), Observable {

    private val observables = mutableListOf<Observer>()
    var yesId = 0
    var noId = 0

    class CurrentGoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCurrentSum: TextView = itemView.findViewById(R.id.tvCurrentSum)
        val tvIncomePercentage: TextView = itemView.findViewById(R.id.tvIncomePercentage)
        val tvProgress: TextView = itemView.findViewById(R.id.tvProgress)
        val ibGoalEdit: ImageView = itemView.findViewById(R.id.ibGoalEdit)
        val ibGoalDelete: ImageView = itemView.findViewById(R.id.ibGoalDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentGoalViewHolder =
        CurrentGoalViewHolder(LayoutInflater.from(context).inflate(R.layout.current_goal, parent, false))

    override fun onBindViewHolder(holder: CurrentGoalViewHolder, position: Int) {
        holder.tvName.text = currentGoals[position].name
        holder.tvCurrentSum.text = currentGoals[position].necessarySum.toString()
        holder.tvIncomePercentage.text = currentGoals[position].incomePercentile.toString()

        var percentage = ((currentGoals[position].actualSum / currentGoals[position].necessarySum) * 100).toInt()
        val color = (percentage * 1.33).toFloat()
        val hsl = ColorUtils.HSLToColor(floatArrayOf(color, 1.0f, 0.6f))

        if (percentage > 100) percentage = 100

        holder.tvProgress.text = percentage.toString()
        holder.tvProgress.setTextColor(hsl)

        holder.ibGoalEdit.setOnClickListener {
            yesId = currentGoals[position].id
            noticePositive()
        }

        holder.ibGoalDelete.setOnClickListener {
            noId = currentGoals[position].id
            noticeNegative()
        }
    }

    override fun getItemCount(): Int = currentGoals.size

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