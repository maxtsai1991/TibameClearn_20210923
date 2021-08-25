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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;
//  0.上一頁資訊  Bundle
//  1.預估清潔規模
//  2.選擇清潔時間
//  3.上傳照片
//  4.備註
//  5.服務對象資料
//  6.彈跳視窗 :簡單的訂單明細與訂單付款金額
public class F_CleanPlan_02_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CleanPlan_02_Fragment";
    private Activity activity;

//  1.預估清潔規模
    private EditText et_people_11;
    private EditText et_ping_11;
//  2.選擇清潔時間
    private RadioGroup rg_CP02time1_11;

//  3.上傳照片
    private ImageView iv_CP02_takepicture;
    private ImageView iv_CP02_takepicture_11;

//  4.備註
    private EditText et_CP02_remark_11;

//  5.服務對象資料
    private CheckBox cb_memberinit_11;

//  6.彈跳視窗
    private  Button bt_CP02next_11;

    //next button
    private Button bt_cp02_next;
    //Toolbar
    private Button righthomeicon;
    private Button leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_f__clean_plan_02_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
        handlenext(view);
        handletoolbar(view);

//        handlebutton(view);
        handleleRadioButton(view);
        handleCheckBoxes(view);
    }

    private void findview(View view) {
        bt_cp02_next = view.findViewById(R.id.bt_CP02next_11);
        et_people_11 = view.findViewById(R.id.et_people_11);
        et_ping_11 = view.findViewById(R.id.et_ping_11);
        rg_CP02time1_11 = view.findViewById(R.id.rg_CP02time1_11);
        cb_memberinit_11 = view.findViewById(R.id.cb_memberinit_11);
        bt_CP02next_11 = view.findViewById(R.id.bt_CP02next_11);
    }


//  2.選擇清潔時間   RadioButton

    private void handleleRadioButton(View view) {
        rg_CP02time1_11.setOnCheckedChangeListener((group, checkedId) -> {
            final RadioButton rb_CP01gender_11 = group.findViewById(checkedId);
//            textView待修改為bundle，以將檔案傳送至下一頁
//            textView.setText("(RadioButton) " + rb_CP01gender_11.getText());
        });

    }

//5.同會員資料
    private void handleCheckBoxes(View view) {
        final CompoundButton.OnCheckedChangeListener listener = (checkBox, isChecked) -> {
//            帶入會員資料
//            textView.setText("(CheckBox) " + checkBox.getText() + ": " + isChecked);
        };
        cb_memberinit_11.setOnCheckedChangeListener(listener);
    }


    //  6.彈跳視窗 :簡單的訂單明細與訂單付款金額

    private void handleBtAlert(View view) {
        bt_CP02next_11.setOnClickListener(v -> {
            activity = getActivity();
            new AlertDialog.Builder(getActivity())                    // 實例化AlertDialog.Builder物件
                    .setTitle("訂單金額")                                 // 設定標題文字
//                    用基本費用與規模去做計算
//                    .setMessage(R.string.textSubmitMessage)             // 設定訊息文字

//                    有點不懂這段後面的參數在fragment該怎麼設置            // 設定確定按鈕-顯示文字及監聽器
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    // 設定否定按鈕-顯示文字及監聽器
//                    .setNegativeButton("No", this)

                    // 設定不決定按鈕-顯示文字及監聽器
                    .setNeutralButton("exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })  // 設定不決定按鈕-顯示文字及監聽器
                    .setCancelable(true)                               // 設定是否可點擊對話框以外之處離開對話框
                    .show();
        });
    }





    //next button
    private void handlenext(View view) {
        bt_cp02_next.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_03_Fragment);

        });
    }

    //    客製Toolbar
    private void handletoolbar(View view) {
//    抓按鍵
        leftarrowicon = view.findViewById(R.id.iv_arrow_back);
        righthomeicon = view.findViewById(R.id.iv_home);
//    改標題
        tvprojectname = view.findViewById(R.id.tv_project_name);


//    按鍵(需更改導覽路線的ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.f_CleanPlan_02_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.homePageFragment072);
        });

//    標題
        tvprojectname.setText("清潔計畫");

    }

}