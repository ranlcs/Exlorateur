package com.lcs.Explo;

import android.view.WindowManager;
import android.view.Display;
import android.view.View;
import android.view.Gravity;

import android.content.Context;
import android.util.AttributeSet;

import android.graphics.Point;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.util.StringTokenizer;
import android.content.res.TypedArray;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class ViewFichier extends LinearLayout{
	public static final int INCONNUE = 0; 
	public static final int DOSSIER = 1;
	public static final int AUDIO = 2;
	public static final int VIDEO = 3;
	public static final int TEXTE = 4;
	public static final int HTML = 5;
	public static final int IMAGE = 6;

	private int tailleX;
	private ImageView image = null;
	private TextView texte = null;

	public ViewFichier(Context t,GridExplo.Ecouteur onclick){
		super(t);
		setOrientation(LinearLayout.VERTICAL);
		setOnClickListener(onclick);
		image  = new ImageView(t);
		texte = new TextView(t);
		initView(t,onclick.getNomFichier(),onclick.getType());
		addView(image);
		addView(texte);
	}

	public void initView(Context tre,String nom,int type){
		texte.setText(nom);
		texte.setGravity(Gravity.CENTER_HORIZONTAL);
		switch(type){
			case DOSSIER:image.setImageResource(R.drawable.dossier);
			break;
			case AUDIO:image.setImageResource(R.drawable.audio);
			break;
			case INCONNUE:image.setImageResource(R.drawable.inconnue);
			break;
			case VIDEO:image.setImageResource(R.drawable.video);
			break;
			case HTML:image.setImageResource(R.drawable.html);
			break;
			case TEXTE:image.setImageResource(R.drawable.texte);
			break;
			case IMAGE:image.setImageResource(R.drawable.image);
			break;
		}
	}
}