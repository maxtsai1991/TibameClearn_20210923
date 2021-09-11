package idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;


public class ReserveOrderStateApplyFragment extends Fragment {
    private static final String TAG = "TAG_OrderStateApply";
    private AppCompatActivity activity;
    private FirebaseFirestore db;

    private FirebaseStorage storage;

    private RecyclerView rv_reserveorder_apply_11;
    private List<Cpreserveorder> cpreserveorders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        cpreserveorders = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reserve_order_state_apply, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        showAllOrders();

    }

    private void findViews(View view) {
        rv_reserveorder_apply_11 = view.findViewById(R.id.rv_reserveorder_apply_11);
    }
    /** 取得所有訂單資訊後顯示 */
    private void showAllOrders() {
        db.collection("cleanplanorder")
                .whereEqualTo("cporderstate", "0")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // 先清除舊資料後再儲存新資料
                        if (!cpreserveorders.isEmpty()) {
                            cpreserveorders.clear();
                        }
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            cpreserveorders.add(document.toObject(Cpreserveorder.class));
                        }
                        // 顯示訂單
                        showOrders();
                    } else {
                        String message = task.getException() == null ?
                                "Not Found" :
                                task.getException().getMessage();
                        Log.e(TAG, "exception message: " + message);
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showOrders() {
        ReserveOrderStateApplyFragment.OrderAdapter orderAdapter = (ReserveOrderStateApplyFragment.OrderAdapter) rv_reserveorder_apply_11.getAdapter();
        if (orderAdapter == null) {
            orderAdapter = new ReserveOrderStateApplyFragment.OrderAdapter();
            rv_reserveorder_apply_11.setAdapter(orderAdapter);
        }
        rv_reserveorder_apply_11.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderAdapter.setOrders(cpreserveorders);
    }



    private class OrderAdapter extends RecyclerView.Adapter<ReserveOrderStateApplyFragment.OrderAdapter.MyOrderViewHolder> {
        private List<Cpreserveorder> cpreserveorders;

        public OrderAdapter() {

        }

        public void setOrders(List<Cpreserveorder> cpreserveorders) {
            this.cpreserveorders = cpreserveorders;
        }

          class MyOrderViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_reserve_image_11;
            TextView tv_reserve_date_11;
            TextView tv_reserve_time_11;
            TextView tv_reserve_name_11;
            TextView tv_reserve_title_number;
            TextView tv_reserve_name_2;

            public MyOrderViewHolder(View itemView) {
                super(itemView);
                iv_reserve_image_11 = itemView.findViewById(R.id.iv_reserve_image_11);
                tv_reserve_date_11 = itemView.findViewById(R.id.tv_reserve_date_11);
                tv_reserve_time_11 = itemView.findViewById(R.id.tv_reserve_time_11);
                tv_reserve_name_11 = itemView.findViewById(R.id.tv_reserve_name_11);
                tv_reserve_name_2 = itemView.findViewById(R.id.tv_reserve_name_2);
                tv_reserve_title_number = itemView.findViewById(R.id.tv_reserve_title_number);
            }
        }

        @NonNull

        @Override
        public ReserveOrderStateApplyFragment.OrderAdapter.MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_itemcard_reserve, parent, false);
            return new ReserveOrderStateApplyFragment.OrderAdapter.MyOrderViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReserveOrderStateApplyFragment.OrderAdapter.MyOrderViewHolder holder, int position) {
            final Cpreserveorder cpreserveorder = cpreserveorders.get(position);
            //            圖片路徑是空值(需多加一個判斷為cpreserveorder.getPicture().equals("null")，主要是當firebaseg上沒有圖片時下載下來會是文字的null，如此是字串null而非空值)，，給一個沒有圖片的圖
            if (cpreserveorder.getPicture() == null || cpreserveorder.getPicture().equals("null")) {
                holder.iv_reserve_image_11.setImageResource(R.drawable.no_image);
            } else {
//                有圖片路徑就呼叫showImage
                showImage(holder.iv_reserve_image_11, cpreserveorder.getPicture());
            }

            holder.tv_reserve_date_11.setText("預約日期： "+cpreserveorder.getOnedate() );
            holder.tv_reserve_time_11.setText("預約時間： " + cpreserveorder.getTime()+" 9:00~12:00");
            holder.tv_reserve_name_11.setText("家事者姓名： " + cpreserveorder.getCpservice());
            holder.tv_reserve_name_2.setText("服務對象姓名："+cpreserveorder.getServicename());
            holder.tv_reserve_title_number.setText(cpreserveorder.getCpordernumber());


            // 點選會開啟修改頁面
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cpreserveorders", cpreserveorder);
                Navigation.findNavController(v)
                        .navigate(R.id.f_CleanPlan_05_Fragment, bundle);
//                reserveOrderContantFragment
            });
        }

        @Override
        public int getItemCount() {
            return cpreserveorders == null ? 0 : cpreserveorders.size();
        }
    }



    // 下載Firebase storage的照片並顯示在ImageView上
    private void showImage(final ImageView iv_reserve_image_11, final String path) {
        final int ONE_MEGABYTE = 1024 * 1024;
        StorageReference imageRef = storage.getReference().child(path);
//        對應的路徑下載相應的圖檔，傳進來
        imageRef.getBytes(ONE_MEGABYTE)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        byte[] bytes = task.getResult();
//                        轉成bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                        顯示在imageView
                        iv_reserve_image_11.setImageBitmap(bitmap);
                    } else {
                        String message = task.getException() == null ?
                                getString(R.string.textImageDownloadFail) + ": " + path :
                                task.getException().getMessage() + ": " + path;
                        Log.e(TAG, message);
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                })
//                關閉掉當圖片下在失敗時的TOAST
                .addOnFailureListener(Throwable::printStackTrace);
    }

}