package fr.enkirche.sissll;

/**
 * Created by Emmanuel on 19/07/2015 à partir de http://instinctcoder.com/android-studio-sqlite-database-example/.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView article_Id;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,ArticleDetail.class);
            intent.putExtra("article_Id",0);
            startActivity(intent);

        }else {

            ArticleRepo repo = new ArticleRepo(this);

            ArrayList<HashMap<String, String>>articleList =  repo.getArticleList();
            if(articleList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        article_Id = (TextView) view.findViewById(R.id.article_Id);
                        String articleId = article_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),ArticleDetail.class);
                        objIndent.putExtra("article_Id", Integer.parseInt( articleId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(
                        MainActivity.this,articleList,
                        R.layout.view_article_entry,
                        new String[] { "id","denom"},
                        new int[] {R.id.article_Id, R.id.article_denom});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"Pas d'article!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }

}