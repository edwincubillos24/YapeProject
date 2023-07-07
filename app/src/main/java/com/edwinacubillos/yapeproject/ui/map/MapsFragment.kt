package com.edwinacubillos.yapeproject.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.edwinacubillos.yapeproject.R
import com.edwinacubillos.yapeproject.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val args: MapsFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->

        val recipe = args.localRecipe

        val country = LatLng(recipe.latitude, recipe.longitude)
        googleMap.addMarker(MarkerOptions().position(country).title(recipe.name).snippet(recipe.originCountry))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(country, 15.0F))
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val mapBinding = FragmentMapsBinding.inflate(inflater, container, false)
        return mapBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
