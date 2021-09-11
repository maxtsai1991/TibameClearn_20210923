package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.OrderConstants;
import idv.tfp10207.nowclearnnow0818.market.GooglePayMainActivity;

//  0.前面訂單的資料  >>>bundle
//  1.選擇付款方式 改googlepay
//  1-1.信用卡資訊   改googlepay
//  2.付款人資訊  與 同會員資料
//  3.彈跳視窗: 確認媒合>>>訂單送出 (本次媒合詳情)
//     googlepay

//  3-1.訂單資訊回傳給資料庫
//  3-2.推撥給家事者

public class F_CleanPlan_03_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CP03_Fragment";
    private Activity activity;

    private static final int RESULT_OK = -1;
    private static final int RESULT_FAIL = 0;
    private static final int RESULT_EXIT = 1;


    //  2.付款人資訊  與 同會員資料
    private CheckBox cb_CP03_paypereson_member_11;
    private EditText et_CP03_payperson_name_11;
    private EditText et_CP03_payperson_Phone_11;
    private EditText et_CP03_payperson_Email_11;
    private EditText et_CP03_payperson_Address_11;

//  3.彈跳視窗: 確認媒合>>>訂單送出


    private Button bt_CP03next_11;
    private String orderinfo;

    //  7.資料放入OrderConstants帶到下一頁
    private OrderConstants orderconstants;

    //  8.資料傳進資料庫
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    private boolean pictureTaken;
    private Uri cropImageUri; // 截圖的Uri


    ActivityResultLauncher<Intent> googlepayLauncher = registerForActivityResult( //0904//
            new ActivityResultContracts.StartActivityForResult(),
            this::googlePayResult); // callback，登入頁面完成之後，回原先頁面

    //next button
    private Button bt_cp03_next;
    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BUNDLE範例  以後有機會可修改
//        orderconstants = (OrderConstants)getArguments().getSerializable("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        // 7.實例化Bundle物件
        orderconstants = new OrderConstants();
        //取得Firebasestore物件
        db = FirebaseFirestore.getInstance();
        // 取得FirebaseStorage物件
        storage = FirebaseStorage.getInstance();
        return inflater.inflate(R.layout.fragment_f__clean_plan_03_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
        handletoolbar(view);

        handleCheckBoxes(view);
//        handlebutton(view);
        handleNextBtAlert(view);

    }


    private void findview(View view) {
        bt_cp03_next = view.findViewById(R.id.bt_CP03next_11);

        cb_CP03_paypereson_member_11 = view.findViewById(R.id.cb_CP03_paypereson_member_11);
        et_CP03_payperson_name_11 = view.findViewById(R.id.et_CP03_payperson_name_11);
        et_CP03_payperson_Phone_11 = view.findViewById(R.id.et_CP03_payperson_Phone_11);
        et_CP03_payperson_Email_11 = view.findViewById(R.id.et_CP03_payperson_Email_11);
        et_CP03_payperson_Address_11 = view.findViewById(R.id.et_CP03_payperson_Address_11);

    }


//  2.付款人資訊  與 同會員資料

    private void handleCheckBoxes(View view) {
        final CompoundButton.OnCheckedChangeListener listener = (checkBox, isChecked) -> {
            //  帶入會員資料
            et_CP03_payperson_name_11.setText("王大明");
            et_CP03_payperson_Phone_11.setText("0922222222");
            et_CP03_payperson_Email_11.setText("aaa@gmail.com");
            et_CP03_payperson_Address_11.setText("台北市中山區吉林路");

        };


        // 7-1取得資料
        String etPaypersonname = et_CP03_payperson_name_11.getText().toString().trim();
        String etPaypersonphone = et_CP03_payperson_Phone_11.getText().toString().trim();
        String etPaypersonemail = et_CP03_payperson_Email_11.getText().toString().trim();
        String etPaypersonaddress = et_CP03_payperson_Address_11.getText().toString().trim();


        // 7-2資料放入Bundle物件

        OrderConstants.payname = etPaypersonname;
        orderconstants.setPayphone(etPaypersonphone);
        orderconstants.setPayemail(etPaypersonemail);
        orderconstants.setPayaddress(etPaypersonaddress);

        // 4-2測試
//            Log.d("TAG_Paypersonname=", orderconstants.getPayname());
//            Log.d("TAG_Payphone=", orderconstants.getPayphone());
//            Log.d("TAG_Payemail=", orderconstants.getPayemail());
//            Log.d("TAG_Payaddress=", orderconstants.getPayaddress());


        cb_CP03_paypereson_member_11.setOnCheckedChangeListener(listener);


    }
