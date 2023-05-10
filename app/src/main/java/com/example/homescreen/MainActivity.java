package com.example.homescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  ListView lvNFT;
  CollectionAdapter nftAdapter;
  ArrayList<Collection> collectionList = new ArrayList<Collection>();

  EditText edtSearch;
  ViewFlipper banner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setControls();
    setEvents();
  }

  private void setEvents() {
    createBanner();
    initData();
    nftAdapter = new CollectionAdapter(this, R.layout.collection_layout, collectionList);
    lvNFT.setAdapter(nftAdapter);

    edtSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        nftAdapter.getFilter().filter(charSequence);
      }

      @Override
      public void afterTextChanged(Editable editable) {}
    });
  }

  private void setControls() {
    lvNFT = findViewById(R.id.lvNFT);
    edtSearch = findViewById(R.id.edtSearch);
    banner = findViewById(R.id.banner);
  }


  // Helpers
  private void initData() {
    collectionList.add(new Collection(R.drawable.img_collection_01, "Abstracto #312", "Gin", 4.24));
    collectionList.add(new Collection(R.drawable.img_collection_02, "Green Coins", "Gin", 3.12));
    collectionList.add(new Collection(R.drawable.img_collection_03, "NFT coins race", "Gin", 5));
    collectionList.add(new Collection(R.drawable.img_collection_04, "Abstracto #312", "Gin", 10));
    collectionList.add(new Collection(R.drawable.img_collection_05, "NFT purple coins", "Gin", 7));
  }

  private ImageView initItemOfBanner(int imgResource, ImageView.ScaleType scaleType) {
    ImageView img = new ImageView(getApplicationContext());
    img.setImageResource(imgResource);

    scaleType = scaleType == null ? ImageView.ScaleType.CENTER_CROP : scaleType;

    img.setScaleType(scaleType);

    return img;
  }

  private void createBanner() {
    ImageView img1 = initItemOfBanner(R.drawable.img_collection_01, ImageView.ScaleType.CENTER_CROP);
    ImageView img2 = initItemOfBanner(R.drawable.img_collection_02, ImageView.ScaleType.CENTER_CROP);
    ImageView img3 = initItemOfBanner(R.drawable.img_collection_03, ImageView.ScaleType.CENTER_CROP);
    ImageView img4 = initItemOfBanner(R.drawable.img_collection_04, ImageView.ScaleType.CENTER_CROP);
    ImageView img5 = initItemOfBanner(R.drawable.img_collection_05, ImageView.ScaleType.CENTER_CROP);

    banner.addView(img1);
    banner.addView(img2);
    banner.addView(img3);
    banner.addView(img4);
    banner.addView(img5);

    banner.setAutoStart(true);
  }

}