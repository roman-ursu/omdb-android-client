package com.perfectial.omdb.ui.search;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.perfectial.omdb.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rursu on 13.04.16.
 */
public class SearchFilterFragmentDialog extends DialogFragment {

    private static String TAG = SearchFilterFragmentDialog.class.getSimpleName();

    @Bind(R.id.sp_type)
    Spinner typeSpinner;

    @Bind(R.id.tv_year)
    TextView yearTextView;

    @Bind(R.id.tv_title)
    TextView titleTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.search_filter_fragment, container, false);
        ButterKnife.bind(this, viewGroup);


        getDialog().setTitle(R.string.search_filter);


        return viewGroup;
    }
}
