package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.Cpreserveorder;

public class Reserve_02_Fragment extends Fragment {
    private static final String TAG = "TAG_Reserve_02";
    private Activity activity;


    private FirebaseFirestore db;
    private Cpreserveorder cpreserveorder;

    private RatingBar rb_reserve_assess_start_11;
    private CheckBox cb_reserve_assess1_11, cb_reserve_assess2_11,cb_reserve_assess3_11, cb_reserve_assess4_11,cb_reserve_assess5_11;
    private Button bt_reserve_assess_send_11;


    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        activity = getActivity();

        return inflater.inflate(R.layout.fragment_reserve_02_, container, false);
    }


    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
        handleSend(view);
        handletoolbar(view);
    }

    private void findview(View view) {
        bt_reserve_assess_send_11 = view.findViewById(R.id.bt_reserve_assess_send_11);
    }

    private void handleSend(View view) {
        bt_reserve_assess_send_11.setOnClickListener(v -> {
            if (getArguments() != null) {
                cpreserveorder = (Cpreserveorder) getArguments().getSerializable("cpreserveorders");
            }
//            cpreserveorder.setCporderstate(cpreserveorder.getCporderstate()+0);
//
//            modify(cpreserveorder);

            cpreserveorder.setCporderstate(cpreserveorder.getCporderstate()+1);

            Bundle bundle = new Bundle();
            bundle.putSerializable("cpreserveorders", cpreserveorder);

            Log.d(TAG,"????????????"+cpreserveorder.getCporderstate());

            modify(cpreserveorder);


            Navigation.findNavController(v).navigate(R.id.reserve_01_Fragment);

        });
    }


    private void modify(Cpreserveorder cpreserveorder) {
        // ???????????? ID ?????????
        db.collection("cleanplanorder").document(cpreserveorder.getCpordernumber()).set(cpreserveorder)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String message = "???????????? with ID: " + cpreserveorder.getCpordernumber();
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        // ?????????????????????????????????
//                        NavController navController = Navigation.findNavController(tv_modify_tast1);
//                        navController.navigate(R.id.indexFragment);
                    } else {
                        String message = task.getException() == null ?
                                "????????????" : task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }




    //    ??????Toolbar
    private void handletoolbar(View view) {
//    ?????????
        leftarrowicon = view.findViewById(R.id.iv_arrow_back_11);
        righthomeicon = view.findViewById(R.id.iv_home_11);
//    ?????????
        tvprojectname = view.findViewById(R.id.tv_project_name_11);


//    ??????(????????????????????????ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.reserve_02_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

//    ??????
        tvprojectname.setText("????????????");

    }



}