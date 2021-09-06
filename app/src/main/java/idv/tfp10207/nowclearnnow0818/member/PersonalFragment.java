package idv.tfp10207.nowclearnnow0818.member;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.yalantis.ucrop.UCrop;


import java.io.File;
import java.util.Objects;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.member.bean.User;


public class PersonalFragment extends Fragment {
    private static final String TAG = "PersonalFragment";
    private Activity activity;
    //元件
    private ImageView iv_01, iv_clear14_01, iv_back_01, iv_clear15_01;
    private TextView tv_store_01, tv_clear49_01;
    private EditText edt_name_01, edt_email_01, edt_phone_01, edt_address_01, edt_gender_01;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseFirestore db;
    private User user;
    private Uri contentUri; // 拍照需要的Uri

    ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            this::takePictureResult);

    ActivityResultLauncher<Intent> cropPictureLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            this::cropPictureResult);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();//取得Activity參考
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        user = new User();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleToolbar(view);
        handleImageView();
        handleSpinner();
        handleEditText();
        handleCamera();
        handleStory();
    }


    private void findViews(View view) {
        iv_01 = view.findViewById(R.id.iv_01);
        iv_clear14_01 = view.findViewById(R.id.iv_clear14_01);
        iv_clear15_01 = view.findViewById(R.id.iv_clear15_01);
        edt_name_01 = view.findViewById(R.id.edt_name_01);//名字
        edt_email_01 = view.findViewById(R.id.edt_email_01);//信箱
        edt_phone_01 = view.findViewById(R.id.edt_phone_01);//手機
        edt_address_01 = view.findViewById(R.id.edt_address_01);//地址
        edt_gender_01 = view.findViewById(R.id.edt_gender_01);//男女
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_store_01 = view.findViewById(R.id.tv_store_01);
        tv_clear49_01 = view.findViewById(R.id.tv_clear49_01);//名稱
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
                        edt_name_01.setText(user.getName());
                        edt_gender_01.setText(user.getGender());
                        edt_email_01.setText(user.getMail());
                        edt_phone_01.setText(user.getPhone());
                        edt_address_01.setText(user.getAddress());
                    } else {
                        String message = task.getException() == null ?
                                "查無資料" :
                                task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

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

    private void handleCamera() {
        iv_01.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File dir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (dir != null && !dir.exists()) {
                if (!dir.mkdirs()) {
                    return;
                }
            }
            File file = new File(dir, "picture.jpg");
            contentUri = FileProvider.getUriForFile(
                    activity, activity.getPackageName() + ".provider", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            try {
                takePictureLauncher.launch(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(activity, "No camera app found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void takePictureResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            crop(contentUri);
        }
    }

    private void crop(Uri sourceImageUri) {
        File file = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        file = new File(file, "picture_cropped.jpg");
        Uri destinationUri = Uri.fromFile(file);
        Intent cropIntent = UCrop.of(sourceImageUri, destinationUri)
                .getIntent(activity);
        cropPictureLauncher.launch(cropIntent);
    }

    private void cropPictureResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Uri imageUri = UCrop.getOutput(result.getData());
            if (imageUri != null) {
                uploadImage(imageUri);
            }
        }
    }

    private void uploadImage(Uri imageUri) {
        // 取得storage根目錄位置
        StorageReference rootRef = storage.getReference();
        final String imagePath = getString(R.string.app_name)
                + "/images/"
                + Objects.requireNonNull(auth.getCurrentUser()).getUid();
        // 建立當下目錄的子路徑
        final StorageReference imageRef = rootRef.child(imagePath);
        // 將儲存在uri的照片上傳
        imageRef.putFile(imageUri)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String message = "Upload successfully";
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                        // 下載剛上傳好的照片
                        downloadImage(imagePath);
                    } else {
                        String message = task.getException() == null ?
                                "Upload fail" : task.getException().getMessage();
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void downloadImage(String path) {
        final int ONE_MEGABYTE = 1024 * 1024;
        StorageReference imageRef = storage.getReference().child(path);
        imageRef.getBytes(ONE_MEGABYTE)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        byte[] bytes = task.getResult();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        iv_01.setImageBitmap(bitmap);
                    } else {
                        String message = task.getException() == null ?
                                "Download fail" : task.getException().getMessage();
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleImageView() {

        iv_clear14_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.passwordFragment);
        });
        iv_clear15_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.introductionFragment);
        });

    }

    private void handleStory() {
        tv_store_01.setOnClickListener(v -> {
            final String name = String.valueOf(edt_name_01.getText());
            final String gender = String.valueOf(edt_gender_01.getText());
            final String mail = String.valueOf(edt_email_01.getText());
            final String phone = String.valueOf(edt_phone_01.getText());
            final String address = String.valueOf(edt_address_01.getText());

            // 判斷名稱不可為空
            if (name.isEmpty()){
                edt_name_01.setError("請輸入名字");
            }
            //判斷信箱不可為空
            if (mail.isEmpty()) {
                edt_email_01.setError("請輸入信箱");
            }
            //判斷電話不可為空
            if (phone.isEmpty()) {
                edt_phone_01.setError("請輸入電話");
            }
            //判斷地址不可為空
            if (address.isEmpty()) {
                edt_address_01.setError("請輸入地址");
            } else {
                // 將文件 ID (UID) 設為 ID
                user.setID(auth.getCurrentUser().getUid());
                // 個人資訊
                final String imagePath = getString(R.string.app_name)
                        + "/images/"
                        + Objects.requireNonNull(auth.getCurrentUser()).getUid();
                user.setImagePath(imagePath);
                user.setName(name);
                user.setGender(gender);
                user.setMail(mail);
                user.setPhone(phone);
                user.setAddress(address);
                modify(user);

            }
        });
    }

    private void modify(User user) {
        // 修改指定 ID 的文件
        db.collection("users").document(user.getID()).set(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String message = "修改成功 with ID: " + user.getID();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    } else {
                        String message = task.getException() == null ?
                                "修改失敗" : task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }




    private void handleSpinner() {
//        // 註冊/實作 選項被選取監聽器
//        sp_gender_01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            // 當選取的選項改變時，自動被呼叫
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

    }

    private void handleToolbar(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.memberCentreFragment);
        });


    }

}
