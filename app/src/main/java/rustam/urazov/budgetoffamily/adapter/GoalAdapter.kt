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

class GoalAdapter(private val context: Context, private val goals: List<GoalData>) :
    RecyclerView.Adapter<GoalAdapter.ViewHolder>(), Observable {

    private val observables = mutableListOf<Observer>()
    var yesId = 0
    var noId = 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCurrentSum: TextView = itemView.findViewById(R.id.tvCurrentSum)
        val tvIncomePercentage: TextView = itemView.findViewById(R.id.tvIncomePercentage)
        val tvProgress: TextView = itemView.findViewById(R.id.tvProgress)
        val ibGoalEdit: ImageView = itemView.findViewById(R.id.ibGoalEdit)
        val ibGoalDelete: ImageView = itemView.findViewById(R.id.ibGoalDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.goal, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = goals[position].name
        holder.tvCurrentSum.text = goals[position].necessarySum.toString()
        holder.tvIncomePercentage.text = goals[position].incomePercentile.toString()

        var percentage = ((goals[position].actualSum / goals[position].necessarySum) * 100).toInt()
        val color = (percentage * 1.33).toFloat()
        val hsl = ColorUtils.HSLToColor(floatArrayOf(color, 1.0f, 0.6f))

        if (percentage > 100) percentage = 100

        holder.tvProgress.text = percentage.toString()
        holder.tvProgress.setTextColor(hsl)

        holder.ibGoalEdit.setOnClickListener {
            yesId = goals[position].id
            noticePositive()
        }

        holder.ibGoalDelete.setOnClickListener {
            noId = goals[position].id
            noticeNegative()
        }
    }

    override fun getItemCount(): Int = goals.size

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