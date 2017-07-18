package poojab26.attendance;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pblead26 on 29-Nov-16.
 */

public class Tuesday extends Fragment {
    Button btnSE_A, btnSE_B, btnCN_A, btnCN_B, btnFLA_A, btnFLA_B, btnOS_A, btnOS_B, btnCS_A, btnCS_B;
    TextView tvSEPercent, tvCNPercent, tvFLAPercent, tvOSPercent, tvCSPercent;
    DatabaseHandler db;
    String SE ="1", CN="2", FLA="3", CNLab="4", CAT="5", OS="6", CS="7", OSLab="8", MA="9", PECC="10";

    /*
    * 1-Software Engineering - SE
    * 2-Computer Networks - CN
    * 3-Formal Languages and Automata Theory - FLA
    * 6-Operating Systems - OS
    * 7-Cyber Security - CS
    *
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tuesday, container, false);

        btnSE_A = (Button) view.findViewById(R.id.SE_A);
        btnSE_B = (Button) view.findViewById(R.id.SE_B);
        btnCN_A = (Button) view.findViewById(R.id.CN_A);
        btnCN_B = (Button) view.findViewById(R.id.CN_B);
        btnFLA_A = (Button) view.findViewById(R.id.FLA_A);
        btnFLA_B = (Button) view.findViewById(R.id.FLA_B);
        btnOS_A = (Button) view.findViewById(R.id.OS_A);
        btnOS_B = (Button) view.findViewById(R.id.OS_B);
        btnCS_A = (Button) view.findViewById(R.id.CS_A);
        btnCS_B = (Button) view.findViewById(R.id.CS_B);

        tvCNPercent = (TextView)view.findViewById(R.id.CN_Att);
        tvCSPercent = (TextView)view.findViewById(R.id.CS_Att);
        tvFLAPercent = (TextView)view.findViewById(R.id.FLA_Att);
        tvOSPercent = (TextView)view.findViewById(R.id.OS_Att);
        tvSEPercent = (TextView)view.findViewById(R.id.SE_Att);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DatabaseHandler(getActivity());



        getAttendanceSE(SE);
        getAttendanceCN(CN);
        getAttendanceFLA(FLA);
        getAttendanceOS(OS);
        getAttendanceCS(CS);


    }


    void getAttendanceSE(final String subid){
        btnSE_A.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvSEPercent);

                }
                viewData();
            }
        });


        btnSE_B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvSEPercent);

                }
                viewData();
            }
        });

    }
    void getAttendanceCN(final String subid){
        btnCN_A.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvCNPercent);

                }
                viewData();
            }
        });


        btnCN_B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvCNPercent);

                }
                viewData();
            }
        });

    }
    void getAttendanceFLA(final String subid){
        btnFLA_A.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvFLAPercent);

                }
                viewData();
            }
        });


        btnFLA_B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvFLAPercent);

                }
                viewData();
            }
        });

    }
    void getAttendanceOS(final String subid){
        btnOS_A.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvOSPercent);

                }
                viewData();
            }
        });


        btnOS_B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvOSPercent);

                }
                viewData();
            }
        });

    }
    void getAttendanceCS(final String subid){
        btnCS_A.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvCSPercent);

                }
                viewData();
            }
        });


        btnCS_B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvCSPercent);

                }
                viewData();
            }
        });

    }





    public void addData(String subid, double total, double absent){

        double perc = ((total - absent) / total)*100;
        boolean isInserted = db.insertData(subid, total, absent, perc);
        if(isInserted == true)
            Log.d(this.getClass().getSimpleName(), "inserted");
        else
            Log.d(this.getClass().getSimpleName(), "NOT inserted");

    }
    public void updateData(String subid, double total, double absent){

        String values[] = showRowData(subid);
        Log.d("HELLO HI", values[0]+ "  " + values[1]);
        total = total + Double.parseDouble(values[0]);
        absent = absent + Double.parseDouble(values[1]);
        double perc = ((total - absent) / total)*100;

        boolean isUpdate = db.updateData(subid, total, absent, perc);
        if(isUpdate == true)
            Log.d(this.getClass().getSimpleName(), "updated");
        else
            Log.d(this.getClass().getSimpleName(), "not updated");

    }


    public boolean isEmpty(String subid) {
        Cursor res = db.getRowData(subid);
        if (res.getCount() == 0) {
            return true;
        } else return false;
    }
    public String[] showRowData(String subid){
        Cursor cursor = db.getRowData(subid);
        if (cursor.getCount() == 0)
            return null;
        String values[]= new String[2];
        if (cursor.moveToFirst()){
            do{
                values[0] = cursor.getString(cursor.getColumnIndex("total"));
                values[1] = cursor.getString(cursor.getColumnIndex("absentno"));

                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        return values;

    }
    public void viewData(){
        Cursor res = db.getAllData();

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Subid :" + res.getString(1) + "\n");
            buffer.append("Total :" + res.getString(2) + "\n");
            buffer.append("AbsentNo :" + res.getString(3) + "\n\n");
            buffer.append("Perc :" + res.getString(4) + "\n\n");



            Log.d("Data",buffer.toString());

        }
        // Show all data

    }

    public String getPerc(String subid){
        Cursor cursor = db.getPercData(subid);
        String Perc = new String();
        if (cursor.moveToFirst()){
            do{
                Perc  = cursor.getString(cursor.getColumnIndex("perc"));

                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Perc;
    }
    void showPercentage(String subid, TextView tv){

        String percentage = getPerc(subid);
        double Perc = Double.parseDouble(percentage);
        tv.setText("Your attendance is " + Perc );
        tv.setVisibility(View.VISIBLE);
        tv.requestFocus();
        tv.invalidate();

    }
}
