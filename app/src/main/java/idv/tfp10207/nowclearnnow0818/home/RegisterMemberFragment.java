package idv.tfp10207.nowclearnnow0818.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import idv.tfp10207.nowclearnnow0818.R;


public class RegisterMemberFragment extends Fragment {
    private final static String TAG = "TAG_MainFragment";
    private Activity activity;
    private EditText etmembername,etmembergender,etmemberemail,etmemberphone,etmemberaddress,et_register_account07,et_register_password07;
    private Button btmemberregister;
    private TextView tvdebug; //debug



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_member, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view); //註冊元件
        handleRegisterMember();
    }

    private void findViews(View view) {
        et_register_account07 = view.findViewById(R.id.et_register_account07);
        et_register_password07 = view.findViewById(R.id.et_register_password07);
        etmembername = view.findViewById(R.id.et_register_member_name07);
        etmembergender = view.findViewById(R.id.et_register_member_gender07);
        etmemberemail = view.findViewById(R.id.et_register_email07);
        etmemberphone = view.findViewById(R.id.et_register_phone07);
        etmemberaddress = view.findViewById(R.id.et_register_address07);
        btmemberregister = view.findViewById(R.id.bt_member_login);
        tvdebug = view.findViewById(R.id.tvdebug);
    }

    private void handleRegisterMember() {
        btmemberregister.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
            Toast.makeText(activity, "註冊完成", Toast.LENGTH_SHORT).show();
        });

        tvdebug.setOnClickListener(v -> {
            et_register_account07.setText("aaa@gmail.com");
            et_register_password07.setText("98745612");
            etmembername.setText("王大明");
            etmembergender.setText("男");
            etmemberemail.setText("aaa@gmail.com");
            etmemberphone.setText("0922222222");
            etmemberaddress.setText("台北市中山區吉林路");
        });

    }


}