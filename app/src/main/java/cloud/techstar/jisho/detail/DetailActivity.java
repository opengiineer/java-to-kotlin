package cloud.techstar.jisho.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import cloud.techstar.jisho.Injection;
import cloud.techstar.jisho.R;
import cloud.techstar.jisho.database.Word;
import cloud.techstar.jisho.database.WordTable;
import cloud.techstar.jisho.database.Words;

public class DetailActivity extends AppCompatActivity implements DetailContract.View{

    private ImageButton favBtn;
    private TextView meaningMn;
    private TextView headKanji;
    private TextView headHiragana;
    private TextView meaning;
    private TextView partOfSpeech;
    private TextView level;
    private TextView kanji;

    private DetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        headKanji = (TextView) findViewById(R.id.header_kanji);
        headHiragana = (TextView) findViewById(R.id.header_hiragana);
        meaning = (TextView) findViewById(R.id.detail_meaning);
        meaningMn = (TextView) findViewById(R.id.detail_meaning_mn);
        partOfSpeech = (TextView) findViewById(R.id.detail_part_of);
        level = (TextView) findViewById(R.id.detail_level);
        kanji = (TextView) findViewById(R.id.detail_kanji);

        ImageButton backBtn = findViewById(R.id.back);
        favBtn = findViewById(R.id.btnFav);
        Intent intent = getIntent();

        new DetailPresenter(
                intent.getStringExtra("word_id"),
                Injection.provideWordsRepository(getApplicationContext()),
                this);

        presenter.init();

//        if (word.getIsFavorite().equals("true"))
//            favBtn.setImageResource(R.drawable.ic_favorite_full);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//
//        favBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (word.getIsFavorite().equals("false")) {
//                    word.setIsFavorite("true");
//                    wordTable.update(word);
//                    favBtn.setImageResource(R.drawable.ic_favorite_full);
//                } else {
//                    word.setIsFavorite("false");
//                    wordTable.update(word);
//                    favBtn.setImageResource(R.drawable.ic_favorite);
//                }
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMissingWord() {

    }

    @Override
    public void setData(Words word) {
        headKanji.setText(word.getKanji());
        headHiragana.setText(word.getCharacter());
        meaning.setText("\u2022 ".concat(word.getMeaning()));
        meaningMn.setText("\u2022 ".concat(word.getMeaningMon()));
        partOfSpeech.setText(word.getPartOfSpeech());
        level.setText(word.getLevel());
        kanji.setText(word.getKanji());
    }
}
