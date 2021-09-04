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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Objects;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.bean.Material;


public class PersonalFragment extends Fragment {
    private static final String TAG = "PersonalFragment";
    private Activity activity;
    //元件
    private ImageView imageView, iv_clear14_01, iv_back_01, iv_clear15_01;
    private TextView tv_store_01, tv_clear49_01;
    private EditText edt_name_01, edt_email_01, edt_phone_01, edt_address_01;
    private Spinner sp_gender_01;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseFirestore db;
    private Material material;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();//取得Activity參考
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

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
    }

    private void handleEditText() {
        if (getArguments() != null) {
            material = (Material) getArguments().getSerializable("users");
            if (material != null) {
                edt_name_01.setText(material.getName());
                edt_email_01.setText(material.getMail());
                edt_phone_01.setText(material.getPhone());
                edt_address_01.setText(material.getAddress());
            }
        }
///**
// 查詢指定文件內的欄位 (EX : uid)
// .whereEqualTo("uid", auth.getCurrentUser().getUid())
// 查詢指定文件 (有給值指定，文件 ID 為當下使用者的 UID)
// .document(auth.getCurrentUser().getUid())
// 查詢指定文件 (沒給值，系統產生一組隨機字串作為文件 ID)
// .document()
// */
//        // 查詢指定集合
//        db.collection("users")
//                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
//                 //獲取資料
//                .get()
//                //設置網路傳輸監聽器
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        // 將獲取的資料存成自定義類別
//                        // for (DocumentSnapshot documentSnapshot : task.getResult())
//                        DocumentSnapshot documentSnapshot = task.getResult();
//                        material = documentSnapshot.toObject(Material.class);
//                        showInfo();
//                    } else {
//                        String message = task.getException() == null ?
//                                "查無資料" :
//                                task.getException().getMessage();
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    private void findViews(View view) {
        imageView = view.findViewById(R.id.imageView);
        iv_clear14_01 = view.findViewById(R.id.iv_clear14_01);
        iv_clear15_01 = view.findViewById(R.id.iv_clear15_01);
        edt_name_01 = view.findViewById(R.id.edt_name_01);//名字
        edt_email_01 = view.findViewById(R.id.edt_email_01);//信箱
        edt_phone_01 = view.findViewById(R.id.edt_phone_01);//手機
        edt_address_01 = view.findViewById(R.id.edt_address_01);//地址
        sp_gender_01 = view.findViewById(R.id.sp_gender_01);//男女
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_store_01 = view.findViewById(R.id.tv_store_01);
        tv_clear49_01 = view.findViewById(R.id.tv_clear49_01);//名稱
    }

//    private void showInfo() {
//        edt_name_01.append(" " + material.getName());
//        edt_email_01.append(" " + material.getMail());
//        edt_phone_01.append(" " + material.getPhone());
//        edt_address_01.append(" " + material.getAddress());
//    }

    private void handleImageView() {
        tv_clear49_01.setOnClickListener(v -> {
            edt_name_01.setText("陳小明");
            edt_email_01.setText("b123@gmail.com");
            edt_phone_01.setText("0965555888");
            edt_address_01.setText("台北市大安區和平東路二段");
        });

        iv_clear14_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.passwordFragment);
        });
        iv_clear15_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.introductionFragment);
        });
        tv_store_01.setOnClickListener(view -> {
            String name = edt_name_01.getText().toString().trim();
            if (name.length() <= 0) {
                Toast.makeText(activity, "name is invalid",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            String phone = edt_phone_01.getText().toString().trim();
            String address = edt_address_01.getText().toString().trim();
            String email = edt_email_01.getText().toString().trim();

            material.setName(name);
            material.setPhone(phone);
            material.setAddress(address);
            material.setMail(email);
            Navigation.findNavController(view).navigate(R.id.memberCentreFragment);
            Toast.makeText(activity, "變更成功", Toast.LENGTH_SHORT).show();
        });

    }


    private void handleSpinner () {
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

    private void handleToolbar (View view){
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.personalFragment, true);
        });

    }
}
//    public void getImage(final ImageView imageView, final String path) {
//        final int ONE_MEGABYTE = 1024 * 1024 * 6; //設定上限
//        StorageReference imageRef = storage.getReference().child(path);
//        imageRef.getBytes(ONE_MEGABYTE)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        byte[] bytes = task.getResult();
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                        imageView.setImageBitmap(bitmap);
//                    } else {
//                        String errorMessage = task.getException() == null ? "" : task.getException().getMessage();
//                        Toast.makeText(activity, "圖片取得錯誤", Toast.LENGTH_SHORT).show();
//                        Log.d("顯示Firebase取得圖片的錯誤", errorMessage);
//
//                    }
//                });
//
//    }


//    private <O> void takePictureResult(O o) {
//    }
//
//    private <O> void pickPictureResult(O o) {
//    }
//
//    private <O> void cropPictureResult(O o) {
//    }
//}