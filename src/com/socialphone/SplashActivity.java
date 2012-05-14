package com.socialphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class SplashActivity extends Activity 
{
    /** Called when the activity is first created. */
	
	String saddr;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        setContentView(R.layout.splash);  

        Bundle extras = getIntent().getExtras(); 
        

        if (extras != null) {
        	saddr = extras.getString("saddr");
            // and get whatever type user account id is
        }
        
        
        Handler x = new Handler();  
        x.postDelayed(new splashhandler(), 2000);          
        
    }
    
    class splashhandler implements Runnable
    {  
    	  public void run() {  
    		  Intent intent = new Intent(getApplication(), BTTabMenu.class);
    		  intent.putExtra("saddr", saddr);
    	      startActivity(intent);  
    	     SplashActivity.this.finish();  
    	  }  
    }  
    
}
 
   
