package poojab26.attendance;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
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
    /*
     * 1-Software Engineering - SE
     * 2-Computer Networks - CN
     * 3-Formal Languages and Automata Theory - FLA
     * 4-CN Lab
     * 5-CAT - I
     * 6-Operating Systems - OS
     * 7-Cyber Security - CS
     * 8-OS labs
     * 9-Multimedia Applications - MA
     * 10-PECC
     *
     * */
    //This is our viewPager
    private ViewPager viewPager;
    public String allSubjects=" 1-SE\n" +
            "        2-CN\n" +
            "        3-FLA\n" +
            "        4-CN Lab\n" +
            "        5-CAT\n" +
            "        6-OS\n" +
            "        7-CS\n" +
            "        8-OS Labs\n" +
            "        9-MA\n" +
            "        10-PECC";
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
        selectPage(day);
        scheduleNotification(getNotification("30 second delay"), 1000);




    }

    private void selectPage(String day){
        int pageIndex=0;
        if(day=="Tuesday")
            pageIndex=1;
        else if (day=="Wednesday")
            pageIndex=2;
        else if (day=="Thursday")
            pageIndex=3;
        else if (day=="Friday")
            pageIndex=4;


        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }


    private String getDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString = null;
        switch (day) {
            case Calendar.MONDAY:
                dayString = "Monday";
                sendNotification(dayString);
                break;
            case Calendar.TUESDAY:
                dayString = "Tuesday";
                sendNotification(dayString);

                break;
            case Calendar.WEDNESDAY:
                dayString = "Wednesday";
                sendNotification(dayString);

                break;
            case Calendar.THURSDAY:
                dayString = "Thursday";
                sendNotification(dayString);

                break;
            case Calendar.FRIDAY:
                dayString = "Friday";
                sendNotification(dayString);

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
    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        //builder.setSmallIcon(R.drawable.ic_launcher);
        return builder.build();
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
