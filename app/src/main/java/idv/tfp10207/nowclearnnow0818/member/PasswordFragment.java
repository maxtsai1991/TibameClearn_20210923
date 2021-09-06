package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;

public class PasswordFragment extends Fragment {
    private Activity activity;
    private ImageView iv_back_01;
    private EditText et_clear0_01, et_clear1_01, et_clear2_01;
    private TextView tv_clear15_01;
    private Button bt_confirm_01;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        handleToolbar(view);
        handleImageView();
        handleButton();
    }

    private void findView(View view) {
        et_clear0_01 = view.findViewById(R.id.et_clear0_01);
        et_clear1_01 = view.findViewById(R.id.et_clear1_01);
        et_clear2_01 = view.findViewById(R.id.et_clear2_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        bt_confirm_01 = view.findViewById(R.id.bt_confirm_01);
        et_clear0_01 = view.findViewById(R.id.et_clear0_01);
        et_clear1_01 = view.findViewById(R.id.et_clear1_01);
        et_clear2_01 = view.findViewById(R.id.et_clear2_01);
        tv_clear15_01 = view.findViewById(R.id.tv_clear15_01);
    }

    private void handleImageView() {
        tv_clear15_01.setOnClickListener(v -> {
            et_clear0_01.setText("98745612");
            et_clear1_01.setText("5674321");
            et_clear2_01.setText("5674321");
        });
    }

    private void handleButton() {
        bt_confirm_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.memberCentreFragment);
            Toast.makeText(activity, "變更成功", Toast.LENGTH_SHORT).show();
        });
    }

    private void handleToolbar(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.passwordFragment,true);
        });
    }
}