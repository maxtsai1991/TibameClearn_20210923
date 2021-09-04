package idv.tfp10207.nowclearnnow0818.mycommodity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;

import static android.app.Activity.RESULT_OK;
import static idv.tfp10207.nowclearnnow0818.mycommodity.Addproduce.DETAIL;
import static idv.tfp10207.nowclearnnow0818.mycommodity.Addproduce.PRICE;
import static idv.tfp10207.nowclearnnow0818.mycommodity.Addproduce.PRODUCE;
import static idv.tfp10207.nowclearnnow0818.mycommodity.Addproduce.PRODUECNAME;
import static idv.tfp10207.nowclearnnow0818.mycommodity.Addproduce.QUANTITY;


public class ProduceFragment extends Fragment {
    private static final String TAG = "TAG_ProduceFragment";
    private Activity activity;
    private Button button, button4, button5;
   // private TextView tvproduce,tvprice,tvquantity,tvdetail,tvpdname;
    private EditText etpdname, etdetail, etquantity,etprice,etproduce;
    private ImageView imageView,imageView2,imageView3;
    private List<Uri> imageUris;

    ActivityResultLauncher<Intent> pickPicturesLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            this::pickPicturesResult);

    private void pickPicturesResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Intent intent = result.getData();
            // null代表只挑選一張，不為null代表挑選多張圖片
            if (intent.getClipData() != null) {
                int count = intent.getClipData().getItemCount();
                Log.d(TAG, "count: " + count);
                for (int i = 0; i < count; i++) {
                    Uri imageUri = intent.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        imageUris = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_produce, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handlebutton();

    }
    private void findViews(View view) {
        button = view.findViewById(R.id.button);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        etpdname = view.findViewById(R.id.etpdname);
        etdetail = view.findViewById(R.id.etdetail);
        etprice = view.findViewById(R.id.etprice);
        etproduce = view.findViewById(R.id.etproduce);
        etquantity = view.findViewById(R.id.etquantity);
        imageView = view.findViewById(R.id.imageView);
//        imageView2 = view.findViewById(R.id.imageView2);
//        imageView3 = view.findViewById(R.id.imageView3);


    }

    private void handlebutton() {
      button.setOnClickListener(view -> {
          final String produce = String.valueOf(etpdname.getText());
          final String price = String.valueOf(etprice.getText());
          final String quantity = String.valueOf(etquantity.getText());
          final String detail = String.valueOf(etdetail.getText());
          final String producename = String.valueOf(etpdname.getText());
          // 實例化Bundle物件
          Bundle bundle = new Bundle();
          // 放入資料
          bundle.putString(PRODUCE, produce);
          bundle.putString(PRICE, price);
          bundle.putString(QUANTITY, quantity);
          bundle.putString(DETAIL, detail);
          bundle.putString(PRODUECNAME, producename);
          // 3. 實例化Intent物件
          Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          // 必須指定挑選格式，否則無法執行挑選動作
          intent.setType("image/*");
          // 允許挑選多個項目
          intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
          // 允許用戶選取指定項目(例如：照片)，選完後會回傳
          intent.setAction(Intent.ACTION_GET_CONTENT);

          // 4. 檢查是否有內建的相機App
          if (isIntentAvailable(intent)) {
              // 5. 跳轉至內建相簿App
              startActivityForResult(intent, 1);
          } else {
              Toast.makeText(activity, "hhh", Toast.LENGTH_SHORT).show();
          }


      });

    }
    /**
     * 5. 檢查是否有內建的相機App
     */
    private boolean isIntentAvailable(Intent intent) {
        PackageManager packageManager = activity.getPackageManager();
        return intent.resolveActivity(packageManager) != null;
    }
    /**
     * 6. 取得圖片
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            // 6.1 取得Uri物件
            Uri uri = data.getData();
            try {
                Bitmap bitmap = null;
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.P) {
                    // 6.2.a Android 9-
                    // 6.2.a.1 取得InputStream物件
                    InputStream is = activity.getContentResolver().openInputStream(uri);
                    // 6.2.a.2 取得Bitmap物件
                    bitmap = BitmapFactory.decodeStream(is);

                } else {
                    // 6.2.b Android 9(+
                    // 6.2.b.1 從Uri物件建立ImageDecoder.Source物件
                    ImageDecoder.Source source = ImageDecoder.createSource(
                            activity.getContentResolver(),
                            uri);
                    // 6.2.b.2 取得Bitmap物件
                    bitmap = ImageDecoder.decodeBitmap(source);
                }
                // TODO
                imageView.setImageBitmap(bitmap);
//                imageView2.setImageBitmap(bitmap);
//                imageView3.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }
        }
    }
}