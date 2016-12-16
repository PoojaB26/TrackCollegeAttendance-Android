package poojab26.attendance;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views
public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    Button btnSeeALL;
    private TabLayout tabLayout;
    Cursor res;
   DatabaseHandler db;

    //This is our viewPager
    private ViewPager viewPager;
    public String allSubjects=" 1-dbms\n" +
            "        2-COA\n" +
            "        3-elective\n" +
            "        4-DC\n" +
            "        5-math\n" +
            "        6-DAA\n" +
            "        7-dbmslab\n" +
            "        8-DAAlab\n" +
            "        9-busc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Mon"));
        tabLayout.addTab(tabLayout.newTab().setText("Tues"));
        tabLayout.addTab(tabLayout.newTab().setText("Wed"));
        tabLayout.addTab(tabLayout.newTab().setText("Thu"));
        tabLayout.addTab(tabLayout.newTab().setText("Fri"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
        btnSeeALL = (Button)findViewById(R.id.btnSeeAll);
        btnSeeALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    viewData();
            }
        });


        String day = getDay();
        sendNotification(day);

    }

    private String getDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString = null;
        switch (day) {
            case Calendar.MONDAY:
                dayString = "Monday";
                break;
            case Calendar.TUESDAY:
                dayString = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                dayString = "Wednesday";
                break;
            case Calendar.THURSDAY:
                dayString = "Thursday";
                break;
            case Calendar.FRIDAY:
                dayString = "Friday";
                break;
        }
        return dayString;
    }

    public void sendNotification(String day) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Reminder");
        builder.setContentText(day + " Attendance");
        builder.setSubText("Tap to track the attendance today.");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }

    public void viewData(){

        res = db.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(allSubjects + "\n\n\n");
        while (res.moveToNext()) {
            buffer.append("Subid :"+ res.getString(1)+"\n");
            buffer.append("Total :"+ res.getString(2)+"\n");
            buffer.append("Absent :"+ res.getString(3)+"\n\n");
            buffer.append("Perc :"+ res.getString(4)+"\n\n");

        }

        // Show all data
        showMessage("Data",buffer.toString());
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabLayout.setOnTabSelectedListener(this);

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
