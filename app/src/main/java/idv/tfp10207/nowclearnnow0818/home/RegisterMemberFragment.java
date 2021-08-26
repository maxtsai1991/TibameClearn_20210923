package idv.tfp10207.nowclearnnow0818.home;

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

import idv.tfp10207.nowclearnnow0818.R;


public class RegisterMemberFragment extends Fragment {
    private EditText etmembername,etmembergender,etmemberemail,etmemberphone,etmemberaddress;
    private Button btmemberregister;
    private TextView tvdebug; //debug



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        });

        tvdebug.setOnClickListener(v -> {
            etmembername.setText("陳嘉");
            etmembergender.setText("女");
            etmemberemail.setText("abi@gmail.com");
            etmemberphone.setText("0952123456");
            etmemberaddress.setText("台北市大安區忠孝東路四段285號");
        });

    }


}