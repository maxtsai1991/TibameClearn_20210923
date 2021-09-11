package idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.FirebaseFirestore;

import idv.tfp10207.nowclearnnow0818.R;

public class F_CleanPlan_06_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CP06_Fragment";
    private Activity activity;

    private FirebaseFirestore db;
    private Cpreserveorder cpreserveorder;
    //  1.訂單詳情
    private TextView tv_CP06_reserveorder_11;
    private TextView tv_CP06_reserveorder1_11;
    private TextView tv_CP06_reserveorder2_11;

    private Button bt_cp06_orderchart_11;
    private Button bt_cp06_setorderstate_canel_11;
    private Button bt_cp06_setorderstate_accept_11;

    private  TextView tv_CP06_orderscale1_11;
    private  TextView tv_CP06_orderscale2_11;
    private  TextView tv_CP06_orderscale3_11;
    private  TextView tv_CP06_orderscale4_11;
    private  TextView tv_CP06_orderscale5_11;

    private TextView tv_CP06_orderpay1_11;
    private TextView tv_CP06_orderpay2_11;
    private TextView tv_CP06_orderpay3_11;
    private TextView tv_CP06_orderpay4_11;

    private TextView tv_CP06_orderpayway1_11;
    private TextView tv_CP06_orderpayway2_11;

    private TextView tv_CP06_orderclient1_11;
    private TextView tv_CP06_orderclient2_11;
    private TextView tv_CP06_orderclient3_11;
    private TextView tv_CP06_orderclient4_11;


    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_f__clean_plan_06_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
//        handlebutton();
        handleItemViews();
//        handleOrder(view);
        handletoolbar(view);

    }

    private void findview(View view) {
        bt_cp06_orderchart_11 = view.findViewById(R.id.bt_cp06_orderchart_11);
        bt_cp06_setorderstate_canel_11 = view.findViewById(R.id.bt_cp06_setorderstate_canel_11);
        bt_cp06_setorderstate_accept_11 = view.findViewById(R.id.bt_cp06_setorderstate_accept_11);

        tv_CP06_reserveorder_11 = view.findViewById(R.id.tv_CP06_reserveorder_11);
        tv_CP06_reserveorder1_11 = view.findViewById(R.id.tv_CP06_reserveorder1_11);
        tv_CP06_reserveorder2_11 = view.findViewById(R.id.tv_CP06_reserveorder2_11);

        tv_CP06_orderscale1_11 = view.findViewById(R.id.tv_CP06_orderscale1_11);
        tv_CP06_orderscale2_11 = view.findViewById(R.id.tv_CP06_orderscale2_11);
        tv_CP06_orderscale3_11 = view.findViewById(R.id.tv_CP06_orderscale3_11);
        tv_CP06_orderscale4_11 = view.findViewById(R.id.tv_CP06_orderscale4_11);
        tv_CP06_orderscale5_11 = view.findViewById(R.id.tv_CP06_orderscale5_11);

        tv_CP06_orderpay1_11 = view.findViewById(R.id.tv_CP06_orderpay1_11);
        tv_CP06_orderpay2_11 = view.findViewById(R.id.tv_CP06_orderpay2_11);


        tv_CP06_orderpayway1_11 = view.findViewById(R.id.tv_CP06_orderpayway1_11);
        tv_CP06_orderpayway2_11 = view.findViewById(R.id.tv_CP06_orderpayway2_11);

        tv_CP06_orderclient1_11 = view.findViewById(R.id.tv_CP06_orderclient1_11);
        tv_CP06_orderclient2_11 = view.findViewById(R.id.tv_CP06_orderclient2_11);
        tv_CP06_orderclient4_11 = view.findViewById(R.id.tv_CP06_orderclient4_11);
    }


    private void handleItemViews() {
        // 顯示剛剛點選的 itemview 資料
        if (getArguments() != null) {
            cpreserveorder = (Cpreserveorder) getArguments().getSerializable("cpreserveorders");
            if (cpreserveorder != null) {

                String Cpordernumber = cpreserveorder.getCpordernumber();
                tv_CP06_reserveorder2_11.setText(Cpordernumber);

                String orderPeople ="清潔規模："+ cpreserveorder.getPeoplenumber()+"人";
                tv_CP06_orderscale1_11.setText(orderPeople);

                String orderPing ="清潔規模："+ cpreserveorder.getPing()+"坪";
                tv_CP06_orderscale2_11.setText(orderPing);

                String orderTime ="時間："+ cpreserveorder.getTime();
                tv_CP06_orderscale3_11.setText(orderTime);

                String orderRemark ="備註："+  cpreserveorder.getRemark();
                tv_CP06_orderscale4_11.setText(orderRemark);

                String orderCpservice = "家事者："+  cpreserveorder.getCpservice();
                tv_CP06_orderscale5_11.setText(orderCpservice);

                String orderPayname = "付款者姓名："+  cpreserveorder.getPayname();
                tv_CP06_orderpay1_11.setText(orderPayname);

                String orderPayphone = "付款者手機："+  cpreserveorder.getPayphone();
                tv_CP06_orderpay2_11.setText(orderPayphone);

                String orderPayway = "付款方式："+  cpreserveorder.getCppay();
                tv_CP06_orderpayway1_11.setText(orderPayway);

//               資料庫沒抓到金額，先寫死
                String orderPaymoney = "付款金額："+"7450"+"元";
//                String orderPaymoney = "付款金額："+  cpreserveorder.getCppaymoney()+"元";
                tv_CP06_orderpayway2_11.setText(orderPaymoney);

                String orderServicename = "服務對象姓名："+  cpreserveorder.getServicename();
                tv_CP06_orderclient1_11.setText(orderServicename);

                String orderServicephone = "服務對象手機："+  cpreserveorder.getServicephone();
                tv_CP06_orderclient2_11.setText(orderServicephone);

                String orderServiceaddress = "服務對象地址："+  cpreserveorder.getServiceaddress();
                tv_CP06_orderclient4_11.setText(orderServiceaddress);

            }
        }

        // 監聽選擇的訂單狀態
//        rgState.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId == R.id.rb_ready) {
//                order.setTast1("A");
//            } else if (checkedId == R.id.rb_received) {
//                order.setTast1("B");
//            } else  {
//                order.setTast1("C");
//            }
//        });

//聯絡
        bt_cp06_orderchart_11.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.messageFragment11);
        });

