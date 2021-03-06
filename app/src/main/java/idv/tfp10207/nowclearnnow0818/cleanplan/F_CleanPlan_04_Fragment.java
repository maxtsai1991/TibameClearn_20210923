package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.sql.Date;
import java.text.SimpleDateFormat;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.OrderConstants;

//  0.訂單資樂bundle or 從新撈資料
//  1.訂單詳情
//  2.前往預約頁面

public class F_CleanPlan_04_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CleanPlan_04_Fragment";
    private Activity activity;

    //  0.訂單資樂bundle or 從新撈資料

    //  1.訂單詳情
    private TextView tv_CP04_order_number2_11;

    private  TextView tv_CP04_orderscale1_11;
    private  TextView tv_CP04_orderscale2_11;
    private  TextView tv_CP04_orderscale3_11;
    private  TextView tv_CP04_orderscale4_11;
    private  TextView tv_CP04_orderscale5_11;

    private TextView tv_CP04_orderpay1_11;
    private TextView tv_CP04_orderpay2_11;
    private TextView tv_CP04_orderpay3_11;
    private TextView tv_CP04_orderpay4_11;

    private TextView tv_CP04_orderpayway1_11;
    private TextView tv_CP04_orderpayway2_11;

    private TextView tv_CP04_orderclient1_11;
    private TextView tv_CP04_orderclient2_11;
    private TextView tv_CP04_orderclient3_11;
    private TextView tv_CP04_orderclient4_11;

    //  7.資料放入OrderConstants帶到下一頁
    private OrderConstants orderconstants;

    //next button
    private Button bt_cp04_next;
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
        return inflater.inflate(R.layout.fragment_f__clean_plan_04_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
        handlenext(view);
        handletoolbar(view);

//        handlebutton(view);
        handleOrder(view);
    }
    //  0.訂單資樂bundle or 從新撈資料
    private void findview(View view) {
        tv_CP04_order_number2_11 = view.findViewById(R.id.tv_CP04_order_number2_11);
        bt_cp04_next = view.findViewById(R.id.bt_cp04_newt);

        tv_CP04_orderscale1_11 = view.findViewById(R.id.tv_CP04_orderscale1_11);
        tv_CP04_orderscale2_11 = view.findViewById(R.id.tv_CP04_orderscale2_11);
        tv_CP04_orderscale3_11 = view.findViewById(R.id.tv_CP04_orderscale3_11);
        tv_CP04_orderscale4_11 = view.findViewById(R.id.tv_CP04_orderscale4_11);
        tv_CP04_orderscale5_11 = view.findViewById(R.id.tv_CP04_orderscale5_11);

        tv_CP04_orderpay1_11 = view.findViewById(R.id.tv_CP04_orderpay1_11);
        tv_CP04_orderpay2_11 = view.findViewById(R.id.tv_CP04_orderpay2_11);
        tv_CP04_orderpay3_11 = view.findViewById(R.id.tv_CP04_orderpay3_11);
        tv_CP04_orderpay4_11 = view.findViewById(R.id.tv_CP04_orderpay4_11);

        tv_CP04_orderpayway1_11 = view.findViewById(R.id.tv_CP04_orderpayway1_11);
        tv_CP04_orderpayway2_11 = view.findViewById(R.id.tv_CP04_orderpayway2_11);

        tv_CP04_orderclient1_11 = view.findViewById(R.id.tv_CP04_orderclient1_11);
        tv_CP04_orderclient2_11 = view.findViewById(R.id.tv_CP04_orderclient2_11);
        tv_CP04_orderclient3_11 = view.findViewById(R.id.tv_CP04_orderclient3_11);
        tv_CP04_orderclient4_11 = view.findViewById(R.id.tv_CP04_orderclient4_11);
    }


//顯示訂單
    private void handleOrder(View view) {

//      顯示訂單編號

        tv_CP04_order_number2_11.setText(orderconstants.getCpordernumber());

        //媒合詳情
        tv_CP04_orderscale1_11.setText("預估清潔規模："+orderconstants.getPeoplenumber()+"人");
        tv_CP04_orderscale2_11.setText("預估清潔規模："+orderconstants.getPing()+"坪");
        tv_CP04_orderscale3_11.setText("清潔時間："+orderconstants.getTime());
        tv_CP04_orderscale4_11.setText("備註："+orderconstants.getRemark());
        tv_CP04_orderscale5_11.setText("家事者："+orderconstants.getCpservice());
        // 付款人 帶入會員資料
        tv_CP04_orderpay1_11.setText("付款人姓名："+orderconstants.getPayname());
        tv_CP04_orderpay2_11.setText("付款人手機："+orderconstants.getPayphone());
        tv_CP04_orderpay3_11.setText("付款人信箱："+orderconstants.getPayemail());
        tv_CP04_orderpay4_11.setText("付款人地址："+orderconstants.getPayaddress());
        //付款資訊 帶入會員資料
        tv_CP04_orderpayway1_11.setText("繳費方式："+orderconstants.getCppay());
        tv_CP04_orderpayway2_11.setText("繳費金額："+ orderconstants.getCppaymoney()+"元");
        //寫死 服務對象 帶入會員資料
        tv_CP04_orderclient1_11.setText("服務對象姓名："+orderconstants.getServicename());
        tv_CP04_orderclient2_11.setText("服務對象手機："+orderconstants.getServicephone());
        tv_CP04_orderclient3_11.setText("服務對象信箱："+orderconstants.getServiceemail());
        tv_CP04_orderclient4_11.setText("服務對象地址："+orderconstants.getServiceaddress());
    }

    //  next button
    private void handlenext(View view) {
        bt_cp04_next.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.reserve_01_Fragment);

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

