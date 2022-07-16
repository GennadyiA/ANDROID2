package com.example.android2.view.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.android2.viewmodel.AppState
import com.example.android2.R
import com.google.android.material.snackbar.Snackbar
import com.example.android2.databinding.FragmentDetailsBinding
import com.example.android2.model.Weather
import com.example.android2.view.main.MainFragmentAdapter
import com.example.android2.viewmodel.MainViewModel
import com.example.android2.view.main.MainFragment


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?.let { weather ->
            weather.city.also {
                renderData(weather)
            }
        }
    }
    private fun renderData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weather.city.lat.toString(),
                weather.city.lon.toString()
            )
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()

        }
    }
    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
        fun newInstance() =
            DetailsFragment()
    }
}


