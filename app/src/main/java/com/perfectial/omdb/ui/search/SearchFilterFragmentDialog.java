package com.perfectial.omdb.ui.search;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.perfectial.omdb.R;
import com.perfectial.omdb.domain.bean.OpenDBMovie;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rursu on 13.04.16.
 */
public class SearchFilterFragmentDialog extends DialogFragment {

    public interface SearchFilterDialogListener {
        void onFilterSet(Map<String, String> options);
    }

    private static String TAG = SearchFilterFragmentDialog.class.getSimpleName();
    private SearchFilterDialogListener dialogListener;

    @Bind(R.id.sp_type)
    Spinner typeSpinner;

    @Bind(R.id.et_year)
    EditText yearTextView;

    @Bind(R.id.et_title)
    EditText titleTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.search_filter_fragment, container, false);
        ButterKnife.bind(this, viewGroup);

        getDialog().setTitle(R.string.search_filter);

        return viewGroup;
    }

    @OnClick(R.id.bt_apply)
    public void onClick() {
        if (dialogListener != null) {
            String type = (String) typeSpinner.getSelectedItem();
            String title = titleTextView.getText().toString();
            String yearString = yearTextView.getText().toString();

            if (StringUtils.isNoneBlank(title)) {

                Map<String, String> options = new HashMap<>();
                options.put(OpenDBMovie.TYPE_FIELD, type);
                options.put(OpenDBMovie.TITLE_FIELD, title);

                if (StringUtils.isNotBlank(yearString) && StringUtils.isNumeric(yearString)) {
                    options.put(OpenDBMovie.YEAR_FIELD, yearString);
                }

                dialogListener.onFilterSet(options);
            }
        }
    }

    public void setDialogListener(SearchFilterDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}
