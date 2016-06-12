package com.example.jerryyin.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jerryyin.myapplication.R;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private DanmakuView mBarrageView;
    private EditText mEtTxt;
    private Button mBtnSend;

    private List<IDanmakuItem> mItemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        initView();
    }

    private void initView() {
        mBarrageView = (DanmakuView) findViewById(R.id.barrage_view);
        mEtTxt = (EditText) findViewById(R.id.et_text);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mItemList.add(new DanmakuItem(MainActivity2.this, "fefs", mBarrageView.getWidth()));
        mItemList.add(new DanmakuItem(MainActivity2.this, "fefjfhshu", mBarrageView.getWidth()));
        mItemList.add(new DanmakuItem(MainActivity2.this, "aafhifhis", mBarrageView.getWidth()));
//        mBarrageView.addItem(mItemList, true);

        mBarrageView.show();
//        mBarrageView.hide();
//        mBarrageView.clear();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                String s = mEtTxt.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    mItemList.add(new DanmakuItem(MainActivity2.this, s, mBarrageView.getWidth()));
                    mBarrageView.addItem(new DanmakuItem(this, s, mBarrageView.getWidth()));
//                    mItemList.notify();
                }
                break;
        }
    }
}
