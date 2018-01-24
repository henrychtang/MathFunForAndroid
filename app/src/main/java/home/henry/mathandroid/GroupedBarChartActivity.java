package home.henry.mathandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import afu.org.checkerframework.checker.igj.qual.I;

public class GroupedBarChartActivity extends Activity {

    BarChart barChart2;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouped_bar_chart);

        barChart = findViewById(R.id.groupedBarChart);
        barChart2 = findViewById(R.id.groupedBarChart2);

        // create BarEntry for Bar Group 1
        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
        bargroup1.add(new BarEntry(0, 8));
        bargroup1.add(new BarEntry(1, 4));
        bargroup1.add(new BarEntry(2, 2));
        bargroup1.add(new BarEntry(3, 12));

        ArrayList<BarEntry> bargroup2 = new ArrayList<>();
        bargroup2.add(new BarEntry(4, 4));
        bargroup2.add(new BarEntry(5, 5));
        bargroup2.add(new BarEntry(6, 3));
        bargroup2.add(new BarEntry(7, 6));

        ArrayList<BarEntry> bargroup3 = new ArrayList<>();
        bargroup3.add(new BarEntry(0, 9));
        bargroup3.add(new BarEntry(1, 9));
        bargroup3.add(new BarEntry(2, 2));
        bargroup3.add(new BarEntry(3, 3));

        ArrayList<BarEntry> bargroup4 = new ArrayList<>();
        bargroup4.add(new BarEntry(4, 9));
        bargroup4.add(new BarEntry(5, 4));
        bargroup4.add(new BarEntry(6, 3));
        bargroup4.add(new BarEntry(7, 15));

// creating dataset for Bar Group1
        BarDataSet barDataSet1 = new BarDataSet(bargroup1, "Addition");
        barDataSet1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet1.setValueTextSize(11f);
        barDataSet1.setBarBorderWidth(1);
        barDataSet1.setFormSize(4);
        barDataSet1.setValueTextColor(Color.RED);

// creating dataset for Bar Group 2
        BarDataSet barDataSet2 = new BarDataSet(bargroup2, "Subtraction");
        barDataSet2.setColors(ColorTemplate.PASTEL_COLORS);
        barDataSet2.setValueTextSize(11f);
        barDataSet2.setBarBorderWidth(1);
        barDataSet2.setFormSize(4);
        barDataSet2.setValueTextColor(Color.RED);

        BarDataSet barDataSet3 = new BarDataSet(bargroup3, "Multiplication");
        barDataSet3.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet3.setValueTextSize(11f);
        barDataSet3.setBarBorderWidth(1);
        barDataSet3.setFormSize(4);
        barDataSet3.setValueTextColor(Color.RED);

        BarDataSet barDataSet4 = new BarDataSet(bargroup4, "Division");
        barDataSet4.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet4.setValueTextSize(11f);
        barDataSet4.setBarBorderWidth(1);
        barDataSet4.setFormSize(4);
        barDataSet4.setValueTextColor(Color.RED);
        //barDat
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Agnes");
        labels.add("Anson");
        labels.add("Audrey");
        labels.add("Henry");
        labels.add("Agnes");
        labels.add("Anson");
        labels.add("Audrey");
        labels.add("Henry");



        ArrayList<IBarDataSet> dataSets = new ArrayList<>();  // combined all dataset into an arraylist
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        ArrayList<IBarDataSet> dataSets2 = new ArrayList<>();  // combined all dataset into an arraylist
        dataSets2.add(barDataSet3);
        dataSets2.add(barDataSet4);




// initialize the Bardata with argument labels and dataSet
        BarData data = new BarData(dataSets);
        barChart.setDescription(null);
        barChart.animateY(2000);
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setTextColor(Color.RED);
        barChart.getXAxis().setTextSize(11f);
        barChart.getXAxis().setLabelRotationAngle(30f);
        barChart.getAxisLeft().setTextColor(Color.RED);
        barChart.getAxisLeft().setTextSize(11f);
        barChart.getAxisRight().setTextColor(Color.RED);
        barChart.getAxisRight().setTextSize(11f);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setTextSize((11f));
        l.setFormSize(9f);
        l.setTextColor(Color.RED);
        l.setXEntrySpace(4f);


        BarData data2 = new BarData(dataSets2);
        barChart2.setDescription(null);
        barChart2.animateY(2000);
        barChart2.setData(data2);
        barChart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart2.getXAxis().setDrawGridLines(false);
        barChart2.getXAxis().setTextColor(Color.RED);
        barChart2.getXAxis().setTextSize(11f);
        barChart2.getXAxis().setLabelRotationAngle(30f);
        barChart2.getAxisLeft().setTextColor(Color.RED);
        barChart2.getAxisLeft().setTextSize(11f);
        barChart2.getAxisRight().setTextColor(Color.RED);
        barChart2.getAxisRight().setTextSize(11f);

        Legend l2 = barChart2.getLegend();
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l2.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l2.setDrawInside(false);
        l2.setForm(Legend.LegendForm.SQUARE);
        l2.setTextSize((11f));
        l2.setFormSize(9f);
        l2.setTextColor(Color.RED);
        l2.setXEntrySpace(4f);
    }
}
