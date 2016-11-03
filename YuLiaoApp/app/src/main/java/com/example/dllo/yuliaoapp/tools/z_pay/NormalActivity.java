package com.example.dllo.yuliaoapp.tools.z_pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.fuqianla.paysdk.FuQianLa;
import com.fuqianla.paysdk.FuQianLaPay;
import com.fuqianla.paysdk.bean.FuQianLaResult;

/**
 * @author 赵玲琳
 * 订单页面
 */
public class NormalActivity extends Activity implements View.OnClickListener {

    private EditText etCommodity, etDetails, etAmount;

    private TextView tvCommodity,tvDetails;
    private Button btnPay;

    private String jdToken = "";

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_activity_normal);

//        etCommodity = (EditText) findViewById(R.id.et_commodity);
//        etDetails = (EditText) findViewById(R.id.et_details);
        etAmount = (EditText) findViewById(R.id.et_amount);
        btnPay = (Button) findViewById(R.id.btn_pay);
        tvCommodity = (TextView) findViewById(R.id.tv_commodity);
        tvDetails = (TextView) findViewById(R.id.tv_details);
        btnPay.setOnClickListener(this);

        etAmount.setText("0.01");


        myGestureListener();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pay:

                String subject = tvCommodity.getText().toString();
                String amount = etAmount.getText().toString();
                String body = tvDetails.getText().toString();

                if (TextUtils.isEmpty(subject)
                        || TextUtils.isEmpty(amount)
                        || TextUtils.isEmpty(body))
                    return;


                //支付核心代码
                FuQianLaPay pay = new FuQianLaPay.Builder(this)
                        .partner(Merchant.MERCHANT_NO)//商户号
                        .alipay(true)
                        .wxpay(true)
                        .baidupay(true)
                        .unionpay(true,2)
                        .jdpay(true, 1)
                        .orderID(OrderUtils.getOutTradeNo())//订单号
                        .amount(Double.parseDouble(amount))//金额
                        .subject(subject)//商品名称
                        .body(body)//商品交易详情
                        .notifyUrl(Merchant.MERCHANT_NOTIFY_URL)
                        .build();
                pay.startPay();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //返回结果
        if (requestCode == FuQianLa.REQUESTCODE
                && resultCode == FuQianLa.RESULTCODE
                && data != null) {
            FuQianLaResult result = data.getParcelableExtra(FuQianLa.PAYRESULT_KEY);
            Toast.makeText(getApplicationContext(), result.payCode, Toast.LENGTH_SHORT).show();
            if (FuQianLa.JD.equals(result.payChannel)) {
                jdToken = result.payMessage;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void myGestureListener() {
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d("NormalActivity", "e2.getX()-e1.getX():" + (e2.getX() - e1.getX()));
                if (e2.getX() - e1.getX()>50){
                    finish();
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e2.getX() - e1.getX()>50){
                    finish();
                }
                return true;
            }
        });
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

}
