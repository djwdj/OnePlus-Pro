package l.o;

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



public class f extends Service 
{
	WindowManager wm;
	WindowManager.LayoutParams wmParams;
	float x0, y0,x,y,x2,y2;
	int state;
	int delaytime=1000;
	TextView t1,t2;
	Handler handler = new Handler();
	LinearLayout l,c1,c2,xx;
	SharedPreferences sp;
	int f,r;
	Editor edit;
	Context c;
	boolean b=false;
	Runnable task=new Runnable() {
		public void run() {
			d();
			handler.postDelayed(this, delaytime);
			wm.updateViewLayout(l, wmParams);
		}
	};
	@Override
    public void onCreate()
    {
        super.onCreate();
		c=this;
		sp=this.getSharedPreferences("l", 0);
		f = sp.getInt("f",0);
		edit = sp.edit();
		r=f;
		l=new LinearLayout(this);
		l.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams lp = new LayoutParams(
			LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT
		);
		l.setLayoutParams(lp);

		c1=new LinearLayout(this);
		c1.setLayoutParams(lp);
		c1.setOrientation(LinearLayout.VERTICAL);
		c1.setBackground(r(55,0x99aaaaaa));
		l.addView(c1);

		t1=new TextView(this);
		t1.setLayoutParams(lp);
		t1.setTextColor(Color.WHITE);
		t1.setGravity(Gravity.CENTER);
		d();
		c1.addView(t1);

		c2=new LinearLayout(this);
		c2.setLayoutParams(lp);
		l.addView(c2);

		xx=new LinearLayout(this);
		xx.setLayoutParams(new LinearLayout.LayoutParams(60,60));
		xx.setGravity(Gravity.CENTER);
		
		xx.setBackground(x());
		xx.setVisibility(View.GONE);
		xx.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent serviceStop = new Intent();
					serviceStop.setClass(c, f.class);
					stopService(serviceStop);

				}
			});
		c2.addView(xx);


		wm = (WindowManager) getApplicationContext().getSystemService("window");
		// 设置LayoutParams(全局变量）相关参数
		wmParams = new WindowManager.LayoutParams();
		wmParams.type = 2003;
		wmParams.flags |= 8;	//焦点 20不能点
		wmParams.gravity = Gravity.LEFT | Gravity.TOP; // 调整悬浮窗口至左上角
		// 以屏幕左上角为原点，设置x、y初始值
		wmParams.x = 0;
		wmParams.y = 0;
		// 设置悬浮窗口长宽数据
		wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.format = 1;	//透明

		wm.addView(l, wmParams);

		l.setOnTouchListener(new OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					// 获取相对屏幕的坐标，即以屏幕左上角为原点
					x = event.getRawX();
					y = event.getRawY()-getStatusBarHeight() ; // 系统状态栏的高度


					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							state = MotionEvent.ACTION_DOWN;
							x2 = x;
							y2 = y;
							// 获取相对View的坐标，即以此View左上角为原点
							x0 = event.getX();
							y0 = event.getY();
							break;
						case MotionEvent.ACTION_MOVE:
							state = MotionEvent.ACTION_MOVE;
							wmParams.x = (int) (x - x0);
							wmParams.y = (int) (y - y0);
							wm.updateViewLayout(l, wmParams);
							if(Math.abs(x - x2) > 2 && Math.abs(y - y2) > 2){

								xx.setVisibility(View.GONE);
							}
							break;

						case MotionEvent.ACTION_UP:
							state = MotionEvent.ACTION_UP;
							wmParams.x = (int) (x - x0);
							wmParams.y = (int) (y - y0);
							wm.updateViewLayout(l, wmParams);

							x0 = y0 = 0;
							break;
					}
					return false;
				}
			});
		l.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
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
				}
			});
		l.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					xx.setVisibility(View.VISIBLE);
					return true;
				}
			});

		handler.postDelayed(task, delaytime);
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

	}
	@Override
	public void onDestroy() {
		handler.removeCallbacks(task);
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


