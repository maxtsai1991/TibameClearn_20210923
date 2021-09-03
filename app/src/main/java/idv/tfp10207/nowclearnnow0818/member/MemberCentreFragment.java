package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.bean.Material;

public class MemberCentreFragment extends Fragment {
    private Activity activity;
    private ImageView iv_clear1_01, iv_clear2_01, iv_clear3_01, iv_clear4_01, iv_clear5_01, iv_clear6_01;
    private ImageView iv_home_01, iv_question_01 ;
    private TextView tv_clear8_01, tv_clear1_01;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private List<Material> materials;
    String str1 = "一般會員";
    String str2 = "家事者";





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();

        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_member_centre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleImageView();
        handleTextview();
    }

    private void findViews(View view) {
        iv_clear1_01 = view.findViewById(R.id.iv_clear1_01);
        iv_clear2_01 = view.findViewById(R.id.iv_clear2_01);
        iv_clear3_01 = view.findViewById(R.id.iv_clear3_01);
        iv_clear4_01 = view.findViewById(R.id.iv_clear4_01);
        iv_clear5_01 = view.findViewById(R.id.iv_clear5_01);
        iv_clear6_01 = view.findViewById(R.id.iv_clear6_01);
        iv_home_01 = view.findViewById(R.id.iv_home_01);
        tv_clear8_01 = view.findViewById(R.id.tv_clear8_01);
        iv_question_01 = view.findViewById(R.id.iv_question_01);
        iv_clear2_01 = view.findViewById(R.id.iv_clear2_01);
        tv_clear8_01 = view.findViewById(R.id.tv_clear8_01);
        tv_clear1_01 = view.findViewById(R.id.tv_clear1_01);

    }

    private void handleTextview() {
        tv_clear1_01.setText(str1);//設置原来的文本
        tv_clear1_01.setTag(false);//标记textview为false（表示没有被点击过）
        tv_clear8_01.setOnClickListener(v -> {
            boolean flag = (boolean) tv_clear1_01.getTag();//当点击时，首先判断是否已经点击过
            if (!flag){
                tv_clear1_01.setText(str2);
                tv_clear1_01.setTag(true);
            }else {//已经点击过了
                tv_clear1_01.setText(str1);
                tv_clear1_01.setTag(false);
            }

        });
    }

    private void handleImageView() {
        iv_clear1_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.personalFragment);
        });
        iv_clear4_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.orderFragment);
        });
        iv_clear5_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.success_get_Fragment);
        });
        iv_clear6_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.evaluationFragment);
        });
        iv_question_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.QAFragment);
        });
        iv_home_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.homePageFragment072);
        });
        iv_clear2_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.addDelCommodityFragment);
        });
        }
//    public void onBindViewHolder(@NonNull SpotViewHolder holder, int position) {
//        final Spot spot = spots.get(position);
//        if (spot.getImagePath() == null) {
//            holder.ivSpot.setImageResource(R.drawable.no_image);
//        } else {
//            showImage(holder.ivSpot, spot.getImagePath());
//        }
//        holder.tvName.setText(spot.getName());
//        holder.tvPhone.setText(spot.getPhone());
//        holder.tvAddress.setText(spot.getAddress());
//        holder.tvWeb.setText(spot.getWeb());
//
//        // 點選會開啟修改頁面
//        holder.itemView.setOnClickListener(v -> {
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("spot", spot);
//            Navigation.findNavController(v)
//                    .navigate(R.id.action_spotListFragment_to_spotUpdateFragment, bundle);
//        });
    }