//確認
        bt_cp06_setorderstate_accept_11.setOnClickListener(v -> {
//            String amountStr = etAmount.getText().toString().trim();
//            int amount = Integer.parseInt(amountStr);
//            order.setOrderAmount(amount);
            cpreserveorder.setCporderstate(cpreserveorder.getCporderstate()+1);
            Log.d(TAG,"訂單狀態"+cpreserveorder.getCporderstate());

            modify(cpreserveorder);

            Navigation.findNavController(v).navigate(R.id.reserve_01_Fragment);



        });

//取消
        bt_cp06_setorderstate_canel_11.setOnClickListener(v -> {
            Log.d(TAG,"訂單編號:"+cpreserveorder.getCpordernumber());

            new AlertDialog.Builder(getActivity())                    // 實例化AlertDialog.Builder物件
                    .setTitle("取消預約")                                 // 設定標題文字
//                    .setIcon(R.drawable.ptt01)                           // 設定標題圖示
                    .setMessage("確認要取消本次預約？")             // 設定訊息文字

//                    有點不懂這段後面的參數在fragment該怎麼設置            // 設定確定按鈕-顯示文字及監聽器
                    .setPositiveButton("確定取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            cpreserveorder.setCporderstate(cpreserveorder.getCporderstate()+0);
                            Log.d(TAG,"訂單狀態:"+cpreserveorder.getCporderstate());
                            modify(cpreserveorder);
                            dialog.dismiss();
                        }
                    })
                    // 設定否定按鈕-顯示文字及監聽器
//                    .setNegativeButton("No", this)

                    // 設定不決定按鈕-顯示文字及監聽器
                    .setNeutralButton("返回", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(true)                               // 設定是否可點擊對話框以外之處離開對話框
                    .show();
        });



    }


    private void modify(Cpreserveorder cpreserveorder) {
        // 修改指定 ID 的文件
        db.collection("cleanplanorder").document(cpreserveorder.getCpordernumber()).set(cpreserveorder)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String message = "修改成功 with ID: " + cpreserveorder.getCpordernumber();
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        // 修改完畢跳轉至訂單列表
//                        NavController navController = Navigation.findNavController(tv_modify_tast1);
//                        navController.navigate(R.id.indexFragment);
                    } else {
                        String message = task.getException() == null ?
                                "修改失敗" : task.getException().getMessage();
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

//顯示訂單
    private void handleOrder(View view) {
        //寫死 媒合詳情
        tv_CP06_orderscale1_11.setText("預估清潔規模："+"1"+"人");
        tv_CP06_orderscale2_11.setText("預估清潔規模："+"25"+"坪");
        tv_CP06_orderscale3_11.setText("清潔時間："+"早上");
        tv_CP06_orderscale4_11.setText("備註："+"家中有養狗，清理時請注意，謝謝!");
        tv_CP06_orderscale5_11.setText("家事者："+"黃永珠");
        //寫死 付款人 帶入會員資料
        tv_CP06_orderpay1_11.setText("付款人姓名："+"王大明");
        tv_CP06_orderpay2_11.setText("付款人手機："+"0922222222");
        tv_CP06_orderpay3_11.setText("付款人信箱："+"aaa@gmail.com");
        tv_CP06_orderpay4_11.setText("付款人地址："+"台北市中山區吉林路");
        //寫死 付款資訊 帶入會員資料
        tv_CP06_orderpayway1_11.setText("繳費方式："+"Googlepay");
        tv_CP06_orderpayway2_11.setText("繳費金額："+"7450元");
        //寫死 服務對象 帶入會員資料
        tv_CP06_orderclient1_11.setText("服務對象姓名："+"王大明");
        tv_CP06_orderclient2_11.setText("服務對象手機："+"0922222222");
        tv_CP06_orderclient3_11.setText("服務對象信箱："+"aaa@gmail.com");
        tv_CP06_orderclient4_11.setText("服務對象地址："+"台北市中山區吉林路");
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
            Navigation.findNavController(view).popBackStack(R.id.f_CleanPlan_06_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

//    標題
        tvprojectname.setText("預約詳情");

    }



}