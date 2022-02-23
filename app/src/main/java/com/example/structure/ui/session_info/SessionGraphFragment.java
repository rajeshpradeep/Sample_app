package com.example.structure.ui.session_info;


import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.structure.R;
import com.example.structure.data.models.DetailedSessionResponseModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SessionGraphFragment extends BottomSheetDialogFragment implements OnChartValueSelectedListener {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;

    @BindView(R.id.session_graph_chart)
    LineChart session_graph_chart;

    DetailedSessionResponseModel detailedSessionResponseModel;
    ArrayList<DetailedSessionResponseModel.DetailedSessionBean> detailedSessionBeans;

    public SessionGraphFragment() {
        // Required empty public constructor
    }

    public SessionGraphFragment(DetailedSessionResponseModel detailedSessionResponseModel) {
        this.detailedSessionResponseModel = detailedSessionResponseModel;
        detailedSessionBeans = new ArrayList<>();
        detailedSessionBeans = (ArrayList<DetailedSessionResponseModel.DetailedSessionBean>) detailedSessionResponseModel.getDetailedSession();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session_graph, container, false);
        unbinder = ButterKnife.bind(this, view);

        {   // // Chart Style // //
//            chart = findViewById(R.id.chart1);

            // background color
            session_graph_chart.setBackgroundColor(Color.WHITE);

            // disable description text
            session_graph_chart.getDescription().setEnabled(false);

            // enable touch gestures
            session_graph_chart.setTouchEnabled(true);

            // set listeners
            session_graph_chart.setOnChartValueSelectedListener(this);
            session_graph_chart.setDrawGridBackground(false);

            // create marker to display box when values are selected
//            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

            // Set the marker to the chart
//            mv.setChartView(session_graph_chart);
//            session_graph_chart.setMarker(mv);

            // enable scaling and dragging
            session_graph_chart.setDragEnabled(true);
            session_graph_chart.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            session_graph_chart.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = session_graph_chart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = session_graph_chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            session_graph_chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(-50f);
        }
        Typeface tfRegular;
        tfRegular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NunitoSans-Regular.ttf");

        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
            llXAxis.setTypeface(tfRegular);

            LimitLine ll1 = new LimitLine(150f, "Upper Limit");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            ll1.setTypeface(tfRegular);

            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);
            ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(ll2);
            //xAxis.addLimitLine(llXAxis);
        }

        setData(45, 120);

        // draw points over time
        session_graph_chart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = session_graph_chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);

        return view;
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        /*for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) - 30;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }*/

        LineDataSet set1;

        if (session_graph_chart.getData() != null &&
                session_graph_chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) session_graph_chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            session_graph_chart.getData().notifyDataChanged();
            session_graph_chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return session_graph_chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = getResources().getDrawable(R.drawable.graph_gradient);
//                Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.bg_splash);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            session_graph_chart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOW HIGH", "low: " + session_graph_chart.getLowestVisibleX() + ", high: " + session_graph_chart.getHighestVisibleX());
        Log.i("MIN MAX", "xMin: " + session_graph_chart.getXChartMin() + ", xMax: " + session_graph_chart.getXChartMax()
                + ", yMin: " + session_graph_chart.getYChartMin() + ", yMax: " + session_graph_chart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
