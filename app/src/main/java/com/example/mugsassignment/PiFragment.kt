package com.example.mugsassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.mugsassignment.databinding.FragmentPiBinding

class PiFragment : Fragment(R.layout.fragment_pi) {
    private val salary = listOf(40,30,20,10)
    private val months = listOf("40", "30", "20", "10")
    private var _binding: FragmentPiBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPiBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configCharView()
    }
    private fun configCharView(){
        val pie: Pie = AnyChart.pie()
        val dataPieChart: MutableList<DataEntry> = mutableListOf()
        for (index in salary.indices){
            dataPieChart.add(ValueDataEntry(months.elementAt(index),salary.elementAt(index)))
        }
        pie.data(dataPieChart)
        pie.title("Pie Chart")
        binding?.piChartFragment?.setChart(pie)
    }
}