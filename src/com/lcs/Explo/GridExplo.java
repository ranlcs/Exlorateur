package com.lcs.Explo;

import android.widget.TableLayout;
import android.widget.TableRow;

import android.content.Context;
import android.util.AttributeSet;
import java.io.File;
import android.content.Intent;

import java.util.Comparator;
import java.util.Arrays;
import java.util.StringTokenizer;
import android.view.View;
import android.view.KeyEvent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class GridExplo extends TableLayout implements Comparator<String>{
	private GridExplo ito = this;
	private String chemin = "/storage/sdcard0";
	private Context cont;

	public GridExplo(Context t){
		super(t);
		cont = t;
	}
	public GridExplo(Context t,AttributeSet a){
		super(t,a);
		cont = t;
	}

	public void ajouterFichiers(Context cont,String[] nomFichiers){
		removeAllViews();
		int nbLim=0;
		TableRow temp=null;
		Arrays.sort(nomFichiers);
		Arrays.sort(nomFichiers,this);
		for(int i=0;i<nomFichiers.length;i++){
			if(nbLim==0)
				temp = new TableRow(cont);
			Ecouteur ecoutTemp = new Ecouteur(nomFichiers[i]);
			ViewFichier viewTemp = new ViewFichier(cont,ecoutTemp);
			temp.addView(viewTemp);
			nbLim++;
			if(nbLim==4){
				addView(temp);
				temp=null;
				nbLim=0;
			}
		}
		if(temp!=null)
			addView(temp);
		/*for(int i=0;i<nomFichiers.length;i++)
			addView(new ViewFichier(cont,nomFichiers[i]));*/
	}

	@Override
	public int compare(String p,String d){
		int ret = 4;
		File pf = new File(chemin+File.separator+p);
		File df = new File(chemin+File.separator+d);
		if(!pf.isFile() && df.isFile())
			ret = -4;
		return ret;
	}

	public boolean back(Context t){
		int position=-1;
		int i=chemin.length()-1;
		while(i>=0){
			if(String.valueOf(chemin.charAt(i)).equals(File.separator))
				break;
			i--;
		}
		if(chemin.equals("/storage"))
			return true;
		else{
			chemin = chemin.substring(0,i);
			ajouterFichiers(t,new File(chemin).list());
			return false;
		}
	}










	//Classe interne Ecouteur pour éecouteur des viewFichier
	class Ecouteur implements View.OnClickListener{

		private String nomFichier;
		private int type;
		private String extension;
		private String nomAffiche;

		public Ecouteur(String nom){
			File temp = new File(chemin+File.separator+nom);
			if(!temp.isFile()){
				nomFichier = nom;
				nomAffiche = nom;
				type = ViewFichier.DOSSIER;
			}
			else{
				initType(nom);
			}
		}

		public void initType(String nomExt){
			nomFichier = nomExt;
			StringTokenizer s = new StringTokenizer(nomExt,".");
			nomAffiche = s.nextToken();
			extension = "tsisy";
			if(s.hasMoreTokens())
				extension = s.nextToken();
			switch(extension){
				case "mp3":case "ogg":case "wave":type=ViewFichier.AUDIO;
				break;
				case "3gp":case "mp4":case "avi":type = ViewFichier.VIDEO;
				break;
				case "pdf":case "txt":type = ViewFichier.TEXTE;
				break;
				case "html": type = ViewFichier.HTML;
				break;
				case "png":case "jpg":case "jpeg":case "bmp":type=ViewFichier.IMAGE;
				break;
				default : type = ViewFichier.INCONNUE;
			}
			if(type==ViewFichier.INCONNUE)
				nomAffiche=nomExt;
		}

		public String getNomFichier(){
			return nomAffiche;
		}
		public int getType(){
			return type;
		}

		@Override 
		public void onClick(View v){
			if(type==ViewFichier.DOSSIER){
				chemin = chemin+File.separator+nomAffiche;
				ito.ajouterFichiers(cont,new File(chemin).list());
			}
			else{
				Intent temp = new Intent(Intent.ACTION_VIEW);
				String type2=MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
				if(type2==null)
					type2="*/*";
				temp.setDataAndType(Uri.fromFile(new File(chemin+File.separator+nomFichier)),type2);
				cont.startActivity(temp);
			}
		}
	}






} 