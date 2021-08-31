package idv.tfp10207.nowclearnnow0818.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;


public class RegisterHouseworkInfoFragment extends Fragment {
    private final static String TAG = "TAG_MainFragment";
    private Activity activity;
    private EditText etaccount,etpassword,etaddress,etphone,etemail,etexpertise,etcharge,etother,etselfintroduction;
    private Button btregisterhouseworker;
    private TextView tvhousedebug;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_register_housework_info07, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view); //註冊元件
        handleRegisterHouseWorker();

    }

    private void findViews(View view) {
        etaccount = view.findViewById(R.id.et_house_Account);
        etpassword = view.findViewById(R.id.et_houser_Password);
        etaddress = view.findViewById(R.id.et_house_address);
        etphone = view.findViewById(R.id.et_house_Phone);
        etemail = view.findViewById(R.id.et_house_email);
        etexpertise = view.findViewById(R.id.et_house_item);
        etcharge = view.findViewById(R.id.et_house_charge);
        etother = view.findViewById(R.id.et_house_other);
        etselfintroduction = view.findViewById(R.id.et_house_Self_introduction);
        btregisterhouseworker = view.findViewById(R.id.bt_registerHouserwork);
        tvhousedebug = view.findViewById(R.id.tv_registerhouser_debug07);
    }

    private void handleRegisterHouseWorker() {
        tvhousedebug.setOnClickListener(v -> {
            etaccount.setText("le55@gmail.com");
            etpassword.setText("85209888");
            etaddress.setText("台北市大同區重慶北路一段");
            etphone.setText("0985472666");
            etemail.setText("le55@gmail.com");
            etexpertise.setText("廁所清潔");
            etcharge.setText("$300");
            etother.setText("不喜寵物");
            etselfintroduction.setText("樂於學習新事物，\n我相信知識與經驗,是在不同環境中學到");
        });

        btregisterhouseworker.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });
    }


}