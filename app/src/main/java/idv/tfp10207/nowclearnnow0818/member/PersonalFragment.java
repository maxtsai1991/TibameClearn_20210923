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

import idv.tfp10207.nowclearnnow0818.R;


public class PersonalFragment extends Fragment {
    private Activity activity;
    //    private Uri contentUri;
//    private Member member;
    //元件
    private ImageView imageView, iv_clear14_01, iv_back_01, iv_clear15_01;
    private TextView tv_store_01, tv_clear49_01;
    private EditText edt_name_01, edt_email_01, edt_phone_01, edt_address_01;
    private Spinner sp_gender_01;
//    //判斷點擊哪個按鈕
//    private int tv_store_01Click = 1; // 1->完成(編輯資料)
////    private int btPIApplyClick = 0;  // 0->申請成房東 1->取消
//    //判斷上傳點擊哪個按鈕
//    private boolean HSisClick=false;
//    private boolean GPisClick=false;
//    //判斷是否有成功上傳
//    private boolean upNewHS=false;
//    private boolean upNewGP=false;
//    private SimpleDateFormat sdf;
//    private FirebaseStorage storage;
//    private String picUri; //回傳路徑用
//    private ByteArrayOutputStream baos; //上傳用
//    private String serverresp;
//    private SharedPreferences sharedPreferences;
//    private final String url = Common.URL + "memberCenterPersonalInformation";
//
//    ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            this::takePictureResult);
//
//    ActivityResultLauncher<Intent> pickPictureLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            this::pickPictureResult);
//
//    ActivityResultLauncher<Intent> cropPictureLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            this::cropPictureResult);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
//        storage = FirebaseStorage.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        // 指定拍照存檔路徑
//        File file = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        file = new File(file, "picture.jpg");
//        contentUri = FileProvider.getUriForFile(
//                activity, activity.getPackageName() + ".fileProvider", file);
//        sharedPreferences = activity.getSharedPreferences( "SharedPreferences", Context.MODE_PRIVATE);
//        //跟後端提出請求
//        JsonObject clientreq = new JsonObject();
//        clientreq.addProperty("action", "getMember");
//        int memberId=sharedPreferences.getInt("memberId",-1);
//        clientreq.addProperty("member_id",memberId);
//        serverresp = RemoteAccess.getJsonData(url, clientreq.toString());
        findViews(view);
        handleToolbar(view);
        handleImageView();
        handleSpinner();
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
            Navigation.findNavController(view).navigate(R.id.memberCentreFragment);
            Toast.makeText(activity, "變更成功", Toast.LENGTH_SHORT).show();
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