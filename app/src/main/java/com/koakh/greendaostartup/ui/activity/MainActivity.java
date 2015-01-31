package com.koakh.greendaostartup.ui.activity;

import java.util.Locale;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.koakh.greendaostartup.R;
import com.koakh.greendaostartup.app.Singleton;
import com.koakh.greendaostartup.model.DaoSession;
import com.koakh.greendaostartup.model.Note;
import com.koakh.greendaostartup.model.NoteDao;
import com.koakh.greendaostartup.model.repositories.NoteRepository;

/**
 * Created by mario on 31/01/2015.
 */
public class MainActivity extends ActionBarActivity {

  private Singleton mApp;
  private Context mContext;

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide
   * fragments for each of the sections. We use a
   * {@link FragmentPagerAdapter} derivative, which will keep every
   * loaded fragment in memory. If this becomes too memory intensive, it
   * may be best to switch to a
   * {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  SectionsPagerAdapter mSectionsPagerAdapter;

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(mSectionsPagerAdapter);

    // Get Application Singleton
    mApp = ((Singleton) this.getApplicationContext());
    mContext = this.getBaseContext();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    else
    //Use it from Repository
    if (id == R.id.action_test_with_repository) {
      try {
        Note note = new Note();
        note.setText("Note1");
        note.setComment("Note1 comment");
        //Insert
        NoteRepository.insertOrUpdate(mContext, note);
        Log.d(mApp.TAG, "Inserted new note1, ID: " + note.getId());
        //Load
        Log.d(mApp.TAG, String.format("Loaded Note: %s", NoteRepository.get(mContext, 1).getText()));
      } catch (Exception e) {
        Log.e(mApp.TAG, e.getMessage());
        e.printStackTrace();
      }
      return true;
    }
    else
    //Using Dao Object without Repository
    if (id == R.id.action_test_with_dao_objects) {
      try {
        Note note = new Note();
        note.setText("Note2");
        note.setComment("Comment Note2");
        DaoSession daoSession = mApp.getDaoSession();
        NoteDao noteDao = daoSession.getNoteDao();
        noteDao.insert(note);
        Log.d(mApp.TAG, "Inserted new note2, ID: " + note.getId());
        //Load
        Log.d(mApp.TAG, String.format("Loaded Note: %s", noteDao.load(note.getId()).getText()));
      } catch (Exception e) {
        Log.e(mApp.TAG, e.getMessage());
        e.printStackTrace();
      }
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      Locale l = Locale.getDefault();
      switch (position) {
        case 0:
          return getString(R.string.title_section1).toUpperCase(l);
        case 1:
          return getString(R.string.title_section2).toUpperCase(l);
        case 2:
          return getString(R.string.title_section3).toUpperCase(l);
      }
      return null;
    }
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }

}
