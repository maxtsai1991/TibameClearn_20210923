package idv.tfp10207.nowclearnnow0818.mycommodity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;


public class MyProduceFragment extends Fragment {
    Activity activity;
    private RecyclerView recycler_view;
    private MyAdapter adapter;
    private ArrayList<String> mData = new ArrayList<>();
    private ImageView imageView20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_produce, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleimage();

        // 準備資料，塞50個項目到ArrayList裡
        for(int i = 0; i < 10; i++) {
            mData.add("項目"+i);
        }

        // 連結元件
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(activity));
        // 設置格線
        recycler_view.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

        // 將資料交給adapter
        adapter = new MyAdapter(getProduceList());
        // 設置adapter給recycler_view
        recycler_view.setAdapter(adapter);
    }

    private void handleimage() {
        imageView20.setOnClickListener(v -> {

            Navigation.findNavController(v)
                    .navigate(R.id.addDelCommodityFragment);
        });
    }

    private void findViews(View view) {
        imageView20 = view.findViewById(R.id.imageView20);
    }



    private List<Produce> getProduceList() {
        ArrayList<Produce> list = new ArrayList<>();
        list.add(new Produce(R.drawable.m2, "高樂氏廚房清潔劑"));
        list.add(new Produce(R.drawable.m3, "妙管家地板清潔劑"));
        list.add(new Produce(R.drawable.m1, "高樂氏去汙清潔劑"));
        list.add(new Produce(R.drawable.m4, "妙管家窗戶清潔劑"));
        list.add(new Produce(R.drawable.m5, "魔術靈玻璃清潔劑"));


        return list;
    }
}