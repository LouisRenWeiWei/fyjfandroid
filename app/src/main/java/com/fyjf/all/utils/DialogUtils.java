package com.fyjf.all.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fyjf.all.R;
import com.fyjf.all.adapter.SingleChoiceAdapter;

import java.util.List;

/**
 * Created by 任伟伟
 * Datetime: 2016/11/9-12:26
 * Email: renweiwei@ufashion.com
 */

public class DialogUtils {
    public static interface OnCancelListener{
        void onCanceled();
    }
    public static interface OnConfirmListener{
        void onConfirmed();
    }
    public static interface OnItemClickedListener{
        void onItemClicked(int position);
    }
    public static interface OnSingleChoiceListener{
        void onSingleChoice(int position);
    }
    public static void showPromote1(Context mContext,String message,final OnConfirmListener onConfirmListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final Dialog dialog = builder.create();
        View view  = LayoutInflater.from(mContext).inflate(R.layout.dailog_prompt1, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        Button btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(onConfirmListener!=null)onConfirmListener.onConfirmed();
            }
        });
        tv_message.setText(message);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        dialog.show();
        dialog.setContentView(view);
    }
    public static void showPromote2(Context mContext,String message,final OnCancelListener onCancelListener,final OnConfirmListener onConfirmListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final Dialog dialog = builder.create();
        View view  = LayoutInflater.from(mContext).inflate(R.layout.dailog_prompt2, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(onCancelListener!=null)onCancelListener.onCanceled();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(onConfirmListener!=null)onConfirmListener.onConfirmed();
            }
        });
        tv_message.setText(message);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        dialog.show();
        dialog.setContentView(view);
    }

    public static void showSingleChoice(Context mContext, String title, List data,int selection, final OnSingleChoiceListener onSingleChoiceListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final Dialog dialog = builder.create();
        View view  = LayoutInflater.from(mContext).inflate(R.layout.dailog_single_choice, null);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        SingleChoiceAdapter adapter = new SingleChoiceAdapter(mContext,data);
        adapter.setSelection(selection);
        listView.setAdapter(adapter);
        tv_title.setText(title);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onSingleChoiceListener!=null)onSingleChoiceListener.onSingleChoice(position);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setContentView(view);
    }

}
