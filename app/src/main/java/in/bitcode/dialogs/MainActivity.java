package in.bitcode.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAlertDialog, mBtnTimePickerDialog, mBtnDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        mBtnAlertDialog.setOnClickListener(this);

        View.OnClickListener listener = new BtnDateAndTimePickerClickListener();
        mBtnTimePickerDialog.setOnClickListener(listener);
        mBtnDatePickerDialog.setOnClickListener(listener);
    }

    class BtnDateAndTimePickerClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view == mBtnTimePickerDialog) {

                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(
                                MainActivity.this,
                                new TimeListener(),
                                14,
                                51,
                                false
                        );
                timePickerDialog.show();
            }

            if (view.getId() == R.id.btnDatePickerDialog) {
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(
                                MainActivity.this,
                                new DateListener(),
                                2000,
                                1,
                                1
                        );
                datePickerDialog.show();
            }

        }
    }

    private class DateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mBtnDatePickerDialog.setText( dayOfMonth + "-" + (month+1) + "-" + year);
        }
    }


    private class TimeListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker dialog, int hourOfDay, int minute) {
            mBtnTimePickerDialog.setText(hourOfDay + ":" + minute);
        }
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("BitCode");
        builder.setMessage("Are you sure, you want to learn?");
        builder.setIcon(R.mipmap.ic_launcher);

       /* builder.setPositiveButton("Yes", new YesListener());
        builder.setNegativeButton("No", new NoListener());
        builder.setNeutralButton("Cancel", new CancelListener());*/

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mt("Dialog dismissed!");
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mt("Dialog cancelled!");
            }
        });

        builder.setCancelable(false);

        DialogInterface.OnClickListener listener = new AlertButtonsListener();

        builder.setPositiveButton("Yes", listener);
        builder.setNegativeButton("No", listener);
        builder.setNeutralButton("Cancel", listener);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    class AlertButtonsListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                mt("You said Yes!");
            }
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                mt("You said No!");
            }
            if (which == DialogInterface.BUTTON_NEUTRAL) {
                mt("You Cancelled");
            }
        }
    }

    /*class YesListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You said yes!");
        }
    }

    class NoListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You said No!");
        }
    }

    class CancelListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You cancelled!");
        }
    }*/

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


    private void init() {
        setContentView(R.layout.activity_main);

        mBtnAlertDialog = findViewById(R.id.btnAlertDialog);
        mBtnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
        mBtnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
    }
}