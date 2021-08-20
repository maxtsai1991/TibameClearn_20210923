package idv.tfp10207.nowclearnnow0818.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;


public class LoginFragment extends Fragment {
    private Button btForget;
    private Button btHouseworker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login07, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleButton();

    }

    private void findViews(View view) {
        btForget = view.findViewById(R.id.tv_foreget_password);
        btHouseworker = view.findViewById(R.id.bt_houseworker);

    }

    private void handleButton() {


    }



}





