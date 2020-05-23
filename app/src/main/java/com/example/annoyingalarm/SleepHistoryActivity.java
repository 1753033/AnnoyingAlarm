package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class SleepHistoryActivity extends AppCompatActivity {
    private CombinedChart mChart1;
    private CombinedChart mChart2;
    private ImageButton btnAlarm,btnWeather,btnNews,btnNight,btnMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_sleep_history);
        btnWeather = findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToWeather = new Intent(SleepHistoryActivity.this,WeatherActivity.class);
                startActivity(switchToWeather);
            }
        });
        btnNews = findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNews = new Intent(SleepHistoryActivity.this,NewsActivity.class);
                startActivity(switchToNews);
            }
        });
        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNight = new Intent(SleepHistoryActivity.this,MainActivity.class);
                startActivity(switchToNight);
            }
        });
        btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToMore = new Intent(SleepHistoryActivity.this,MoreActivity.class);
                startActivity(switchToMore);
            }
        });
//        mChart1 = (CombinedChart) findViewById(R.id.combinedChart1);
//        mChart1.getDescription().setEnabled(false);
//        mChart1.setBackgroundColor(Color.WHITE);
//        mChart1.setDrawGridBackground(false);
//        mChart1.setDrawBarShadow(false);
//        mChart1.setHighlightFullBarEnabled(false);
//        mChart1.setOnChartValueSelectedListener((OnChartValueSelectedListener) this);
//
//        YAxis rightAxis = mChart1.getAxisRight();
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setAxisMinimum(0f);
//
//        YAxis leftAxis = mChart1.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMinimum(0f);
//
//        final List<String> xLabel = new ArrayList<>();
//        xLabel.add("Jan");
//        xLabel.add("Feb");
//        xLabel.add("Mar");
//        xLabel.add("Apr");
//        xLabel.add("May");
//        xLabel.add("Jun");
//        xLabel.add("Jul");
//        xLabel.add("Aug");
//        xLabel.add("Sep");
//        xLabel.add("Oct");
//        xLabel.add("Nov");
//        xLabel.add("Dec");
//
//        XAxis xAxis = mChart1.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setAxisMinimum(0f);
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return xLabel.get((int) value % xLabel.size());
//            }
//        });
//
//        CombinedData data = new CombinedData();
//        LineData lineDatas = new LineData();
//        lineDatas.addDataSet((ILineDataSet) dataChart());
//
//        data.setData(lineDatas);
//
//        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
//
//        mChart1.setData(data);
//        mChart1.invalidate();
    }

//    @Override
//    public void onValueSelected(Entry e, Highlight h) {
//        Toast.makeText(this, "Value: "
//                + e.getY()
//                + ", index: "
//                + h.getX()
//                + ", DataSet index: "
//                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected() {
//
//    }
//
//    private static DataSet dataChart() {
//
//        LineData d = new LineData();
//        int[] data = new int[] { 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 9 };
//
//        ArrayList<Entry> entries = new ArrayList<Entry>();
//
//        for (int index = 0; index < 12; index++) {
//            entries.add(new Entry(index, data[index]));
//        }
//
//        LineDataSet set = new LineDataSet(entries, "Request Ots approved");
//        set.setColor(Color.GREEN);
//        set.setLineWidth(2.5f);
//        set.setCircleColor(Color.GREEN);
//        set.setCircleRadius(5f);
//        set.setFillColor(Color.GREEN);
//        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        set.setDrawValues(true);
//        set.setValueTextSize(10f);
//        set.setValueTextColor(Color.GREEN);
//
//        set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        d.addDataSet(set);
//
//        return set;
//    }
}
