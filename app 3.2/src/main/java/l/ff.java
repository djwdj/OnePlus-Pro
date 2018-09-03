package l;
import android.app.*;
import android.os.*;
import android.content.*;
import android.provider.*;
import android.net.*;
import android.widget.*;

public class ff extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		startService(new Intent().setClass(this,f.class).putExtra("",getIntent().getIntExtra("",0)));
		
		finish();
	}

}
