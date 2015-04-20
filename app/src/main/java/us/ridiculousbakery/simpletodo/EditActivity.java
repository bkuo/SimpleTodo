package us.ridiculousbakery.simpletodo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class EditActivity extends ActionBarActivity {
    private ArrayList dataList = null;
    private int position ;

    private void refreshData(){
        dataList = Model.readItems(this);
    }
    private void saveData(){
        Model.writeItems(this, dataList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        position = getIntent().getIntExtra(MainActivity.US_RIDICULOUSBAKERY_SUBJECT_IDX, 0);
        refreshData();
        EditText etSub=(EditText)findViewById(R.id.editSubject);
        etSub.setText(value());
    }

    private String value(){
        return (String)dataList.get(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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

        return super.onOptionsItemSelected(item);
    }
    public void onSubmit(View v){
        EditText etSub=(EditText)findViewById(R.id.editSubject);
        dataList.set(position, etSub.getText().toString() );
        saveData();
        refreshData();
        finish();
    }
}
