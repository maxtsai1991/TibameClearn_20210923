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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.CleanplanAreaService;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.OrderConstants;

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
    private Button bt_CP01date2_11;
    private TextView tv_CP01onedate_11;
    private TextView tv_CP01manydate_11;
    private TextView tv_CP01manydate2_11;
    private TextView tv_CP01manydate3_11;

    //  2.指定性別
    private RadioGroup rg_CP01gender_11;
    private TextView tv_CP01gender_11;
    //  3.選擇家事者(地區)
    private TextView tv_CP01data_3;
    private RecyclerView rc_CP01_servicearea_11;
    private Button bt_areaservice_11;
    private TextView tv_CP01_servername_11;
    private List<CleanplanAreaService> searchCpAreaList = new ArrayList<>();
    private List<CleanplanAreaService> searchCpAreaServiceList = new ArrayList<>();

    //  4.資料帶到下一頁
    private OrderConstants orderconstants;


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
        // 7.實例化Bundle物件
        orderconstants = new OrderConstants();
        return inflater.inflate(R.layout.fragment_f__clean_plan_01_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
        handlenext(view);

        handlebtdate(view);
        handleRadioGroup(view);
//        handleBundle(view);
      handlebutton(view);
        handletoolbar(view);
//     3.家事者
//        handleRecyclerView(view);

    }

    private void findview(View view) {
        bt_cp01_next = view.findViewById(R.id.bt_CP01next_11);

        bt_CP01date_11 = view.findViewById(R.id.bt_CP01date_11);
        bt_CP01date2_11 = view.findViewById(R.id.bt_CP01date2_11);

        tv_CP01onedate_11 = view.findViewById(R.id.tv_CP01onedate_11);
        tv_CP01manydate_11 = view.findViewById(R.id.tv_CP01manydate_11);
        tv_CP01manydate2_11 = view.findViewById(R.id.tv_CP01manydate2_11);
        tv_CP01manydate3_11 = view.findViewById(R.id.tv_CP01manydate3_11);

        rg_CP01gender_11 = view.findViewById(R.id.rg_CP01gender_11);
        tv_CP01gender_11 = view.findViewById(R.id.tv_CP01gender_11);

        bt_areaservice_11 = view.findViewById(R.id.bt_areaservice_11);
        rc_CP01_servicearea_11 = view.findViewById(R.id.rc_CP01_servicearea_11);
        tv_CP01_servername_11 = view.findViewById(R.id.tv_CP01_servername_11);
        tv_CP01data_3 = view.findViewById(R.id.tv_CP01data_3);
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

        bt_CP01date2_11.setOnClickListener(v -> {

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
//        tv_CP01manydate_11.setText(text);

        // 7-1取得資料
        String etOnedate = tv_CP01onedate_11.getText().toString().trim();
        String etManeydate = tv_CP01manydate_11.getText().toString().trim();
        String etManeydate2 = tv_CP01manydate2_11.getText().toString().trim();
        String etManeydate3 = tv_CP01manydate3_11.getText().toString().trim();


        // 7-2資料放入Bundle物件
        orderconstants.setOnedate(etOnedate);
        orderconstants.setManydate(etManeydate);
        orderconstants.setManydate2(etManeydate2);
        orderconstants.setManydate3(etManeydate3);
    }




//  2.指定性別

    private void handleRadioGroup(View view) {
//       當 RadioGroup 中的某個選項被選中實觸發
        rg_CP01gender_11.setOnCheckedChangeListener((group, checkedId) -> {
            final RadioButton rb_CP01gender_11 = group.findViewById(checkedId);
//            textView待修改為bundle
//            textView.setText("(RadioButton) " + rb_CP01gender_11.getText());

            // 7-1取得資料
            String etGender = rb_CP01gender_11.getText().toString().trim();
            // 7-2資料放入orderconstants物件
            orderconstants.setGender(etGender);
        });
    }


//     3.家事者

private void handlebutton(View view) {
    bt_areaservice_11.setOnClickListener(v -> {
        handleRecyclerView(view);
    });
}

    private void handleRecyclerView(View view) {
        List<CleanplanAreaService> servicearealist = Arrays.asList(
                new CleanplanAreaService(R.drawable.girl3, "5", "黃永珠", "擅長項目 : 客廳清潔", "1200 元/ 次", "10 件 "),
                new CleanplanAreaService(R.drawable.boy3, "4", "陳明漢", "擅長項目 : 掃廚房", "1200 元/ 次", "5 件")

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
            holder.tv_CP01_toll_standard2_11.setText(itemNo.getToll_standard());
            holder.tv_CP01_finsh2_11.setText(itemNo.getFinsh());

//            點擊item與Checkbox連動
//            holder.cb_areaservice_11.setOnClickListener(v -> {
//
//            });

            holder.itemView.setOnClickListener(view -> {


                // 7-1取得資料
//                String etArea = rb_CP01gender_11.getText().toString().trim();
                String etServicename = holder.tv_CP01_servicename_11.getText().toString().trim();
                // 7-2資料放入orderconstants物件
                orderconstants.setCparea("中山區");
                orderconstants.setCpservice(etServicename);

                tv_CP01_servername_11.setText("已選擇家事者："+orderconstants.getCpservice());
                // 7-3測試
                Log.d("TAG_Cpservice=", orderconstants.getCpservice());
                Log.d("TAG_CpArea=", orderconstants.getCparea());
                Toast.makeText(activity, "已選擇"+etServicename, Toast.LENGTH_SHORT).show();
//        使用Bundle的原因是當時會將資料帶入到下一頁面呈現
//            Bundle bundle = new Bundle();
//            bundle.putString("name",name);
//            bundle.putString("password",password);
//

//                Navigation.findNavController(view).navigate(R.id.f_CleanPlan_02_Fragment);

            });
        }

        @Override
        public int getItemCount() {
            return servicearealist == null ? 0 : servicearealist.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv_CP0_1service_11;
            private RatingBar rat_CP01_ratingservice_11;
            private TextView tv_CP01_servicename_11, tv_CP01_good_project_11, tv_CP01_toll_standard2_11, tv_CP01_finsh2_11;
            private CheckBox cb_areaservice_11;
//
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                iv_CP0_1service_11 = itemView.findViewById(R.id.iv_CP0_1service_11);
                tv_CP01_servicename_11 = itemView.findViewById(R.id.tv_CP01_servicename_11);
                tv_CP01_good_project_11 = itemView.findViewById(R.id.tv_CP01_good_project_11);
                tv_CP01_toll_standard2_11 = itemView.findViewById(R.id.tv_CP01_toll_standard2_11);
                tv_CP01_finsh2_11 = itemView.findViewById(R.id.tv_CP01_finsh2_11);
                cb_areaservice_11 = itemView.findViewById(R.id.cb_areaservice_11);

            }
        }
    }




//  next button
    private void handlenext(View view) {
        bt_cp01_next.setOnClickListener(v -> {
                        //判斷不可為空值
            if (tv_CP01onedate_11.getText().toString().trim().isEmpty() ||
                    orderconstants.getGender() == null ||
                    tv_CP01_servername_11.getText().toString().trim().isEmpty()
            ) {
                tv_CP01onedate_11.setError("日期不可為空");
                tv_CP01gender_11.setError("請選擇性別");
                tv_CP01data_3.setError("請選擇家事者");

                return;
            }

// 4-2測試
//            Log.d("TAG_date=", orderconstants.getOnedate());
//            Log.d("TAG_date=", orderconstants.getManydate());
//            Log.d("TAG_gender=", orderconstants.getGender());
//            Log.d("TAG_CpArea=", orderconstants.getCparea());
//            Log.d("TAG_Cpservice=", orderconstants.getCpservice());


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