package pl.kobasebastian.slidepracticaltest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kobasebastian.slidepracticaltest.R
import pl.kobasebastian.slidepracticaltest.data.model.Data
import pl.kobasebastian.slidepracticaltest.util.DateTimeConverter

class UsersAdapter(private val list: List<Data>) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater, parent)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: Data = list[position]
        holder.bind(user)
    }

}

class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.user_item, parent, false)) {

    private val name = itemView.findViewById(R.id.name) as TextView
    private val email = itemView.findViewById(R.id.email) as TextView
    private val relativeTime = itemView.findViewById(R.id.relative_time) as TextView

    fun bind(user: Data) {
        name.text = user.name
        email.text = user.email
        relativeTime.text = DateTimeConverter.getStringTimeFrom(user.created_at)
    }
}