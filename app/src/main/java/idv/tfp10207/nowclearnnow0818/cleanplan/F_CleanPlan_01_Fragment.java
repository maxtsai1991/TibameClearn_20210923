package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class F_CleanPlan_01_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CleanPlan_01_Fragment";
    private Activity activity;

    //next button
    private Button bt_cp01_next;
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
        return inflater.inflate(R.layout.fragment_f__clean_plan_01_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
//        handlebutton();
//        handletoolbar(view);
    }

    private void findview(View view) {
        bt_cp01_next = view.findViewById(R.id.bt_CP01next_11);

    }


//next button
//    private void handlebutton() {
//        tv_cp00_next.setOnClickListener(v -> {
//           NavController navController = Navigation.findNavController(v);
//           navController.navigate(R.id.action_ch2_10Fragment2_to_hwchapter_Fragment);
//
//        });
//    }


//    //    客製Toolbar
//    private void handletoolbar(View view) {
////    抓按鍵
//        leftarrowicon = view.findViewById(R.id.iv_arrow_back);
//        righthomeicon = view.findViewById(R.id.iv_home);
////    改標題
//        tvprojectname = view.findViewById(R.id.tv_project_name)
//
//
//
////    按鍵(需更改導覽路線的ID)
//        leftarrowicon.setOnClickListener(v -> {
//            Toast.makeText(activity, "返回", Toast.LENGTH_SHORT).show();
//            NavController navController = Navigation.findNavController(v);
//            navController.navigate(R.id.action_ch2_10Fragment2_to_hwchapter_Fragment);
//        });
//
//        righthomeicon.setOnClickListener(v -> {
//            Toast.makeText(activity, "回首頁", Toast.LENGTH_SHORT).show();
//            NavController navController = Navigation.findNavController(v);
//            navController.navigate(R.id.action_ch2_10Fragment2_to_hwchapter_Fragment);
//        });
//
////    標題
//        tvprojectname.setText("CH02_10");
//
//}

}