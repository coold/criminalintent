package ru.coold.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

/**
 * Created by rzk on 13.02.2015.
 */
public class ChooseDialogFragment extends DialogFragment {
    private Date mDate;
    public static final String CHOOSER_DATE = "ru.coold.criminalintent.chooser_date";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(CHOOSER_DATE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_chooser, null);

        Button mDateButton = (Button)v.findViewById(R.id.chooser_date_button);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mDate);
                dialog.setTargetFragment(getTargetFragment(), CrimeFragment.REQUEST_DATE);
                dialog.show(fm, CrimeFragment.DIALOG_DATE);
                ChooseDialogFragment.this.dismiss();
            }
        });

        Button mTimeButton = (Button)v.findViewById(R.id.chooser_time_button);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mDate);
                dialog.setTargetFragment(getTargetFragment(), TimePickerFragment.REQUEST_TIME);
                dialog.show(fm, CrimeFragment.DIALOG_DATE);
                ChooseDialogFragment.this.dismiss();
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.time_or_date_chooser).create();
    }

    public static ChooseDialogFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(CHOOSER_DATE, date);
        ChooseDialogFragment fragment = new ChooseDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
