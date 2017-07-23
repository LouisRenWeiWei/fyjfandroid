package com.fyjf.all.activity.report;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fyjf.all.R;
import com.fyjf.all.activity.BaseActivity;
import com.fyjf.all.activity.BaseFragmentActivity;
import com.fyjf.all.activity.LoginActivity;
import com.fyjf.all.fragment.PDFFragment;
import com.fyjf.dao.entity.CustomerReportInfo;
import com.fyjf.utils.SDUtils;
import com.fyjf.vo.RequestUrl;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

//import com.joanzapata.pdfview.PDFView;
//import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
//import com.joanzapata.pdfview.listener.OnPageChangeListener;

/**
 * Created by ASUS on 2017/6/23.
 */
/*
* author: renweiwei
* datetime:
*
*/
public class CreditReportActivity extends BaseFragmentActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rg_reports)
    RadioGroup rg_reports;

    @BindView(R.id.btn_bank)
    RadioButton btn_bank;
    @BindView(R.id.btn_social)
    RadioButton btn_social;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private CustomerReportInfo report;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_credit_report;
    }

    @Override
    protected void preInitData() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rg_reports.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (btn_bank.isChecked()){
                    viewpager.setCurrentItem(0);
                }else {
                    viewpager.setCurrentItem(1);
                }
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            report = (CustomerReportInfo) intent.getSerializableExtra("report");
            ArrayList<Fragment> fragments = new ArrayList<Fragment>();
            Fragment bank = PDFFragment.getInstance(report.getBankCreditReport());
            fragments.add(bank);

            Fragment social = PDFFragment.getInstance(report.getSocialCreditReport());
            fragments.add(social);

            viewpager.setOnPageChangeListener(new CreditReportActivity.MyOnPageChangeListener());
            viewpager.setAdapter(new CreditReportActivity.PagerAdapter(getSupportFragmentManager(), fragments));
        }


    }

    @Override
    protected void showFragment(String tag) {

    }

    @Override
    protected int getContentId() {
        return 0;
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageSelected(int arg0) {
            if (arg0==0){
                btn_bank.setChecked(true);
            }else{
                btn_social.setChecked(true);
            }
        }
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    }
    //ViewPager PagerAdapter
    public class PagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;//fragment list
        public PagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
    }


}
