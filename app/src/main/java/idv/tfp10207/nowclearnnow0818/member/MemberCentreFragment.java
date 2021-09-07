package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import idv.tfp10207.nowclearnnow0818.MainActivity;
import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.member.bean.User;


public class MemberCentreFragment extends Fragment {
    private Activity activity;
    private ImageView iv_clear1_01, iv_clear3_01, iv_clear4_01;
    private ImageView iv_home_01, iv_question_01, iv_picture_01, iv_off_01;
    private TextView tv_clear8_01;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private User user;
//    String str1 = "一般會員";
//    String str2 = "家事者";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        setHasOptionsMenu(true);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        user = new User();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member_centre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleImageView();
        handleTextview();
        handlePicture();
        handleGmail();
    }

    private void findViews(View view) {
        iv_clear1_01 = view.findViewById(R.id.iv_clear1_01);
        iv_clear3_01 = view.findViewById(R.id.iv_clear3_01);
        iv_clear4_01 = view.findViewById(R.id.iv_clear4_01);
        iv_home_01 = view.findViewById(R.id.iv_home_01);
        tv_clear8_01 = view.findViewById(R.id.tv_clear8_01);
        iv_question_01 = view.findViewById(R.id.iv_question_01);
        tv_clear8_01 = view.findViewById(R.id.tv_clear8_01);
        iv_picture_01 = view.findViewById(R.id.iv_picture_01);
        iv_clear3_01 = view.findViewById(R.id.iv_clear3_01);
        iv_off_01 = view.findViewById(R.id.iv_off_01);
    }

    private void handleTextview() {
        tv_clear8_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.chang_to_homeuserFragment);
//        tv_clear1_01.setTag(false);//标记textview为false（表示没有被点击过）
//        tv_clear1_01.setText(str1);//設置原来的文本
//            boolean flag = (boolean) tv_clear1_01.getTag();//当点击时，首先判断是否已经点击过
//            if (!flag) {
//                tv_clear1_01.setText(str2);
//                tv_clear1_01.setTag(true);
//            } else {//已经点击过了
//                tv_clear1_01.setText(str1);
//                tv_clear1_01.setTag(false);
//            }

        });
    }

    private void handleImageView() {
        iv_clear1_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.personalFragment);
        });
        iv_clear4_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.orderFragment);
        });
        iv_question_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.QAFragment);
        });
        iv_home_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.homePageFragment072);
        });

        iv_off_01.setOnClickListener(view -> {
            LogoutDialog();
        });
    }

    private void handlePicture() {
        db.collection("users")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                // 獲取資料
                .get()
                // 設置網路傳輸監聽器
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // 將獲取的資料存成自定義類別
                        DocumentSnapshot documentSnapshot = task.getResult();
                        user = documentSnapshot.toObject(User.class);
                        assert user != null;
                        if ((user.getImagePath() != null)) {
                            showImage(iv_picture_01, user.getImagePath());
                        }
                    }
                });
    }

    private void showImage(ImageView iv_picture_01, String imagePath) {
        final int ONE_MEGABYTE = 1024 * 1024;
        StorageReference imageRef = storage.getReference().child(imagePath);
        imageRef.getBytes(ONE_MEGABYTE)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        byte[] bytes = task.getResult();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        iv_picture_01.setImageBitmap(bitmap);
                    } else {
                        String message = task.getException() == null ?
                                "Download fail" + " : " + imagePath :
                                task.getException().getMessage() + " : " + imagePath;
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }

                });
    }

    private void handleGmail() {
        iv_clear3_01.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "tibametfp102@email.com" });
            intent.putExtra(Intent.EXTRA_SUBJECT, "客服人員你好");
            intent.putExtra(Intent.EXTRA_TEXT, "我想請問有關商品及預約家事者的問題");
            startActivity(Intent.createChooser(intent, ""));
        });
    }

    private void LogoutDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("是否確定登出");
        alertDialog.setPositiveButton("否", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getBaseContext(), "未登出", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 登出
                auth.signOut();
                // 登出後跳轉至歡迎頁
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setCancelable(false);   // disable click other area
        alertDialog.show();
    }
}
