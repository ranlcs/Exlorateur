package com.lcs.Explo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.KeyEvent;
import android.widget.ScrollView;
import android.widget.HorizontalScrollView;

import java.io.File;

public class ActivityPrinc extends Activity{
	private GridExplo page;
	private ScrollView scroll=null;
	private HorizontalScrollView hScroll=null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.princ_xml);
		
		page = (GridExplo) findViewById(R.id.grid);
		ajouterFichiers("/storage/sdcard0");
	}

	public void ajouterFichiers(String empl){
		File fichier = new File(empl);
		page.ajouterFichiers(this,fichier.list());
	}
	public void retour(View v){
		if(page.back(this))
			finish();
	}
}
