package l;
import android.app.*;
import android.os.*;
import android.content.*;

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
