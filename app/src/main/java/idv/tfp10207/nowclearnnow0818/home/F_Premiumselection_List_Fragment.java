package idv.tfp10207.nowclearnnow0818.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import idv.tfp10207.nowclearnnow0818.R;


public class F_Premiumselection_List_Fragment extends Fragment {
    private static final String TAG = "TAG_F_Premiumselection_List_Fragment";
    private Activity activity;
    private ConstraintLayout cl_girldemo_member_a004;
    private ImageView iv_premiumselection_return07;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_f__premiumselection__list_, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        handlehomeworks();
        handleButton();
    }

    private void findViews(View view) {
        cl_girldemo_member_a004 = view.findViewById(R.id.cl_girldemo_member_a004);
        iv_premiumselection_return07 = view.findViewById(R.id.iv_premiumselection_return07);
    }

    private void handleButton() {
        iv_premiumselection_return07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });
    }

    private void handlehomeworks() {
        cl_girldemo_member_a004.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.f_Premiumselection_Fragment07);
        });
    }
}