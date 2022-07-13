package com.example.android2.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android2.R
import com.example.android2.model.Weather
import com.example.android2.databinding.FragmentMainRecyclerItemBinding

class MainFragmentAdapter(private var onItemViewClickListener:
                          MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var weatherData: List<Weather> = listOf()
    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false) as
                    View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }
    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weather: Weather) {
//            val binding = FragmentMainRecyclerItemBinding.bind(itemView)
//            binding.mainFragmentRecyclerItemTextView.text = weather.city.city
//            binding.root.setOnClickListener {
//                onItemViewClickListener?.onItemViewClick(weather)
//            }
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                weather.city.city
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(weather)
//            val binding= FragmentMainRecyclerItemBinding.bind(itemView)
//            binding.cityName.text = weather.city.city
//            binding.root.setOnClickListener {
//                callback.onItemClick(weather)

//            }
            }
        }
    }
}
