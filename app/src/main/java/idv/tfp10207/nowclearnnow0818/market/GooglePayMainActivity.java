package idv.tfp10207.nowclearnnow0818.market;

//import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.TransactionInfo;
import com.google.android.gms.wallet.WalletConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.network.RemoteAccess_05;
import tech.cherri.tpdirect.api.TPDCard;
import tech.cherri.tpdirect.api.TPDConsumer;
import tech.cherri.tpdirect.api.TPDGooglePay;
import tech.cherri.tpdirect.api.TPDMerchant;
import tech.cherri.tpdirect.api.TPDServerType;
import tech.cherri.tpdirect.api.TPDSetup;

public class GooglePayMainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_GooglePay";
    public static final String TAPPAY_DOMAIN_SANDBOX = "https://sandbox.tappaysdk.com/";
    public static final String TAPPAY_PAY_BY_PRIME_URL = "tpc/payment/pay-by-prime";
    public static final TPDCard.CardType[] CARD_TYPES = new TPDCard.CardType[]{  //信用卡種類
            TPDCard.CardType.Visa
            , TPDCard.CardType.MasterCard
            , TPDCard.CardType.JCB
            , TPDCard.CardType.AmericanExpress
    };
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 101;
    private TPDGooglePay tpdGooglePay;
    private RelativeLayout bt_cl_GooglePayMainBuyWithPay_05;
    private TextView cl_GooglePayMainResult_05;
    private TextView tv_cl_GooglePayMainCardNo_05;
    private PaymentData paymentData;
    private Button bt_cl_GooglePayMainBuy_05;

    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_pay_main);

        handleViews();

        Log.d(TAG, "SDK version is " + TPDSetup.getVersion());

        // 使用TPDSetup設定環境。每個設定值出處參看strings.xml
        TPDSetup.initInstance(this,   //使始初化，在文字檔的TapPay的id跟 appkey
                Integer.parseInt(getString(R.string.TapPay_AppID)),
                getString(R.string.TapPay_AppKey),
                TPDServerType.Sandbox); //Sandbox 測試版

        prepareGooglePay(); //準備執行 google pay  做某些事情檢查，讓 buy with pay按鍵可以按
    }

    public void prepareGooglePay() {
        TPDMerchant tpdMerchant = new TPDMerchant();
        // 設定商店名稱 (信用卡發行單位)
        tpdMerchant.setMerchantName(getString(R.string.TapPay_MerchantName));
        // 設定允許的信用卡種類
        tpdMerchant.setSupportedNetworks(CARD_TYPES);
        // 設定客戶填寫項目
        TPDConsumer tpdConsumer = new TPDConsumer();
        // 不需要電話號碼
        tpdConsumer.setPhoneNumberRequired(false);
        // 不需要運送地址
        tpdConsumer.setShippingAddressRequired(false);
        // 不需要Email
        tpdConsumer.setEmailRequired(false);

        tpdGooglePay = new TPDGooglePay(this, tpdMerchant, tpdConsumer); //建立google pay物件
        // 檢查user裝置是否支援Google Pay //交給google pay檢查，可不可以支援
        tpdGooglePay.isGooglePayAvailable((isReadyToPay, msg) -> { //監聽回來的結果跟訊息
            Log.d(TAG, "Pay with Google availability : " + isReadyToPay);
            if (isReadyToPay) { //檢測成功，按鈕可以按
                bt_cl_GooglePayMainBuyWithPay_05.setEnabled(true);
            } else {
                bt_cl_GooglePayMainBuyWithPay_05.setEnabled(false);
                cl_GooglePayMainResult_05.setText("不能使用 Google Pay");
            }
        });
    }

    private void handleViews() {
        bt_cl_GooglePayMainBuyWithPay_05 = findViewById(R.id.bt_cl_GooglePayMainBuyWithPay_05); //Buy with Pay按鈕
        // 先將Google Pay按鈕設定為不可按，
        // 直到等會執行prepareGooglePay()確定可使用時再改成可按
        bt_cl_GooglePayMainBuyWithPay_05.setEnabled(false); //設定開始不能按，確定可以使用google pay,才可以做執行
        bt_cl_GooglePayMainBuyWithPay_05.setOnClickListener(v -> {
            // 跳出user資訊視窗讓user確認，確認後會呼叫onActivityResult()
            tpdGooglePay.requestPayment(TransactionInfo.newBuilder() //請求支付 requestPayment
                    .setTotalPriceStatus(WalletConstants.TOTAL_PRICE_STATUS_FINAL)
                    // 消費總金額
                    .setTotalPrice("10")
                    // 設定幣別
                    .setCurrencyCode("TWD")
                    .build(), LOAD_PAYMENT_DATA_REQUEST_CODE); //請求代碼 LOAD_PAYMENT_DATA_REQUEST_CODE
        });
        tv_cl_GooglePayMainCardNo_05 = findViewById(R.id.tv_cl_GooglePayMainCardNo_05);
        bt_cl_GooglePayMainBuy_05 = findViewById(R.id.bt_cl_GooglePayMainBuy_05);
        bt_cl_GooglePayMainBuy_05.setEnabled(false); //確認付款 一開始也設false
        bt_cl_GooglePayMainBuy_05.setOnClickListener(v -> getPrimeFromTapPay(paymentData)); // paymentData 是按下繼續，支付跟信用卡資訊送到googleserver，取得paymentdata回來
        cl_GooglePayMainResult_05 = findViewById(R.id.cl_GooglePayMainResult_05);
    }

    @Override //彈出視窗按繼續，關掉視窗，會到此方法
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOAD_PAYMENT_DATA_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK: //按下彈跳的繼續
                    bt_cl_GooglePayMainBuy_05.setEnabled(true); //確認付款會亮，可以按
                    // 取得支付資訊
                    paymentData = PaymentData.getFromIntent(data); //data 為上面設定的資訊，金額、幣別等
                    if (paymentData != null) {
                        // 取得支付資訊中的信用卡資訊並顯示
                        showCardInfo(paymentData); //顯示卡號
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    bt_cl_GooglePayMainBuy_05.setEnabled(false);
                    cl_GooglePayMainResult_05.setText("取消");
                    break;
                case AutoResolveHelper.RESULT_ERROR:
                    bt_cl_GooglePayMainBuy_05.setEnabled(false);
                    Status status = AutoResolveHelper.getStatusFromIntent(data); //data 支付資訊
                    if (status != null) { //不是空值，系統知道什麼錯，可以顯示錯誤訊息
                        String text = "status code: " + status.getStatusCode() +
                                " , message: " + status.getStatusMessage();
                        Log.d(TAG, text);
                        cl_GooglePayMainResult_05.setText(text);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 只取得支付資訊當中的信用卡資訊並顯示
     */
    private void showCardInfo(PaymentData paymentData) {
        Gson gson = new Gson();
        JsonObject paymentDataJO = gson.fromJson(paymentData.toJson(), JsonObject.class);
        Log.d(TAG, "paymentDataJO: ");
        String cardDescription = paymentDataJO.get("paymentMethodData").getAsJsonObject() //有好幾層，先到"paymentMethodData" KEY值，再進到 "description") KEY值取信用卡號
                .get("description").getAsString();
        tv_cl_GooglePayMainCardNo_05.setText(cardDescription);
    }

    private void getPrimeFromTapPay(PaymentData paymentData) {
        showProgressDialog();
        /* 呼叫getPrime()只將支付資料提交給TapPay以取得prime (代替卡片的一次性字串，此字串的時效為 30 秒)，
            參看https://docs.tappaysdk.com/google-pay/zh/reference.html#prime */
        /* 一般而言，手機提交支付、信用卡資料給TapPay後，TapPay會將信用卡等資訊送至Bank確認是否合法，
               Bank會回一個暫時編號給TapPay方便後續支付確認，TapPay儲存該編號後再回一個自編prime給手機，
               手機再傳給server，server再呼叫payByPrime方法提交給TapPay，以確認這筆訂單，
               此時server就可發訊息告訴user訂單成立。
               參看圖示 https://docs.tappaysdk.com/google-pay/zh/home.html#home 向下捲動即可看到 */
        tpdGooglePay.getPrime( //tpdGooglePay  tpd for google pay 的api
                paymentData,
                (prime, tpdCardInfo) -> {   //收到第一次送過去回來的prime，成功
                    hideProgressDialog();

//                    String text = "Your prime is " + prime + "\n\n"
                            /* 手機得到prime後，一般會傳給商家server端再呼叫payByPrime方法提交給TapPay，以確認這筆訂單
                               此範例是現在為了方便，手機直接提交給TapPay */  //正常流程是要手機將訂單資料跟prime送至server儲存訂單資料，再由server送去 tappay確認訂單消費(在tappay有設定server的IP)，再送至銀行進行扣款
                            /*+*/ generatePayByPrimeForSandBox(prime,  //generatePayByPrimeForSandBox : 把得到的prime(第一次詢問交易成功)和以下資訊，再送至tappay
                            getString(R.string.TapPay_PartnerKey),
                            getString(R.string.TapPay_MerchantID));
//                    Log.d(TAG, text);
//                    cl_GooglePayMainResult_05.setText(text);

                    //todo 回傳前頁fragment內容
                    //要將Intent物件放在setResult()內方能回傳
                    Intent intent = getIntent();
                    intent.putExtra("googlepay", "success");
                    setResult(RESULT_OK, intent); //確認付款成功，回傳前一頁fragment
                    finish();


                },
                (status, reportMsg) -> { //收到第一次送過去回來的prime，失敗
                    hideProgressDialog();

                    //todo 回傳前頁fragment內容
                    //要將Intent物件放在setResult()內方能回傳
                    Intent intent = getIntent();
                    intent.putExtra("googlepay", "fail");
                    setResult(0, intent); //確認付款成功，回傳前一頁fragment
                    finish();

//                    String text = "TapPay getPrime failed. status: " + status + ", message: " + reportMsg;
//                    Log.d(TAG, text);
//                    cl_GooglePayMainResult_05.setText(text);
                });
    }

    // 將交易資訊送至TapPay測試區 //第二次送更詳細的資訊回TapPay
    public static String generatePayByPrimeForSandBox(String prime, String partnerKey, String merchantId) {
        JsonObject paymentJO = new JsonObject(); //送給TapPay為json資訊
        paymentJO.addProperty("partner_key", partnerKey);
        paymentJO.addProperty("prime", prime); //再送回prime(當初審核通過的資訊，有30秒限制)回去給TP
        paymentJO.addProperty("merchant_id", merchantId);
        paymentJO.addProperty("amount", 1);
        paymentJO.addProperty("currency", "TWD");
        paymentJO.addProperty("order_number", "TEST001"); //自已設的訂單編號
        paymentJO.addProperty("details", "蘋果1顆");

        JsonObject cardHolderJO = new JsonObject(); //填入信用卡資訊，按官方說明，下方目前先隨意填，但真實要給user填(為必填，安全機制)，但好像沒檢查合不合法
        cardHolderJO.addProperty("name", "Ron");
        cardHolderJO.addProperty("phone_number", "+886912345678");
        cardHolderJO.addProperty("email", "ron@email.com");

        paymentJO.add("cardholder", cardHolderJO); //為json object資訊 //在paymentJO裡的物件，再加入一個cardHolderJO物件

        // TapPay測試區網址
        String url = TAPPAY_DOMAIN_SANDBOX + TAPPAY_PAY_BY_PRIME_URL;
        return RemoteAccess_05.getRemoteData(url, paymentJO.toString(), partnerKey); //由手機送至tp  //要自已開新的執行緒將資訊送至tp
    }

    public ProgressDialog mProgressDialog;

    protected void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }








}