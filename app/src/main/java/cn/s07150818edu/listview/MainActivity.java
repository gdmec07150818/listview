package cn.s07150818edu.listview;

import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"");
        menu.add(0,1,0,"");
        menu.add(0,1,0,"");
        menu.add(0,1,0,"");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void arrayAdapter(){
        final String[] weeks={"Mon","Tus","Wed","Thu","Fri","Sun","Sat"};
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,weeks);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,weeks[i],Toast.LENGTH_LONG).show();
            }
        });

}
    public void simpleCursorAdapter(){
        final Cursor mCursor=getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,null,null,null,null);
        startManagingCursor(mCursor);
        final SimpleCursorAdapter mAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_expandable_list_item_1,mCursor,new String[]
                {ContactsContract.Contacts.DISPLAY_NAME},new int[]{android.R.id.text1},0);
        lv.setAdapter(mAdapter);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this,mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                    Toast.LENGTH_SHORT).show();
           }
       });

    }
    private void simpleAdapter(){
        final List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title","G1");
        map.put("info","google 1");
        map.put("img",R.drawable.icon1);
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("title","G1");
        map.put("info","google 1");
        map.put("img",R.drawable.icon1);
        list.add(map);

        map.put("title","G1");
        map.put("info","google 1");
        map.put("img",R.drawable.icon2);
        list.add(map);

        map.put("title","G1");
        map.put("info","google 1");
        map.put("img",R.drawable.icon3);
        list.add(map);

        SimpleAdapter mAdapter=new SimpleAdapter(this,list,R.layout.simpleadapter_demo,
                new String[]{"img","title","info"},
                new int[]{R.id.imageView,R.id.titleView,R.id.infoView});
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,list.get(i).get("title").toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }



    static class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView info;
        public Button btn;
        public LinearLayout layout;

    }
    private void baseAdapter() {
        class MyBaseAdapter extends BaseAdapter {
            private List<Map<String, Object>> data;
            private Context context;
            private LayoutInflater myLayoutInflater;

            public MyBaseAdapter(Context context, List<Map<String, Object>> data) {
                super();
                this.data = data;
                this.context = context;
                this.myLayoutInflater = LayoutInflater.from(context);
            }

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }


            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                ViewHolder holder = null;
                if(convertView == null){
                    holder = new ViewHolder();
                    convertView = myLayoutInflater.inflate(R.layout.l1,parent,false);
                    holder.img = (ImageView) convertView.findViewById(R.id.img);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    holder.info = (TextView) convertView.findViewById(R.id.info);
                    holder.btn = (Button) convertView.findViewById(R.id.btn);
                    holder.layout = (LinearLayout) convertView.findViewById(R.id.l1);
                    convertView.setTag(holder);
                }else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.img.setImageResource(Integer.parseInt(data.get(position).get("img").toString()));
                holder.title.setText(data.get(position).get("title").toString());
                holder.info.setText(data.get(position).get("info").toString());
                if (position % 2 == 1){
                    holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent));
                }else {
                    holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary));
                }
                holder.btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"按钮点击"+position,Toast.LENGTH_SHORT).show();
                    }

                });
                return convertView;
            }
        }

        final List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();

        map.put("title","G1");
        map.put("info","google 1");
        map.put("img", R.drawable.icon1);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","G2");
        map.put("info","google2");
        map.put("img",R.drawable.icon2);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","G3");
        map.put("info","google3");
        map.put("img",R.drawable.icon3);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("title","G4");
        map.put("info","google4");
        map.put("img",R.drawable.icon4);
        list.add(map);

        map = new HashMap<String ,Object>();
        map.put("title","g5");
        map.put("info","google5");
        map.put("img",R.drawable.icon5);
        list.add(map);

        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this,list);
        lv.setAdapter(myBaseAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,list.get(position).get("title").toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}