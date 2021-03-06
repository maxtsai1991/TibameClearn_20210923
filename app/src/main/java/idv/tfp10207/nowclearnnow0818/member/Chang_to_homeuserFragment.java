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


public class Chang_to_homeuserFragment extends Fragment {
    private Activity activity;
    private ImageView iv_dir_01, iv_dir1_01, iv_dir2_01, iv_dir3_01, iv_dir4_01, iv_dir5_01;
    private ImageView iv_off2_01, iv_picture3_01, iv_home_01, iv_question_01;
    private TextView tv_clv8_01;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private User user;

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
        return inflater.inflate(R.layout.fragment_chang_to_homeuser, container, false);
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
        iv_dir_01 = view.findViewById(R.id.iv_dir_01);
        iv_dir1_01 = view.findViewById(R.id.iv_dir1_01);
        iv_dir2_01 = view.findViewById(R.id.iv_dir2_01);
        iv_dir3_01 = view.findViewById(R.id.iv_dir3_01);
        iv_dir4_01 = view.findViewById(R.id.iv_dir4_01);
        iv_dir5_01 = view.findViewById(R.id.iv_dir5_01);
        iv_off2_01 = view.findViewById(R.id.iv_off2_01);
        iv_picture3_01 = view.findViewById(R.id.iv_picture3_01);
        tv_clv8_01 = view.findViewById(R.id.tv_clv8_01);
        iv_home_01 = view.findViewById(R.id.iv_home_01);
        iv_question_01 = view.findViewById(R.id.iv_question_01);
    }

    private void handleTextview() {
        tv_clv8_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.memberCentreFragment);
        });
    }

    private void handleImageView() {
        iv_dir_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.personalFragment);
        });
        iv_dir1_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.addDelCommodityFragment);
        });
        iv_dir3_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.orderFragment);
        });
        iv_dir4_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.success_get_Fragment);
        });
        iv_dir5_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.evaluationFragment);
        });
        iv_home_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.homePageFragment072);
        });
        iv_question_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.QAFragment);
        });
        iv_off2_01.setOnClickListener(view -> {
            LogoutDialog();
        });


    }

    private void handlePicture() {
        db.collection("users")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                // ????????????
                .get()
                // ???????????????????????????
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // ???????????????????????????????????????
                        DocumentSnapshot documentSnapshot = task.getResult();
                        user = documentSnapshot.toObject(User.class);
                        assert user != null;
                        if ((user.getImagePath() != null)) {
                            showImage(iv_picture3_01, user.getImagePath());
                        }
                    }
                });
    }

    private void showImage(ImageView iv_picture3_01, String imagePath) {
        final int ONE_MEGABYTE = 1024 * 1024;
        StorageReference imageRef = storage.getReference().child(imagePath);
        imageRef.getBytes(ONE_MEGABYTE)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        byte[] bytes = task.getResult();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        iv_picture3_01.setImageBitmap(bitmap);
                    } else {
                        String message = task.getException() == null ?
                                "Download fail" + " : " + imagePath :
                                task.getException().getMessage() + " : " + imagePath;
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }

                });
    }

    private void handleGmail() {
        iv_dir2_01.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "tibametfp102@email.com" });
            intent.putExtra(Intent.EXTRA_SUBJECT, "??????????????????");
            intent.putExtra(Intent.EXTRA_TEXT, "???????????????????????????????????????????????????");
            startActivity(Intent.createChooser(intent, ""));
        });

    }

    private void LogoutDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("??????????????????");
        alertDialog.setPositiveButton("???", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getBaseContext(), "?????????", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("???", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ??????
                auth.signOut();
                // ???????????????????????????
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setCancelable(false);   // disable click other area
        alertDialog.show();
    }
}