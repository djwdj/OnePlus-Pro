package l.o;
import android.app.*;
import android.os.*;
import android.content.*;
import android.widget.*;
import android.provider.*;
import android.net.*;

public class ff extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (!Settings.canDrawOverlays(this)) {
			Toast.makeText(this, "请授权浮窗！", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
									   Uri.parse("package:" + getPackageName()));
			startActivityForResult(intent, 0);
		} else {
		startService(new Intent().setClass(this,f.class));
		}
		finish();
	}

}
