package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;

import idv.tfp10207.nowclearnnow0818.R;

/**
 * A simple {@link Fragment} subclass.
<<<<<<< Updated upstream:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/MarketHomeFragment.java
 * Use the {@link idv.tfp10207.nowclearnnow0818.MarketHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketHomeFragment extends Fragment {
=======
 * Use the {@link reserve_02_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reserve_02_Fragment extends Fragment {
>>>>>>> Stashed changes:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/reserve_02_Fragment.java

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

<<<<<<< Updated upstream:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/idv.tfp10207.nowclearnnow0818.MarketHomeFragment.java
    public MarketHomeFragment() {
=======
    public reserve_02_Fragment() {
>>>>>>> Stashed changes:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/reserve_02_Fragment.java
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
<<<<<<< Updated upstream:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/MarketHomeFragment.java
     * @return A new instance of fragment MarketHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static idv.tfp10207.nowclearnnow0818.MarketHomeFragment newInstance(String param1, String param2) {
        idv.tfp10207.nowclearnnow0818.MarketHomeFragment fragment = new idv.tfp10207.nowclearnnow0818.MarketHomeFragment();
=======
     * @return A new instance of fragment reserve_02_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static reserve_02_Fragment newInstance(String param1, String param2) {
        reserve_02_Fragment fragment = new reserve_02_Fragment();
>>>>>>> Stashed changes:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/reserve_02_Fragment.java
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< Updated upstream:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/ idv.tfp10207.nowclearnnow0818.MarketHomeFragment.java
        return inflater.inflate(R.layout.fragment_market_home, container, false);
=======
        return inflater.inflate(R.layout.fragment_reserve_02_, container, false);
>>>>>>> Stashed changes:app/src/main/java/idv/tfp10207/nowclearnnow0818/market/reserve_02_Fragment.java
    }
}