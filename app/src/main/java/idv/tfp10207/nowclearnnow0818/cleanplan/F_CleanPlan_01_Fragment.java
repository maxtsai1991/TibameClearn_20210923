package idv.tfp10207.nowclearnnow0818.cleanplan;

//

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.CleanplanAreaService;
import idv.tfp10207.nowclearnnow0818.market.MerchInfo;

import static android.provider.Telephony.Carriers.PASSWORD;
import static idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.cleanplan_orderconstants.GENDER;
import static idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.cleanplan_orderconstants.ONEDATE;

//  1.選擇日期
//  2.指定性別
//  3.選擇家事者(地區)
//  4.資料放入Bundle帶到下一頁
public class F_CleanPlan_01_Fragment extends Fragment
        implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "TAG_F_CleanPlan_01_Fragment";
    private Activity activity;

    //  1.選擇日期
    private Button bt_CP01date_11;
    private TextView tv_CP01onedate_11;
    //  2.指定性別
    private RadioGroup rg_CP01gender_11;
    //  3.選擇家事者(地區)
    private RecyclerView rc_CP01_servicearea_11;
    private List<CleanplanAreaService> searchCpAreaList = new ArrayList<>();
    private List<CleanplanAreaService> searchCpAreaServiceList = new ArrayList<>();

    //  4.資料帶到下一頁
    private Bundle bundle01;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
        handlenext(view);
// 實例化Bundle物件
        bundle01 = new Bundle();
//      handlebutton(view);
        handlebtdate(view);
        handleRadioGroup(view);
//        handleBundle(view);
        handletoolbar(view);
//     3.家事者
        handleRecyclerView(view);

    }

    private void findview(View view) {
        bt_cp01_next = view.findViewById(R.id.bt_CP01next_11);

        bt_CP01date_11 = view.findViewById(R.id.bt_CP01date_11);
        tv_CP01onedate_11 = view.findViewById(R.id.tv_CP01onedate_11);

        rg_CP01gender_11 = view.findViewById(R.id.rg_CP01gender_11);

        rc_CP01_servicearea_11 = view.findViewById(R.id.rc_CP01_servicearea_11);
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

            // 1. 設定可選取日期區間
            // 1.1 取得DatePicker物件
            DatePicker datePicker = datePickerDialog.getDatePicker();
            // 1.2 設定可選取的最小日期
            datePicker.setMinDate(calendar.getTimeInMillis());
            // 1.3 設定可選取的最大日期
            calendar.add(Calendar.MONTH, 2);
            datePicker.setMaxDate(calendar.getTimeInMillis());

            // 1.4. 顯示對話框
            datePickerDialog.show();
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final String text = "" + year + "/" + (month + 1) + "/" + dayOfMonth;
        tv_CP01onedate_11.setText(text);
    }


//  2.指定性別

    private void handleRadioGroup(View view) {
        rg_CP01gender_11.setOnCheckedChangeListener((group, checkedId) -> {
            final RadioButton rb_CP01gender_11 = group.findViewById(checkedId);
//            textView待修改為bundle
//            textView.setText("(RadioButton) " + rb_CP01gender_11.getText());

            // 7-1取得資料
            final String gender = String.valueOf(rb_CP01gender_11.getText());
            // 7-2資料放入Bundle物件
            bundle01.putString(GENDER, gender);
        });
    }


//     3.家事者
    private void handleRecyclerView(View view) {
        List<CleanplanAreaService> servicearealist = Arrays.asList(
                new CleanplanAreaService(R.drawable.photo5, "5", "黃永珠", "客廳清潔", "1200", "已完成件數:4件 "),
                new CleanplanAreaService(R.drawable.photo6, "4", "陳明漢", "掃廚房", "1200", "已完成件數:2件 ")

        );
        rc_CP01_servicearea_11.setAdapter(new MyAdapter(activity, servicearealist));

        rc_CP01_servicearea_11.setLayoutManager(new LinearLayoutManager(activity));
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private Context context;
        private List<CleanplanAreaService> servicearealist;


        public MyAdapter(Context context, List<CleanplanAreaService> list) {
            this.context = context;
            this.servicearealist = list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.rc_itemcard_cleanplan01_area, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            CleanplanAreaService itemNo = servicearealist.get(position);
            holder.iv_CP0_1service_11.setImageResource(itemNo.getCp_iv_serviceID());
            holder.tv_CP01_servicename_11.setText(itemNo.getCp_serviceName());
            holder.tv_CP01_good_project_11.setText(itemNo.getCp_good_project());
            holder.tv_CP01_toll_standard_11.setText(itemNo.getToll_standard());
            holder.tv_CP01_finsh_11.setText(itemNo.getFinsh());

            holder.itemView.setOnClickListener(view -> {


//        使用Bundle的原因是當時會將資料帶入到下一頁面呈現
//            Bundle bundle = new Bundle();
//            bundle.putString("name",name);
//            bundle.putString("password",password);
//


//                NavController navController = Navigation.findNavController(view);
//                navController.navigate(R.id.action_a_Ch3_22Fragment2_to_ch3_22Fragment);
            });
        }

        @Override
        public int getItemCount() {
            return servicearealist == null ? 0 : servicearealist.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv_CP0_1service_11;
            private RatingBar rat_CP01_ratingservice_11;
            private TextView tv_CP01_servicename_11, tv_CP01_good_project_11, tv_CP01_toll_standard_11, tv_CP01_finsh_11;
//
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                iv_CP0_1service_11 = itemView.findViewById(R.id.iv_CP0_1service_11);
                tv_CP01_servicename_11 = itemView.findViewById(R.id.tv_CP01_servicename_11);
                tv_CP01_good_project_11 = itemView.findViewById(R.id.tv_CP01_good_project_11);
                tv_CP01_toll_standard_11 = itemView.findViewById(R.id.tv_CP01_toll_standard_11);
                tv_CP01_finsh_11 = itemView.findViewById(R.id.tv_CP01_finsh_11);

            }
        }
    }




//  next button
    private void handlenext(View view) {
        bt_cp01_next.setOnClickListener(v -> {

            // 4-1取得日期資料
            final String onedate = String.valueOf(tv_CP01onedate_11.getText());
            // 4-2資料放入Bundle物件
            bundle01.putString(ONEDATE, onedate);
            // 4-2測試
            Log.d("onedate=", ONEDATE);
            Log.d("gender=", GENDER);

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


}