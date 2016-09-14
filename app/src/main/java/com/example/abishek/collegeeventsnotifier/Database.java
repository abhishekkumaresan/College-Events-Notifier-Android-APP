package com.example.abishek.collegeeventsnotifier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Abishek on 19/3/16.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Event.db";
    public static final String TABLE_NAME = "event";
    public static final String ID = "_id";
    public static final String COLLEGE_NAME = "name";
    public static final String URL = "url";
    public static final String LOCATION = "location";
    public static final String SEARCH_KEY = "search_key";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY ," +
                COLLEGE_NAME + " TEXT, " +
                LOCATION + " TEXT, " +
                SEARCH_KEY + " TEXT, " +
                URL + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNewCollege(String name, String url, String location) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLLEGE_NAME, name);
        contentValues.put(URL, url);
        contentValues.put(LOCATION, location);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public void insertColleges() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLLEGE_NAME, "IIT Bombay");
        contentValues.put(URL, "http://www.iitb.ac.in/");
        contentValues.put(LOCATION, "Mumbai");
        contentValues.put(SEARCH_KEY, "Events Calendar");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT Delhi");
        contentValues.put(URL, "http://www.iitd.ac.in/");
        contentValues.put(LOCATION, "Delhi");
        contentValues.put(SEARCH_KEY, "Events@IIT Delhi");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT Madras");
        contentValues.put(URL, "https://www.iitm.ac.in/");
        contentValues.put(LOCATION, "Chennai");
        contentValues.put(SEARCH_KEY, "Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT kharagpur");
        contentValues.put(URL, "http://www.iitkgp.ac.in/");
        contentValues.put(LOCATION, "kharagpur");
        contentValues.put(SEARCH_KEY, "Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT Kanpur");
        contentValues.put(URL, "http://www.iitk.ac.in/");
        contentValues.put(LOCATION, "Kanpur");
        contentValues.put(SEARCH_KEY, "");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT Roorkee");
        contentValues.put(URL, "http://www.iitr.ac.in/");
        contentValues.put(LOCATION, "Roorkee");
        contentValues.put(SEARCH_KEY, "News and Announcements");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT Ghandinagr");
        contentValues.put(URL, "http://www.iitgn.ac.in/");
        contentValues.put(LOCATION, "Ghandinagr");
        contentValues.put(SEARCH_KEY, "Events @ IITGN");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT Guwahati");
        contentValues.put(URL, "http://www.iitg.ac.in/");
        contentValues.put(LOCATION, "Guwahati");
        contentValues.put(SEARCH_KEY, "EVENTS,HAPPENINGS");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIT Hydrabad");
        contentValues.put(URL, "http://www.iith.ac.in/");
        contentValues.put(LOCATION, "Hydrabad");
        contentValues.put(SEARCH_KEY, "News");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "Indian Institute of Science");
        contentValues.put(URL, "http://www.iisc.ernet.in/");
        contentValues.put(LOCATION, "Bangalore");
        contentValues.put(SEARCH_KEY, "UPCOMING EVENTS");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "NIT Warangal");
        contentValues.put(URL, "http://www.nitw.ac.in/nitw/");
        contentValues.put(LOCATION, "Warangal");
        contentValues.put(SEARCH_KEY, "Workshops,Conferences");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "NIT Trichy");
        contentValues.put(URL, "http://www.nitt.edu/");
        contentValues.put(LOCATION, "Trichy");
        contentValues.put(SEARCH_KEY, "Upcoming Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "NIT Rourkela");
        contentValues.put(URL, "http://http://www.nitrkl.ac.in/Events_Happenings/2EvetThisWeek/Default.aspx,http://www.nitrkl.ac.in/Events_Happenings/3Seminar/Default.aspx,http://www.nitrkl.ac.in/Events_Happenings/7TechnicalEvent/Default.aspx,http://www.nitrkl.ac.in/Events_Happenings/10Miscellaneous/Default.aspx");
        contentValues.put(LOCATION, "Rourkela");
        contentValues.put(SEARCH_KEY, "Events this week,Seminars,Technical Events,Miscellaneous Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "VIT University");
        contentValues.put(URL, "http://www.vit.ac.in/events");
        contentValues.put(SEARCH_KEY, "Events");
        contentValues.put(LOCATION, "Vellore");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "SSN");
        contentValues.put(URL, "http://www.ssn.edu.in/?page_id=124,http://www.ssn.edu.in/?page_id=123,http://www.ssn.edu.in/?page_id=129,http://www.sace.ssn.edu.in/");
        contentValues.put(LOCATION, "Chennai");
        contentValues.put(SEARCH_KEY, "EVENTS,NEWS,News & Events,Latest News & Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "PSG ITech");
        contentValues.put(URL, "http://www.psgitech.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "EVENTS CALENDAR,NEWS UPDATES");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "PSG Tech");
        contentValues.put(URL, "http://www.psgtech.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "Announcements");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "Sri Krishna College of Engineering and Technology");
        contentValues.put(URL, "http://www.skcet.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "News and Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "Coimbatore Institute of Technology");
        contentValues.put(URL, "http://www.cit.edu.in/events-list/,http://www.cit.edu.in/conference-list-2/,http://www.cit.edu.in/programmes-list/,http://www.cit.edu.in/workshops-list/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "KCT");
        contentValues.put(URL, "http://www.kct.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "Sri Eashwar College of Engineering");
        contentValues.put(URL, "http://www.sece.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "News & Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "KPR Institute of Engineering and Technology");
        contentValues.put(URL, "http://www.kpriet.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "Announcements,News HUB");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "Jansons Institute Of Technology");
        contentValues.put(URL, "http://www.jit.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "SNS College of Technology");
        contentValues.put(URL, "http://www.snsct.org/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "Calendar of events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "SNS College of Engineering");
        contentValues.put(URL, "http://www.snsce.ac.in/");
        contentValues.put(LOCATION, "Coimbatore");
        contentValues.put(SEARCH_KEY, "");
        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "RVS College of Engineering and Technology");
        contentValues.put(URL, "http://www.rvscet.ac.in/");
        contentValues.put(SEARCH_KEY, "News & Events");
        contentValues.put(LOCATION, "Coimbatore");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "RVS Engineering College");
        contentValues.put(URL, "http://www.rvseng.ac.in/");
        contentValues.put(LOCATION, "Dindigul");
        contentValues.put(SEARCH_KEY, "News & Events");
        db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COLLEGE_NAME, "IIM Bangalore");
        contentValues.put(URL, "http://www.iimb.ernet.in/events");
        contentValues.put(LOCATION, "Bangalore");
        contentValues.put(SEARCH_KEY, "News & Events");
        db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<String> getCollegeNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + COLLEGE_NAME + " FROM " + TABLE_NAME, null);
        res.moveToFirst();
        ArrayList<String> al = new ArrayList<String>();
        while (!res.isAfterLast()) {
            al.add(res.getString(res.getColumnIndex(COLLEGE_NAME)));
            res.moveToNext();
        }
        res.close();
        return al;
    }

    public String getUrl(String college) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        c = db.rawQuery("SELECT " + URL + " FROM " + TABLE_NAME + " WHERE " + COLLEGE_NAME + "='" + college + "'", null);
        c.moveToFirst();
        Log.d("URl", c.getString(c.getColumnIndex(URL)));
        String ret = c.getString(c.getColumnIndex(URL));
        c.close();
        return ret;
    }

    public ArrayList<String> getCollegeLocations() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT distinct(" + LOCATION + ") FROM " + TABLE_NAME, null);
        res.moveToFirst();
        ArrayList<String> al = new ArrayList<String>();
        while (!res.isAfterLast()) {
            al.add(res.getString(res.getColumnIndex(LOCATION)));
            res.moveToNext();
        }
        res.close();
        return al;
    }

    public ArrayList<ArrayList<String>> getCollegeUrlForLocation(String location) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + URL + " FROM " + TABLE_NAME + " WHERE " + LOCATION + "='" + location + "'", null);
        res.moveToFirst();
        ArrayList<ArrayList<String>> al = new ArrayList<>();
        while (!res.isAfterLast()) {
            ArrayList<String> temp = new ArrayList<>();
            String s = res.getString(res.getColumnIndex(URL));
            String[] t = s.split(",");
            temp.addAll(Arrays.asList(t));
            al.add(temp);
            res.moveToNext();
        }
        res.close();
        return al;
    }

    public String getSearchKeysByCollegeName(String collegeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + SEARCH_KEY + " FROM " + TABLE_NAME + " WHERE " + COLLEGE_NAME + "='" + collegeName + "'", null);
        res.moveToFirst();
        String searchkey = (res.getString(res.getColumnIndex(SEARCH_KEY)));
        res.close();
        return searchkey;
    }

    public ArrayList<ArrayList<String>> getAllSearchKeysByLocation(String location) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + SEARCH_KEY + "," + COLLEGE_NAME + " FROM " + TABLE_NAME + " GROUP BY " + COLLEGE_NAME + " HAVING " + LOCATION + "='" + location + "'", null);
        res.moveToFirst();

        ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
        while (!res.isAfterLast()) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(res.getString(res.getColumnIndex(COLLEGE_NAME)));
            String s[] = res.getString(res.getColumnIndex(SEARCH_KEY)).split(",");
            temp.addAll(Arrays.asList(s));
            al.add(temp);
            res.moveToNext();

        }
        res.close();
        return al;
    }

    public String getCollegeNameForURL(String url) {
        SQLiteDatabase db = this.getReadableDatabase();
        url = "%" + url + "%";
        Cursor res = db.rawQuery("SELECT " + COLLEGE_NAME + " FROM " + TABLE_NAME + " WHERE " + URL + " like   '" + url + "' ", null);
        res.moveToFirst();
        String college = (res.getString(res.getColumnIndex(COLLEGE_NAME)));
        res.close();
        return college;

    }


}
