package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.CleanplanAreaService;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.CleanplanReserve;

//1.table連動
//2.RecyclerView 資料更新
//3.預約buttom 連動 訂單狀態
//4.推播 button

public class Reserve_01_Fragment extends Fragment {
    private static final String TAG = "TAG_reserve_01_Fragment";
    private Activity activity;

    //1.table連動
    private RadioGroup rg_reserve_00_11;
    private RadioButton rb_reserve_01_11;
    private RadioButton rb_reserve_02_11;
    private RadioButton rb_reserve_03_11;
    private Resources resources;

    //2.RecyclerView 資料更新
    private RecyclerView rcv_reserve1_11;

    private TextView tv_reserve_orderdetails_11;

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
        return inflater.inflate(R.layout.fragment_reserve_01_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
        handletoolbar(view);
//1.table連動
        handlerChangeRadiocolor(view);
        resources = getResources();
//2.RecyclerView 資料更新
        handleRecyclerView(view);
        handleOrderInfo(view);
    }

    //訂單詳情
    private void handleOrderInfo(View view) {
//        tv_reserve_orderdetails_11.setOnClickListener(v -> {
//            Navigation.findNavController(v).popBackStack(R.id.f_CleanPlan_05_Fragment, false);
//
//        });
    }

    private void findview(View view) {
        rg_reserve_00_11 = view.findViewById(R.id.rg_reserve_00_11);
        rb_reserve_01_11 = view.findViewById(R.id.rb_reserve_01_11);
        rb_reserve_02_11 = view.findViewById(R.id.rb_reserve_02_11);
        rb_reserve_03_11 = view.findViewById(R.id.rb_reserve_03_11);

        rcv_reserve1_11 = view.findViewById(R.id.rcv_reserve1_11);

//        tv_reserve_orderdetails_11 = view.findViewById(R.id.tv_reserve_orderdetails_11);
    }


    //1.table狀態改變
    private void handlerChangeRadiocolor(View view) {
        rb_reserve_01_11.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                rb_reserve_01_11.setBackgroundColor(resources.getColor(R.color.clearn));
                rb_reserve_01_11.setTextColor(Color.WHITE);
                rb_reserve_02_11.setBackgroundColor(resources.getColor(R.color.lightclearn));
                rb_reserve_02_11.setTextColor(Color.BLACK);
                rb_reserve_03_11.setBackgroundColor(resources.getColor(R.color.lightclearn));
                rb_reserve_03_11.setTextColor(Color.BLACK);
            }
        });

        rb_reserve_02_11.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                rb_reserve_01_11.setBackgroundColor(resources.getColor(R.color.lightclearn));
                rb_reserve_01_11.setTextColor(Color.BLACK);
                rb_reserve_02_11.setBackgroundColor(resources.getColor(R.color.clearn));
                rb_reserve_02_11.setTextColor(Color.WHITE);
                rb_reserve_03_11.setBackgroundColor(resources.getColor(R.color.lightclearn));
                rb_reserve_03_11.setTextColor(Color.BLACK);
            }
        });

        rb_reserve_03_11.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                rb_reserve_01_11.setBackgroundColor(resources.getColor(R.color.lightclearn));
                rb_reserve_01_11.setTextColor(Color.BLACK);
                rb_reserve_02_11.setBackgroundColor(resources.getColor(R.color.lightclearn));
                rb_reserve_02_11.setTextColor(Color.BLACK);
                rb_reserve_03_11.setBackgroundColor(resources.getColor(R.color.clearn));
                rb_reserve_03_11.setTextColor(Color.WHITE);
            }
        });
    }


    //2.RecyclerView 資料更新
    private void handleRecyclerView(View view) {
        List<CleanplanReserve> reservelist = Arrays.asList(
                new CleanplanReserve(R.drawable.photo5, "2021.9.23", "9:00~12:00", "黃永珠")


        );
        rcv_reserve1_11.setAdapter(new MyAdapter(activity, reservelist));

        rcv_reserve1_11.setLayoutManager(new LinearLayoutManager(activity));
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private Context context;
        private List<CleanplanReserve> reservelist;


        public MyAdapter(Context context, List<CleanplanReserve> list) {
            this.context = context;
            this.reservelist = list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.rc_itemcard_reserve, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            CleanplanReserve itemNo = reservelist.get(position);
            holder.iv_reserve_image_11.setImageResource(itemNo.getIv_reserve_image_11());
            holder.tv_reserve_date_11.setText(itemNo.getTv_reserve_date_11());
            holder.tv_reserve_time_11.setText(itemNo.getTv_reserve_time_11());
            holder.tv_reserve_name_11.setText(itemNo.getTv_reserve_name_11());


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
            return reservelist == null ? 0 : reservelist.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv_reserve_image_11;
            private TextView tv_reserve_date_11, tv_reserve_time_11, tv_reserve_name_11, tv_reserve_orderdetails_11;
            private Button bt_reserve_buttom1, bt_reserve_buttom2;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                iv_reserve_image_11 = itemView.findViewById(R.id.iv_reserve_image_11);
                tv_reserve_date_11 = itemView.findViewById(R.id.tv_reserve_date_11);
                tv_reserve_time_11 = itemView.findViewById(R.id.tv_reserve_time_11);
                tv_reserve_name_11 = itemView.findViewById(R.id.tv_reserve_name_11);

                tv_reserve_orderdetails_11 = itemView.findViewById(R.id.tv_reserve_orderdetails_11);
                bt_reserve_buttom1 = itemView.findViewById(R.id.bt_reserve_buttom1);
                bt_reserve_buttom1.setText("取消預約");
                bt_reserve_buttom2 = itemView.findViewById(R.id.bt_reserve_buttom2);

                // 點擊項目中的訂單詳情時
                tv_reserve_orderdetails_11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //                        用連線的方式到指定頁面
//                        NavController navController = Navigation.findNavController(view);
//                        navController.navigate(R.id.action_reserve_01_Fragment_to_homePageFragment072);

//                        直接指定 方法1
//                        Navigation.findNavController(view).popBackStack(R.id.f_CleanPlan_05_Fragment, false);

//                        直接指定 方法2
                        Navigation.findNavController(view).
                                navigate(R.id.f_CleanPlan_05_Fragment);                    }
                });

//              點擊項目中的"取消/接受"時
                bt_reserve_buttom1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
//              點擊項目中的"聯絡"時
                bt_reserve_buttom2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).
                                navigate(R.id.messageFragment07);
                    }
                });
            }
        }
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
            Navigation.findNavController(view).popBackStack(R.id.reserve_01_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

        //    標題
        tvprojectname.setText("訂單狀態");

    }
}