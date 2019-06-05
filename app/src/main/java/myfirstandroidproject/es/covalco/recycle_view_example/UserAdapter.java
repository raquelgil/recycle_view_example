package myfirstandroidproject.es.covalco.recycle_view_example

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import myfirstandroidproject.es.covalco.recycle_view_example.UserAdapter.ViewHolder

class UserAdapter(private val userModelList: List<UserModel>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // Creamos la lista sin personalizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_row, parent, false)
        return ViewHolder(v)
    }

    // Personalizamos el ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = userModelList[position].name
        holder.name.text = name
    }

    // Indicamos el número de elementos de la lista
    override fun getItemCount(): Int {
        return userModelList.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val name: TextView

        init {
            name = v.findViewById(R.id.textUserName) as TextView
        }
    }
}
