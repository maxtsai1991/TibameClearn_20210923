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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
    private EditText etpdname, etdetail, etquantity, etprice, etproduce;
    private ImageView imageView, imageView21;
    private List<Uri> imageUris;
    private TextView textView3, textView5;
    /**
     * 取得圖片
     */
    private ActivityResultLauncher<String> launcher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            result -> {
                Uri uri = result;
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
    );

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
        handleimage();
        handlesend();

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
        imageView21 = view.findViewById(R.id.imageView21);
        textView3 = view.findViewById(R.id.textView3);
        textView5 = view.findViewById(R.id.textView5);
    }


    private void handlebutton() {
        button.setOnClickListener(view -> {

            launcher.launch("image/*");
        });
        textView3.setOnClickListener(v -> {
            etproduce.setText("妙管家地板清潔劑");
            etprice.setText("15");
            etquantity.setText("15");
            etdetail.setText("輕鬆瓦解頑強汙垢，多表面適用");
            etpdname.setText("高樂氏");
        });
        textView5.setOnClickListener(v -> {
            etproduce.setText("高樂氏廚房清潔劑");
            etprice.setText("10");
            etquantity.setText("11");
            etdetail.setText("輕鬆瓦解頑強汙垢，多表面適用");
            etpdname.setText("高樂氏");
        });

    }

    private void handlesend() {
        button4.setOnClickListener(v -> {
            Toast.makeText(activity, "商品已上架", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(v)
                    .navigate(R.id.addDelCommodityFragment);
        });
        button5.setOnClickListener(v -> {

            Navigation.findNavController(v)
                    .navigate(R.id.addDelCommodityFragment);
        });

    }

    private void handleimage() {
        imageView21.setOnClickListener(v -> {

            Navigation.findNavController(v)
                    .navigate(R.id.addDelCommodityFragment);
        });

    }
    /**
     * 5. 檢查是否有內建的相機App
     */
    private boolean isIntentAvailable(Intent intent) {
        PackageManager packageManager = activity.getPackageManager();
        return intent.resolveActivity(packageManager) != null;
    }
}