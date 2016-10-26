package com.example.dllo.yuliaoapp.tools.z_pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.fuqianla.paysdk.FuQianLa;
import com.fuqianla.paysdk.FuQianLaPay;
import com.fuqianla.paysdk.bean.FuQianLaResult;

/**
 * Created by siqi on 16/3/31.
 * <p>
 * 商户APP根据自身需要，可自定义创建交易通道(Activity样式)
 *支付平台的界面
 * @author siqi
 */
public class CustomPayActivity extends Activity implements View.OnClickListener {

    private FuQianLaPay pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_activity_custom);

        Button btnAli = (Button) findViewById(R.id.btn_ali);
        Button btnWX = (Button) findViewById(R.id.btn_wx);
        Button btnBD = (Button) findViewById(R.id.btn_bd);
        Button btnUnionPay = (Button) findViewById(R.id.btn_up);
        Button btnJD = (Button) findViewById(R.id.btn_jd);

        btnAli.setOnClickListener(this);
        btnWX.setOnClickListener(this);
        btnBD.setOnClickListener(this);
        btnUnionPay.setOnClickListener(this);
        btnJD.setOnClickListener(this);

        pay = new FuQianLaPay.Builder(this)
                .promode(true)//通道前置模式
                .partner(Merchant.MERCHANT_NO)//商户号
                .orderID(OrderUtils.getOutTradeNo())//订单号
                .amount(0.01)//金额
                .subject("通道前置Activity-商品名称")//商品名称
                .body("通道前置Activity-交易详情")//商品交易详情
                .notifyUrl(Merchant.MERCHANT_NOTIFY_URL)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ali:
                pay.startPay(FuQianLa.ALI);
                break;
            case R.id.btn_wx:
                pay.startPay(FuQianLa.WX);
                break;
            case R.id.btn_bd:
                pay.startPay(FuQianLa.BD);
                break;
            case R.id.btn_up:
                pay.startPay(FuQianLa.UP);
                break;
            case R.id.btn_jd:
                pay.startPay(FuQianLa.JD);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //返回支付结果
        if (requestCode == FuQianLa.REQUESTCODE
                && resultCode == FuQianLa.RESULTCODE
                && data != null) {
            FuQianLaResult result = data.getParcelableExtra(FuQianLa.PAYRESULT_KEY);
            Toast.makeText(this, result.payCode, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
