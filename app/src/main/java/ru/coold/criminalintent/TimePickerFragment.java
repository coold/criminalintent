package ru.coold.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rzk on 13.02.2015.
 */
public class TimePickerFragment extends DialogFragment {
    private Date mDate;
    public static final int REQUEST_TIME = 1;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(DatePickerFragment.EXTRA_DATE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);

        TimePicker mTimePicker = (TimePicker)v.findViewById(R.id.dialog_time_timePicker);
        mTimePicker.setIs24HourView(true);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);
        Log.d("ololo", ""+mTimePicker.getCurrentHour());
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute1) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(mDate);
                calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar1.set(Calendar.MINUTE, minute1);
                mDate = calendar1.getTime();
                getArguments().putSerializable(DatePickerFragment.EXTRA_DATE, mDate);
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.time_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResult(Activity.RESULT_OK);
            }
        }).create();
    }

    private void sendResult(int resultCode){
        if(getTargetFragment()==null){
            return;
        }

        Intent i = new Intent();
        i.putExtra(DatePickerFragment.EXTRA_DATE,mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(DatePickerFragment.EXTRA_DATE, date);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
