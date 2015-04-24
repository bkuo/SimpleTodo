package us.ridiculousbakery.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public static final String US_RIDICULOUSBAKERY_SUBJECT_IDX = "us.ridiculousbakery.subject_idx";
    private Model model ;
    private ListView lvItems = null;
    private ArrayAdapter itemsAdapter =null;

    private ArrayList dataList = null;

    private void refreshData(){
        dataList = Model.readItems(this);
    }
    private void saveData(){
        Model.writeItems(this, dataList);
    }
    private void refreshViews(){
        itemsAdapter.clear();
        itemsAdapter.addAll(dataList);
        itemsAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshData();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1 );
        lvItems = (ListView) findViewById(R.id.listView);
        lvItems.setAdapter(itemsAdapter);

        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                        dataList.remove(position);
                        saveData();
                        refreshViews();
                        return true;
                    }
                }

        );
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
                        startEditForPos(position);
                    }
                }
        );
    }

    private void startEditForPos(int position){
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra(US_RIDICULOUSBAKERY_SUBJECT_IDX, position);
        startActivityForResult(i, 0);
    }

    public void onQuickAddSubmit(View v){
        dataList.add(quickAddText());
        refreshViews();
        etQuickAdd().setText("");
        saveData();
    }
    private String quickAddText(){
        return etQuickAdd().getText().toString();
    }

    private EditText etQuickAdd(){
       return (EditText) findViewById(R.id.editText);
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
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity( i);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume(){
        super.onResume();
        refreshData();
        refreshViews();
    }

}
