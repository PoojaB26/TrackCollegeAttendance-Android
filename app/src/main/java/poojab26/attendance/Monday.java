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

//Our class extending fragment
public class Monday extends Fragment {
    Button btnDAALabA, btnDAALabB, btnCoaA, btnCoaB, btnDcA, btnDcB, btnMathA, btnMathB;
    TextView tvDAALabPercentage, tvCoaAttendance, tvDcAttendance, tvMathAttendance;
    DatabaseHandler db;
    String DBMS ="1", COA="2", Elect="3", DC="4", MATH="5", DAA="6", DBMSLAB="7", DAALAB="8", BUSC="9";

    /*
    * 1-dbms
    * 2-COA
    * 3-elective
    * 4-DC
    * 5-math
    * 6-DAA
    * 7-dbmslab
    * 8-DAAlab
    * 9-busc
    *
    * */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DatabaseHandler(getActivity());


        buttonsDAALab(DAALAB);
        buttonsCoa(COA);
        buttonsDc(DC);
        buttonsMath(MATH);


    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.monday, container, false);

        btnDAALabA = (Button) view.findViewById(R.id.daalabA);
        btnDAALabB = (Button) view.findViewById(R.id.daalabB);
        btnCoaA = (Button) view.findViewById(R.id.coaA);
        btnCoaB = (Button) view.findViewById(R.id.coaB);
        btnDcA = (Button) view.findViewById(R.id.dcA);
        btnDcB = (Button) view.findViewById(R.id.dcB);
        btnMathA = (Button) view.findViewById(R.id.mathA);
        btnMathB = (Button) view.findViewById(R.id.mathB);
        tvDAALabPercentage = (TextView)view.findViewById(R.id.daalabAtten);
        tvCoaAttendance = (TextView)view.findViewById(R.id.coaAtten);
        tvDcAttendance = (TextView)view.findViewById(R.id.dcAtten);
        tvMathAttendance = (TextView)view.findViewById(R.id.mathAtten);


        return view;
    }


    void buttonsDAALab(final String subid){
        btnDAALabA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvDAALabPercentage);

                }
                viewData();
            }
        });


        btnDAALabB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvDAALabPercentage);

                }
                viewData();
            }
        });

    }
    void buttonsCoa(final String subid){
        btnCoaA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else{

                    updateData(subid, 1, 0);
                    showPercentage(subid, tvCoaAttendance);

                }
                viewData();
            }
        });


        btnCoaB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty==true)
                    addData(subid, 1, 1);
                else {
                    updateData(subid, 1, 1);
                    showPercentage(subid, tvCoaAttendance);
                }
                    viewData();
            }
        });
    }
    void buttonsDc(final String subid){
        btnDcA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else{

                    updateData(subid, 1, 0);
                    showPercentage(subid, tvDcAttendance);

                }
                viewData();
            }
        });


        btnDcB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty==true)
                    addData(subid, 1, 1);
                else {
                    updateData(subid, 1, 1);
                    showPercentage(subid, tvDcAttendance);
                }
                viewData();
            }
        });
    }
    void buttonsMath(final String subid){
        btnMathA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else{

                    updateData(subid, 1, 0);
                    showPercentage(subid, tvMathAttendance);

                }
                viewData();
            }
        });


        btnMathB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty==true)
                    addData(subid, 1, 1);
                else {
                    updateData(subid, 1, 1);
                    showPercentage(subid, tvMathAttendance);
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
        tv.invalidate();

    }







}