//  3.nextbutton彈跳視窗 :(本次媒合詳情)

    private void handleNextBtAlert(View view) {
        bt_cp03_next.setOnClickListener(v -> {

            // 7-1取得資料
//            String etcppay = .getText().toString().trim();

            // 7-2資料放入Bundle物件
            handleCheckBoxes(view);
            orderconstants.setCppay("Googlepay");

            //      訂單編號
            long curDate = System.currentTimeMillis(); //获取当前时间
            String time_stamp = String.valueOf(curDate);
            SimpleDateFormat myFmt1 = new SimpleDateFormat("yy/MM/dd HH:mm");

            orderconstants.setCpordernumber("CP" + time_stamp);

            Log.d("TAG_Cpordernumber=", orderconstants.getCpordernumber());

            //判斷不可為空值
            if (et_CP03_payperson_name_11.getText().toString().trim().isEmpty() ||
                    et_CP03_payperson_Phone_11.getText().toString().trim().isEmpty() ||
                    et_CP03_payperson_Email_11.getText().toString().trim().isEmpty() ||
                    et_CP03_payperson_Address_11.getText().toString().trim().isEmpty()
            ) {
                et_CP03_payperson_name_11.setError("不可為空");
                et_CP03_payperson_Phone_11.setError("不可為空");
                et_CP03_payperson_Email_11.setError("不可為空");
                et_CP03_payperson_Address_11.setError("不可為空");

                return;
            }


            activity = getActivity();
            new AlertDialog.Builder(getActivity())                    // 實例化AlertDialog.Builder物件
                    .setTitle("確認媒合")                                 // 設定標題文字
                    //簡單的訂單明細與訂單付款金額
                    .setMessage("本次媒合詳情:" + "\n" + "預估清潔規模：" + orderconstants.getPeoplenumber() + "人"
                            + "\n" + "預估清潔規模：" + orderconstants.getPing() + "坪"
                            + "\n" + "清潔時間：" + orderconstants.getTime()
                            + "\n" + "備註：" + orderconstants.getRemark()
                            + "\n" + "家事者：" + orderconstants.getCpservice()
                            + "\n" + "付款人姓名：" + orderconstants.getPayname()
                            + "\n" + "服務對象姓名：" + orderconstants.getServicename()
                            + "\n" + "\n" + "訂單金額：" + orderconstants.getCppaymoney() + "元"
                    )             // 設定訊息文字
                    // 設定確定按鈕-顯示文字及監聽器
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

//                    測試直接連到成功畫面
//                            Navigation.findNavController(view).navigate(R.id.cpOrderSuccessFragment);
//
                            Intent intent = new Intent(activity, CpGooglePayMainActivity.class); //intent指定要開啟目的地的頁面 : LoginDialogActivity，把登入的頁面獨立出來成一個頁面，可以在往後的頁面中，直接呼叫出來做登入
                            googlepayLauncher.launch(intent);
                            dialog.dismiss();
                        }


                    })
                    // 設定否定按鈕-顯示文字及監聽器
                    //             .setNegativeButton("No", this)
                    // 設定不決定按鈕-顯示文字及監聽器
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                            dialog.dismiss();
                        }
                    })
                    .setCancelable(true)                               // 設定是否可點擊對話框以外之處離開對話框
                    .show();


        });
        //(本次媒合詳情)
    }


    private void googlePayResult(ActivityResult result) {
        //todo  成功，跳轉至訂單詳情頁面
        if (result.getResultCode() == RESULT_OK) {
            //bt_OrderDetOrderFm_05.setEnabled(true);
            //bt_OrderDetBuyFm_05.setEnabled(false);

//            Navigation.findNavController(cb_CP03_paypereson_member_11).navigate(R.id.cpOrderSuccessFragment);

            NavController navController = Navigation.findNavController(cb_CP03_paypereson_member_11);
            navController.navigate(R.id.cpOrderSuccessFragment);


        }//todo 失敗，跳轉至失敗頁面
        else if (result.getResultCode() == RESULT_FAIL) {
            //tv_OrderDetPayFailFm_05.setText("付款失敗");


            NavController navController = Navigation.findNavController(cb_CP03_paypereson_member_11);
            navController.navigate(R.id.cpOrderFailFragment);

        } else { //todo exit
            NavController navController = Navigation.findNavController(cb_CP03_paypereson_member_11);
            navController.navigate(R.id.f_CleanplanFragment);
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
            Navigation.findNavController(view).popBackStack(R.id.f_CleanPlan_03_Fragment, true);


        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

        //    標題
        tvprojectname.setText("清潔計畫");

    }


}