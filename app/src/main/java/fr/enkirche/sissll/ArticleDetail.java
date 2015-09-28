package fr.enkirche.sissll;

/**
 * Created by Emmanuel on 19/07/2015 à partir de http://instinctcoder.com/android-studio-sqlite-database-example/.
 */

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ArticleDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextDenom;
    EditText editTextCode;
    EditText editTextQtite;
    private int _Article_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextDenom = (EditText) findViewById(R.id.editTextDenom);
        editTextCode = (EditText) findViewById(R.id.editTextCode);
        editTextQtite = (EditText) findViewById(R.id.editTextQtite);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Article_Id =0;
        Intent intent = getIntent();
        _Article_Id =intent.getIntExtra("article_Id", 0);
        ArticleRepo repo = new ArticleRepo(this);
        Article article = new Article();
        article = repo.getArticleById(_Article_Id);

        editTextQtite.setText(String.valueOf(article.qtite));
        editTextDenom.setText(article.denom);
        editTextCode.setText(article.code);
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            ArticleRepo repo = new ArticleRepo(this);
            Article article = new Article();
            article.qtite= Integer.parseInt(editTextQtite.getText().toString());
            article.code=editTextCode.getText().toString();
            article.denom=editTextDenom.getText().toString();
            article.article_ID=_Article_Id;

            if (_Article_Id==0){
                _Article_Id = repo.insert(article);

                Toast.makeText(this,"Nouvel Article Insere",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(article);
                Toast.makeText(this,"Article Mis a jour",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            ArticleRepo repo = new ArticleRepo(this);
            repo.delete(_Article_Id);
            Toast.makeText(this, "Article Supprime", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}