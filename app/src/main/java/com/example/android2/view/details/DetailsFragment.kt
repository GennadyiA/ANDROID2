package com.example.android2.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.example.android2.BuildConfig
import com.example.android2.R
import com.example.android2.databinding.FragmentDetailsBinding
import com.example.android2.model.City
import com.example.android2.model.Weather
import com.example.android2.model.FactDTO
import com.example.android2.model.WeatherDTO
import com.example.android2.viewmodel.AppState
import com.example.android2.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_details.*
import okhttp3.*
import java.io.IOException
import com.example.android2.utils.showSnackBar
import com.squareup.picasso.Picasso


const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_INTENT_EMPTY_EXTRA = "INTENT IS EMPTY"
const val DETAILS_DATA_EMPTY_EXTRA = "DATA IS EMPTY"
const val DETAILS_RESPONSE_EMPTY_EXTRA = "RESPONSE IS EMPTY"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TEMP_EXTRA = "TEMPERATURE"
const val DETAILS_FEELS_LIKE_EXTRA = "FEELS LIKE"
const val DETAILS_CONDITION_EXTRA = "CONDITION"

private const val PROCESS_ERROR = "Обработка ошибки"
private const val MAIN_LINK = "https://api.weather.yandex.ru/v2/informers?"
private const val REQUEST_API_KEY = "X-Yandex-API-Key"

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer {
            renderData(it) })
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat,
            weatherBundle.city.lon)
    }
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error), getString(R.string.reload),
                    { viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat,
                        weatherBundle.city.lon) })
            }
        }
    }
    private fun setWeather(weather: Weather) {
        val city = weatherBundle.city
        saveCity(city, weather)
        binding.cityName.text = city.city
        binding.cityCoordinates.text = String.format(getString(R.string.city_coordinates),city.lat.toString(),city.lon.toString())
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.weatherCondition.text = weather.condition
        Picasso
            .get()
            .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
            .into(headerIcon)
        weather.icon?.let {
            Glide
                .with(this)
                .load("https://yastatic.net/weather/i/icons/funky/dark/${it}.svg")
                .into(weatherIcon);


        }
    }
    private fun saveCity(
        city: City,
        weather: Weather
    ) {
        viewModel.saveCityToDB(
            Weather(
                city,
                weather.temperature,
                weather.feelsLike,
                weather.condition
            )
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
