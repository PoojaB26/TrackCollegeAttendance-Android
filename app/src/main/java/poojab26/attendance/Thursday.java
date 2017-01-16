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

public class Thursday extends Fragment {
    Button btnDbmsA, btnDbmsB, btnDbmsLabA, btnDbmsLabB, btnDaaA, btnDaaB, btnMathA, btnMathB, btnCoaA, btnCoaB, btnElectA, btnElectB;
    TextView tvCoaAttendance, tvDbmsAttendance, tvDaaAttendance, tvDbmsLabAttendance,tvElectAttendance, tvMathAttendance;
    DatabaseHandler db;
    String DBMS ="1", COA="2", Elect="3", DC="4", MATH="5", DAA="6", DBMSLAB="7", DAALAB="8", BUSC="9";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thursday, container, false);

        btnDaaA = (Button) view.findViewById(R.id.daaA);
        btnDaaB = (Button) view.findViewById(R.id.daaB);
        btnDbmsA = (Button) view.findViewById(R.id.dbmsA);
        btnDbmsB = (Button) view.findViewById(R.id.dbmsB);
        btnDbmsLabA = (Button) view.findViewById(R.id.dbmslabA);
        btnDbmsLabB = (Button) view.findViewById(R.id.dbmslabB);
        btnElectA = (Button) view.findViewById(R.id.elecA);
        btnElectB = (Button) view.findViewById(R.id.elecB);
        btnCoaA = (Button) view.findViewById(R.id.coaA);
        btnCoaB = (Button) view.findViewById(R.id.coaB);
        btnMathA = (Button) view.findViewById(R.id.mathA);
        btnMathB = (Button) view.findViewById(R.id.mathB);
        tvElectAttendance = (TextView)view.findViewById(R.id.electiveAtten);
        tvDaaAttendance = (TextView)view.findViewById(R.id.daaAtten);
        tvDbmsAttendance = (TextView)view.findViewById(R.id.dbmsAtten);
        tvDbmsLabAttendance = (TextView)view.findViewById(R.id.dbmslabAtten);
        tvCoaAttendance = (TextView)view.findViewById(R.id.coaAtten);
        tvMathAttendance = (TextView)view.findViewById(R.id.mathAtten);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DatabaseHandler(getActivity());

        buttonsCoa(COA);
        buttonsDbms(DBMS);
        buttonsDaa(DAA);
        buttonsDbmsLab(DBMSLAB);
        buttonsElect(Elect);
        buttonsMath(MATH);


    }
    void buttonsElect(final String subid){
        btnElectA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvElectAttendance);

                }
                viewData();
            }
        });


        btnElectB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvElectAttendance);

                }
                viewData();
            }
        });

    }
    void buttonsDaa(final String subid){
        btnDaaA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvDaaAttendance);

                }
                viewData();
            }
        });


        btnDaaB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvDaaAttendance);

                }
                viewData();
            }
        });

    }
    void buttonsDbms(final String subid){
        btnDbmsA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else {

                    updateData(subid, 1, 0);

                    showPercentage(subid, tvDbmsAttendance);

                }
                viewData();
            }
        });


        btnDbmsB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 1);
                else {

                    updateData(subid, 1, 1);

                    showPercentage(subid, tvDbmsAttendance);

                }
                viewData();
            }
        });

    }
    void buttonsDbmsLab(final String subid){
        btnDbmsLabA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty)
                    addData(subid, 1, 0);
                else{

                    updateData(subid, 1, 0);
                    showPercentage(subid, tvDbmsLabAttendance);

                }
                viewData();
            }
        });


        btnDbmsLabB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isEmpty = isEmpty(subid);
                if(isEmpty==true)
                    addData(subid, 1, 1);
                else {
                    updateData(subid, 1, 1);
                    showPercentage(subid, tvDbmsLabAttendance);
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
