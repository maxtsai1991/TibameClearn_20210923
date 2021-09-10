package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.member.bean.User;


public class Home_user_personalFragment extends Fragment {
    private Activity activity;
    private ImageView iv_cl14_01, iv_cl15_01, iv_back_01, iv_01;
    private TextView tv_st_01;
    private EditText edt_name1_01, edt_gender1_01, edt_email1_01, edt_phone1_01, edt_address1_01;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseFirestore db;
    private User user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        activity = getActivity();//取得Activity參考
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        user = new User();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_user_personal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleImageView();
    }

    private void findViews(View view) {
        iv_cl14_01 = view.findViewById(R.id.iv_cl14_01);
        iv_cl15_01 = view.findViewById(R.id.iv_cl15_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_st_01 = view.findViewById(R.id.tv_st_01);
        edt_name1_01 = view.findViewById(R.id.edt_name1_01);
        edt_gender1_01 = view.findViewById(R.id.edt_gender1_01);
        edt_email1_01 = view.findViewById(R.id.edt_email1_01);
        edt_phone1_01 = view.findViewById(R.id.edt_phone1_01);
        edt_address1_01 = view.findViewById(R.id.edt_address1_01);
        iv_01 = view.findViewById(R.id.iv_01);
    }
    private void handleEditText() {
        // 查詢指定集合
        db.collection("users")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                // 獲取資料
                .get()
                // 設置網路傳輸監聽器
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // 將獲取的資料存成自定義類別
                        // for (DocumentSnapshot documentSnapshot : task.getResult())
                        DocumentSnapshot documentSnapshot = task.getResult();
                        user = documentSnapshot.toObject(User.class);
                        assert user != null;
                        if ((user.getImagePath() != null)){
                            showImage(iv_01, user.getImagePath());
                        }
                        edt_name1_01.setText(user.getName());
                        edt_gender1_01.setText(user.getGender());
                        edt_email1_01.setText(user.getMail());
                        edt_phone1_01.setText(user.getPhone());
                        edt_address1_01.setText(user.getAddress());
                    } else {
                        String message = task.getException() == null ?
                                "查無資料" :
                                task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    // 下載Firebase storage的照片並顯示在imageView上
    private void showImage(ImageView imageView, String imagePath) {
        final int ONE_MEGABYTE = 1024 * 1024;
        StorageReference imageRef = storage.getReference().child(imagePath);
        imageRef.getBytes(ONE_MEGABYTE)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        byte[] bytes = task.getResult();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageView.setImageBitmap(bitmap);
                    } else {
                        String message = task.getException() == null ?
                                "Download fail" + " : " + imagePath :
                                task.getException().getMessage() + " : " + imagePath;
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleImageView() {
        iv_cl14_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.passwordFragment);
        });
        iv_cl15_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.introductionFragment);
        });
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.homeUserFragment);
        });
        tv_st_01.setOnClickListener(v -> {
            Toast.makeText(activity, "變更成功", Toast.LENGTH_SHORT).show();
        });
    }
}