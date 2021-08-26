package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;


public class PersonalFragment extends Fragment {
    private Activity activity;
    private ImageView imageView, iv_clear14_01, iv_back_01, iv_clear15_01;
    private EditText edt_name_01, edt_email_01, edt_phone_01, edt_address_01;
    private Spinner sp_gender_01;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleToolbar(view);
        handleImageView();
        handleSpinner();
    }

    private void findViews(View view) {
        imageView = view.findViewById(R.id.imageView);
        iv_clear14_01 = view.findViewById(R.id.iv_clear14_01);
        iv_clear15_01 = view.findViewById(R.id.iv_clear15_01);
        edt_name_01 = view.findViewById(R.id.edt_name_01);
        edt_email_01 = view.findViewById(R.id.edt_email_01);
        edt_phone_01 = view.findViewById(R.id.edt_phone_01);
        edt_address_01 = view.findViewById(R.id.edt_address_01);
        sp_gender_01 = view.findViewById(R.id.sp_gender_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
    }

    private void handleToolbar(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.personalFragment,true);
        });

    }

    private void handleImageView() {
        iv_clear14_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.passwordFragment);
        });
        iv_clear15_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.introductionFragment);
        });
        }

    private void handleSpinner() {
        // 註冊/實作 選項被選取監聽器
        sp_gender_01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 當選取的選項改變時，自動被呼叫
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}