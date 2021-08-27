package idv.tfp10207.nowclearnnow0818.cleanplan;

//
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Calendar;

import idv.tfp10207.nowclearnnow0818.R;
//  1.選擇日期
//  2.指定性別
//  3.選擇家事者(地區)
public class F_CleanPlan_01_Fragment extends Fragment
        implements DialogInterface.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener
{
    private static final String TAG = "TAG_F_CleanPlan_01_Fragment";
    private Activity activity;

//  1.選擇日期
    private Button bt_CP01date_11;
    private  TextView tv_CP01onedata_11;
//  2.指定性別
    private RadioGroup rg_CP01gender_11;
//  3.選擇家事者(地區)

    //next button
    private Button bt_cp01_next;
    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_f__clean_plan_01_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
        handlenext(view);


//      handlebutton(view);
        handlebtdate(view);
        handleRadioGroup(view);
        handletoolbar(view);
    }

    private void findview(View view) {
        bt_cp01_next = view.findViewById(R.id.bt_CP01next_11);

        bt_CP01date_11 = view.findViewById(R.id.bt_CP01date_11);
        tv_CP01onedata_11 = view.findViewById(R.id.tv_CP01onedata_11);

        rg_CP01gender_11 = view.findViewById(R.id.rg_CP01gender_11);

    }



//  1.選擇日期

    private void handlebtdate(View view) {
        bt_CP01date_11.setOnClickListener(v -> {
            // 1. 取得Calendar物件
            Calendar calendar = Calendar.getInstance();

            // 2. 實例化DatePickerDialog物件
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    activity,
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            // 3. 設定可選取日期區間
            // 3.1 取得DatePicker物件
            DatePicker datePicker = datePickerDialog.getDatePicker();
            // 3.2 設定可選取的最小日期
            datePicker.setMinDate(calendar.getTimeInMillis());
            // 3.3 設定可選取的最大日期
            calendar.add(Calendar.MONTH, 2);
            datePicker.setMaxDate(calendar.getTimeInMillis());

            // 4. 顯示對話框
            datePickerDialog.show();
        });
    }



    //  2.指定性別
    private void handleRadioGroup(View view) {
        rg_CP01gender_11.setOnCheckedChangeListener((group, checkedId) -> {
            final RadioButton rb_CP01gender_11 = group.findViewById(checkedId);
 //            textView待修改為bundle
//            textView.setText("(RadioButton) " + rb_CP01gender_11.getText());
        });
    }


//  next button
    private void handlenext(View view) {
        bt_cp01_next.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_02_Fragment);

        });
    }

//    客製Toolbar
    private void handletoolbar(View view) {
//    抓按鍵
        leftarrowicon = view.findViewById(R.id.iv_arrow_back_11);
        righthomeicon = view.findViewById(R.id.iv_home_11);
//    改標題
        tvprojectname = view.findViewById(R.id.tv_project_name_11);


//    按鍵(需更改導覽路線的ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.f_CleanPlan_01_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

//    標題
        tvprojectname.setText("清潔計畫");

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final String text = "" + year + "/" + (month + 1) + "/" + dayOfMonth;
        tv_CP01onedata_11.setText(text);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}