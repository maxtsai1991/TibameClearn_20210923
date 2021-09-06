package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.member.bean.Myself;


public class GoodatFragment extends Fragment {
    private static final String TAG = "GoodatFragment";
    private Activity activity;
    private TextView tv_store1_01;
    private EditText et_good_01;
    private ImageView iv_back_01;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private Myself myself;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        myself = new Myself();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goodat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleToolbar(view);
        handleTextView(view);
    }

    private void findViews(View view) {
        et_good_01 = view.findViewById(R.id.et_good_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_store1_01 = view.findViewById(R.id.tv_store1_01);
    }

    private void handleTextView(View view) {
// 查詢指定集合
        db.collection("myself")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                // 獲取資料
                .get()
                // 設置網路傳輸監聽器
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // 將獲取的資料存成自定義類別
                        DocumentSnapshot documentSnapshot = task.getResult();
                        myself = documentSnapshot.toObject(Myself.class);
                        assert myself != null;
                        et_good_01.setText(myself.getMyself());
                    } else {
                        String message = task.getException() == null ?
                                "查無資料" :
                                task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }

                });
        tv_store1_01.setOnClickListener(v -> {
            myself.setMyself(String.valueOf(et_good_01.getText()));
            // 修改指定 ID 的文件
            db.collection("myself")
                    .document(auth.getCurrentUser().getUid()).set(myself)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String message = "修改成功";
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.introductionFragment);
                        } else {
                            String message = task.getException() == null ?
                                    "修改失敗" : task.getException().getMessage();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

    private void handleToolbar(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.goodatFragment, true);
        });

    }
}