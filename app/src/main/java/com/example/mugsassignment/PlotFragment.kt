package com.example.mugsassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.charts.Radar
import com.anychart.core.annotations.VerticalLine
import com.example.mugsassignment.databinding.FragmentPlotBinding

class PlotFragment : Fragment(R.layout.fragment_plot) {
    private var _binding: FragmentPlotBinding? = null
    private val binding get() = _binding
    private val salary = listOf(31,30,31,30,31,31,30,31,30,31,28,30)
    private val months = listOf("December","November","October","September","August","July","June","May","April","March","February","January")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlotBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val radar: Cartesian? = AnyChart.vertical()
        radar?.title("XY Chart for Months and Days")
        val dataPlot: MutableList<DataEntry> = mutableListOf()
        for (index in salary.indices){
            dataPlot.add(ValueDataEntry(months.elementAt(index),salary.elementAt(index)))
        }
        radar?.yScale()?.minimum(26)
        radar?.yScale()?.maximum(32)
        radar?.data(dataPlot)
        binding?.plotChartFragment?.setChart(radar)
    }
}