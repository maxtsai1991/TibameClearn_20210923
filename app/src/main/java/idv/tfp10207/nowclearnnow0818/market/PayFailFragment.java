package idv.tfp10207.nowclearnnow0818.market;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import idv.tfp10207.nowclearnnow0818.R;

public class PayFailFragment extends Fragment {

    private Button bt_GooglePayFail_05;
    private TextView tv_cl_GooglePayFail2_05, tv_cl_GooglePayFail3_05;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_fail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        handleButton();
        handleView();
    }


    private void findViews(View view) {
        bt_GooglePayFail_05 = view.findViewById(R.id.bt_GooglePayFail_05);
        tv_cl_GooglePayFail2_05 = view.findViewById(R.id.tv_cl_GooglePayFail2_05);
        tv_cl_GooglePayFail3_05 = view.findViewById(R.id.tv_cl_GooglePayFail3_05);
    }

    private void handleButton() {
        bt_GooglePayFail_05.setOnClickListener( view -> {
            NavController navController = Navigation.findNavController(bt_GooglePayFail_05);
            navController.navigate(R.id.action_payFailFragment_to_orderDetFragment);
        });
    }

    private void handleView() {
        tv_cl_GooglePayFail2_05.setText("付款失敗");
        tv_cl_GooglePayFail3_05.setText("請再重新執行付款流程");
    }

}