package l;

import android.app.*;
import android.content.*;
import android.content.SharedPreferences.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.view.WindowManager.*;
import android.widget.*;
import java.io.*;
import java.text.*;
import java.math.*;
import android.provider.*;
import android.net.*;
import android.util.*;



public class f extends Service implements OnTouchListener,OnClickListener,OnLongClickListener,Runnable
{
	WindowManager wm;
	WindowManager.LayoutParams wp;
	float x0,y0,x1,y1,x2,y2;
	TextView t;
	
	float d;
	int w,h;
	
	LayoutParams lp = new LayoutParams(
		LayoutParams.WRAP_CONTENT,
		LayoutParams.WRAP_CONTENT
	);
	int id=0;
	long lo1=0,lo2=0;
	
	int delaytime=1000;
	TextView t1,t2;
	Handler handler = new Handler();
	LinearLayout l,c1,c2,xx;
	SharedPreferences sp;
	int f,r;
	Editor edit;
	Context c;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		if (intent != null) {
            if (intent.getBooleanExtra("stop",false)) 
				stopSelf();
			id=intent.getIntExtra("",0);
            
        }
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
    public void onCreate()
    {
        super.onCreate();
		c=this;
		sp=this.getSharedPreferences("l", 0);
		f = sp.getInt("f",0);
		edit = sp.edit();
		r=f;

		DisplayMetrics dm = getResources().getDisplayMetrics();
		d = dm.density;
		if (dm.widthPixels < dm.heightPixels)
		{
			w = dm.widthPixels;
			h = dm.heightPixels;
		}
		else
		{
			h = dm.widthPixels;
			w = dm.heightPixels;
		}
		
		l=new LinearLayout(this);
		l.setOnTouchListener(this);
		l.setOnClickListener(this);
		l.setOnLongClickListener(this);
		l.setId(1);
		//l.setOrientation(LinearLayout.VERTICAL);
		l.setLayoutParams(lp);

		c1=new LinearLayout(this);
		c1.setLayoutParams(lp);
		c1.setOrientation(LinearLayout.VERTICAL);
		c1.setBackground(r(55,0x99aaaaaa));
		l.addView(c1);

		t1=new TextView(this);
		t1.setLayoutParams(lp);
		t1.setTextColor(0xffffffff);
		//t1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		//t1.setShadowLayer(01, 0.1f, 0.1f, 0xff000000);
		t1.setGravity(Gravity.CENTER);
		t1.setText("初始化。。。");
		c1.addView(t1);

		c2=new LinearLayout(this);
		c2.setLayoutParams(lp);
		l.addView(c2);

		xx=new LinearLayout(this);
		xx.setLayoutParams(new LinearLayout.LayoutParams(60,60));
		xx.setGravity(Gravity.CENTER);
		xx.setId(0);
		xx.setBackground(x());
		xx.setVisibility(View.GONE);
		xx.setOnClickListener(this);
		c2.addView(xx);

		wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
		wp = new WindowManager.LayoutParams();
		wp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
		wp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;	
		wp.gravity = Gravity.LEFT | Gravity.TOP; 
		wp.x = 0;
		wp.y = h/30;
		wp.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		wp.format = PixelFormat.RGBA_8888;

		

		try{
			wm.addView(l, wp);
		}catch(Exception e){
			startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
									Uri.fromParts("package",getPackageName(),null)));
			Toast.makeText(this,"请允许出现在其它应用上！",50).show();
		}

		handler.postDelayed(this, delaytime);
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case 0:
				stopSelf();
				break;
			case 1:
				xx.setVisibility(View.GONE);
				if (r==0) {
					edit.putInt("f",1).commit();
					r=1;
					d();
				} else if(r==1){
					edit.putInt("f",0).commit();
					r=0;
					d();
				}
				break;
		}
		
	}

	@Override
	public boolean onLongClick(View v)
	{
		xx.setVisibility(View.VISIBLE);
		return true;
	}

	@Override
	public void run()
	{
		d();
		handler.postDelayed(this, delaytime);
		wm.updateViewLayout(l, wp);
	}

	@Override
	public boolean onTouch(View v, MotionEvent e) {
		x1 = e.getRawX();
		y1 = e.getRawY() ; 

		switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				x2=x1;
				y2=y1;
				x0 = e.getX();
				y0 = e.getY();

				break;
			case MotionEvent.ACTION_MOVE:
				wp.x = (int) (x1 - x0);
				wp.y = (int) (y1 - y0);
				wm.updateViewLayout(l, wp);
				break;
			case MotionEvent.ACTION_UP:
				wp.x = (int) (x1 - x0);
				wp.y = (int) (y1 - y0);
				wm.updateViewLayout(l, wp);

				if(x2-x1==0&&x2-x1==0)
				{
					
				}
				x0 = y0 = 0;
				break;
		}
		return false;
	}
	
	private Drawable x()
	{
		int o=60,x=10,y=o-x;
		Bitmap b=Bitmap.createBitmap(o,o, Bitmap.Config.ARGB_8888);
		Canvas v=new Canvas(b);
		Paint p =new Paint(),pa=new Paint();
		
		pa.setColor(Color.parseColor("#ff5555"));
		v.drawOval(new RectF(0, 0, o,o), pa);
		
		p.setColor(Color.WHITE);
		p.setStrokeWidth(2);
		v.drawLine(y, x, x, y, p);
		v.drawLine(x, x, y, y, p);
		
		Drawable d = new  BitmapDrawable(b) ;
		
		return d;
	}

	private Drawable r(int r,int c)
	{
		int p=8;
		ShapeDrawable sd = new ShapeDrawable(new RoundRectShape(new float[] {r,r,r,r,r,r,r,r},null,null));
		sd.getPaint().setColor(c);
		sd.setPadding(p+6,p,p,p);
		return sd;
	}
	private int getStatusBarHeight() {
		Resources resources = this.getResources();
		int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
		int height = resources.getDimensionPixelSize(resourceId);

		return height;
	}

	public void d() {
		switch(id)
		{
			case 0:
				switch(r){
					case 0:
						t1.setText("剩余:" + mu(c) + "M"+"\n"+"总共:" + mt() + "M");

						break;
					case 1:
						BigDecimal bd = new BigDecimal((double)mu(this)/1024f).setScale(2,4);
						float ft = bd.floatValue(); 
						BigDecimal b = new BigDecimal((double)mt()/1024f).setScale(2,4);
						float f = b.floatValue(); 

						t1.setText("剩余:" + ft+ "G"+"\n"+"总共:" + f+ "G");

						break;

				}
				break;
			case 1:
				long l01=TrafficStats.getTotalTxBytes(),
				l02=TrafficStats.getTotalRxBytes(),
				l1=0,l2=0;
				if(lo1==0)
				{
					lo1=l01;
					lo2=l02;
				}
				else 
				{
					l1=l01-lo1;
					lo1=l01;
					l2=l02-lo2;
					lo2=l02;
				}
				t1.setText(
					"▲"+new BigDecimal((double)l1/1024).setScale(2,4)+"K/s"
					+"\n"+
					"▼"+new BigDecimal((double)l2/1024).setScale(2,4)+"K/s"
				
				);
				break;
		}
		

	}
	@Override
	public void onDestroy() {
		handler.removeCallbacks(this);
		wm.removeView(l);
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}	
	public static long mu(Context mContext) {
		long MEM_UNUSED;
		ActivityManager am = (ActivityManager) mContext
			.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(mi);
		MEM_UNUSED = mi.availMem / 1024/1024;
		return MEM_UNUSED;
	}

	public static long mt() {
		long mTotal;
		// 系统内存
		String path = "/proc/meminfo";
		// 存储器内容
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path), 8);
			String line;
			if ((line = br.readLine()) != null) {
				// 采集内存信息
				content = line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// beginIndex
		int begin = content.indexOf(':');
		// endIndex
		int end = content.indexOf('k');
		// 采集数量的内存
		content = content.substring(begin + 1, end).trim();
		// 转换为Int型
		mTotal = Integer.parseInt(content)/1024;	
		return mTotal;
	}	
}


