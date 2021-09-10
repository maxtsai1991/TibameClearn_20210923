package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.Cpreserveorder;

public class Homenotice_11_Fragment extends Fragment {

    private static final String TAG = "TAG_Homenotice_11";
    private Activity activity;
    private FirebaseFirestore db;

    private RecyclerView rv_homenotice_11;
    private List<Cpreserveorder> cpreserveorders;

    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        db = FirebaseFirestore.getInstance();
        cpreserveorders = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_homenotice_11_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handletoolbar(view);
    }

    private void findViews(View view) {
        rv_homenotice_11 = view.findViewById(R.id.rv_homenotice_11);

    }


    //    客製Toolbar
    private void handletoolbar(View view) {
//    抓按鍵
        leftarrowicon = view.findViewById(R.id.iv_arrow_back_11);
        righthomeicon = view.findViewById(R.id.iv_home_11);
//    改標題
        tvprojectname = view.findViewById(R.id.tv_project_name_11);


//    按鍵(需更改導覽路線的ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.homenotice_11_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

//    標題
        tvprojectname.setText("通知");

    }


}