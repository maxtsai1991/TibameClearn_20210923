package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;

//  0.前面訂單的資料  >>>bundle
//  1.選擇付款方式 改googlepay
//  1-1.信用卡資訊   改googlepay
//  2.付款人資訊  與 同會員資料
//  3.彈跳視窗: 確認媒合>>>訂單送出 (本次媒合詳情)
//     googlepay

//  3-1.訂單資訊回傳給資料庫
//  3-2.推撥給家事者

public class F_CleanPlan_03_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CleanPlan_03_Fragment";
    private Activity activity;

//  2.付款人資訊  與 同會員資料
    private CheckBox cb_CP03_paypereson_member_11;
    private EditText et_CP03_payperson_name_11;
    private EditText et_CP03_payperson_Phone_11;
    private EditText et_CP03_payperson_Email_11;
    private EditText et_CP03_payperson_Address_11;

//  3.彈跳視窗: 確認媒合>>>訂單送出
    private  Button bt_CP03next_11;

    //next button
    private Button bt_cp03_next;
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
        return inflater.inflate(R.layout.fragment_f__clean_plan_03_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
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
        cb_CP03_paypereson_member_11.setOnCheckedChangeListener(listener);

    }

//  3.nextbutton彈跳視窗 :(本次媒合詳情)
    private void handleNextBtAlert(View view) {
        bt_cp03_next.setOnClickListener(v -> {
            activity = getActivity();
            new AlertDialog.Builder(getActivity())                    // 實例化AlertDialog.Builder物件
                    .setTitle("確認媒合")                                 // 設定標題文字
    //簡單的訂單明細與訂單付款金額
                    .setMessage("本次媒合詳情:"+"")             // 設定訊息文字
    // 設定確定按鈕-顯示文字及監聽器
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_04_Fragment);
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