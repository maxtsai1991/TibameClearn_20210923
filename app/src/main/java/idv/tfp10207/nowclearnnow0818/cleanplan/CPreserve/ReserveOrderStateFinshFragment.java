package idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve;

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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;

public class ReserveOrderStateFinshFragment extends Fragment {
    private static final String TAG = "TAG_OrderStateFinsh";
    private AppCompatActivity activity;
    private FirebaseFirestore db;

    private RecyclerView rv_reserveorder_finsh_11;
    private List<Cpreserveorder> cpreserveorders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        db = FirebaseFirestore.getInstance();
        cpreserveorders = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reserve_order_state_finsh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        showAllOrders();
    }

    private void findViews(View view) {
        rv_reserveorder_finsh_11 = view.findViewById(R.id.rv_reserveorder_finsh_11);
    }

    /** 取得所有訂單資訊後顯示 */
    private void showAllOrders() {
        db.collection("cleanplanorder")
                .whereEqualTo("cporderstate", "011")
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
        ReserveOrderStateFinshFragment.OrderAdapter orderAdapter = (ReserveOrderStateFinshFragment.OrderAdapter) rv_reserveorder_finsh_11.getAdapter();
        if (orderAdapter == null) {
            orderAdapter = new ReserveOrderStateFinshFragment.OrderAdapter();
            rv_reserveorder_finsh_11.setAdapter(orderAdapter);
        }
        rv_reserveorder_finsh_11.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderAdapter.setOrders(cpreserveorders);
    }


    private static class OrderAdapter extends RecyclerView.Adapter<ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder> {
        private List<Cpreserveorder> cpreserveorders;

        public OrderAdapter() {

        }

        public void setOrders(List<Cpreserveorder> cpreserveorders) {
            this.cpreserveorders = cpreserveorders;
        }

        private static class MyOrderViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_reserve_image_11;
            TextView tv_reserve_date_11;
            TextView tv_reserve_time_11;
            TextView tv_reserve_name_11;

            public MyOrderViewHolder(View itemView) {
                super(itemView);
                iv_reserve_image_11 = itemView.findViewById(R.id.iv_reserve_image_11);
                tv_reserve_date_11 = itemView.findViewById(R.id.tv_reserve_date_11);
                tv_reserve_time_11 = itemView.findViewById(R.id.tv_reserve_time_11);
                tv_reserve_name_11 = itemView.findViewById(R.id.tv_reserve_name_11);
            }
        }

        @Override
        public ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_itemcard_reserve, parent, false);
            return new ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder holder, int position) {
            final Cpreserveorder cpreserveorder = cpreserveorders.get(position);
            holder.tv_reserve_date_11.setText("預約日期： "+cpreserveorder.getOnedate() );
            holder.tv_reserve_time_11.setText("預約時間： " + cpreserveorder.getTime()+" 9:00~12:00");
            holder.tv_reserve_name_11.setText("家事者姓名： " + cpreserveorder.getCpservice());


            // 點選會開啟修改頁面
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cpreserveorders", cpreserveorder);
                Navigation.findNavController(v)
                        .navigate(R.id.f_CleanPlan_07_Fragment, bundle);
//                reserveOrderContantFragment
            });
        }

        @Override
        public int getItemCount() {
            return cpreserveorders == null ? 0 : cpreserveorders.size();
        }
    }

}