package l;

import android.app.*;
import android.content.*;
import android.content.SharedPreferences.*;
import android.content.pm.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import java.io.*;
import java.util.*;
import android.text.*;
import android.text.style.*;
import l.o.*;

public class l extends Activity implements OnClickListener,OnLongClickListener
{
	
	int p,w,h,m;
	float d,ts;
	
	SharedPreferences sp;
	int s,z,r,s0,z0,r0,su,su0;
	Editor edit;
	Context c;
	ScrollView s1,s2,s3;
	LinearLayout l,l1,l2,l3,l31,l4,l41,l42,l6,l7,l8,l9,l11,l12;
	ll l30;
	Button b,pro;
	TextView t;
	EditText e;
	
	LayoutParams ll = new LayoutParams(
		LayoutParams.MATCH_PARENT,
		LayoutParams.WRAP_CONTENT,1);
		
	LayoutParams lp = new LayoutParams(
		LayoutParams.MATCH_PARENT, 
		LayoutParams.WRAP_CONTENT, 1);
		
	Animation aa;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		getWindow().setStatusBarColor(0);
		getWindow().setNavigationBarColor(0);
		
		DisplayMetrics dm = getResources().getDisplayMetrics();
		w = dm.widthPixels;
		h=dm.heightPixels;
		d = dm.density;
		p=w/36;
		m=w/120;
		ts=w/d/24;
		
		c=this;
		
		sp=getSharedPreferences("l", 0);
		
		s0 = sp.getInt("s",0);
		z0 = sp.getInt("z",0);
		r0 = sp.getInt("r",0);
		su =sp.getInt("su",0);
		su0 =sp.getInt("su0",0);
		edit = sp.edit();
		
		s=s0!=0?s0:0xffff5555;
		z=z0!=0?z0:0xffffffff;
		r=r0!=0?0:w/16;
		
		lp.setMargins(m,m,m,m);
		
		l=new LinearLayout(this);
		l.setPadding(p,p,p,p);
		l.setOrientation(LinearLayout.VERTICAL);
		l.setGravity(Gravity.CENTER);
		//l.setId(99);
		l.setOnClickListener(this);
		
		
		aa=new AlphaAnimation(0.1f, 1);
		aa.setDuration(500);
		l.startAnimation(aa);
		
		l1=new LinearLayout(this);
		l1.setOrientation(LinearLayout.VERTICAL);
		l1.setGravity(Gravity.CENTER);
		l1.setBackground(d(r!=0?0xbbcccccc:0xffcccccc,r));
		l1.setPadding(p,p,p,p);
		l.addView(l1);
		
		l2=new LinearLayout(this);
		l2.setOrientation(LinearLayout.HORIZONTAL);
		l2.setGravity(Gravity.CENTER);
		l2.setId(0);
		l2.setOnClickListener(this);
		l1.addView(l2);
		
		
		l3=new LinearLayout(this);
		l3.setOrientation(LinearLayout.VERTICAL);
		l3.setGravity(Gravity.CENTER);
		l3.setPadding(0,m,0,0);
		l1.addView(l3);
		
		s1=new ScrollView(this);
		s1.setFillViewport(true);
		s1.setLayoutParams(new LayoutParams(-1,h/2));
		l3.addView(s1);
		
		l30=new ll(this);
		l30.set(3,m,m);
		s1.addView(l30);
		
		l31=new LinearLayout(this);
		l3.addView(l31);
		
		t();
		
		l6=new LinearLayout(this);
		l6.setOrientation(LinearLayout.VERTICAL);
		l6.setGravity(Gravity.CENTER);
		l6.setPadding(20,20,20,20);
		l6.setOnClickListener(this);
		l6.setLayoutParams(ll);
		l6.setVisibility(View.GONE);
		l1.addView(l6);
		
		t(l6,"主题颜色");
		
		l7=new LinearLayout(this);
		l(l7);
		l6.addView(l7);
		
		l8=new LinearLayout(this);
		l(l8);
		l6.addView(l8);
		
		t(l6,"字体颜色");
		
		l9=new LinearLayout(this);
		l(l9);
		l6.addView(l9);
		
		t(l6,"高级设置");
		l12=new LinearLayout(this);
		ab(l12,su!=1?"启用ROOT":"停用ROOT",4);
		ab(l12,r0!=1?"经典直角":"经典圆角",5);
		l6.addView(l12);
		
		t(l6,"关于本软件");
		l11=new LinearLayout(this);
		l(l11);
		l6.addView(l11);
		
		ab(l11,"更新",1);
		ab(l11,"反馈",2);
		ab(l11,"捐赠",3);
		try
		{
			t(su==1?"超强模式":"标准模式");
			pro();
			t("v" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName+"\n"+Build.DEVICE+"/"+Build.VERSION.SDK_INT);
			
		}catch (PackageManager.NameNotFoundException e){}
		
		b("自启管理","com.oneplus.security",".chainlaunch.view.ChainLaunchAppListActivity");
		b("开机自启","com.oneplus.security",".autorun.AutorunMainActivity");
		b("后台冻结","com.oneplus.security",".cleanbackground.view.ManageBackgroundAppListActivity");
		b("网络控制","com.oneplus.security",".firewall.NetworkRestrictActivity");
		b("短信拦截","com.oneplus.security",".intercept.MmsInterceptPreferenceActivity");
		b("应 用 锁","com.oneplus.security", ".applocker.AppLockerSettingsActivity");
		b("垃圾清理","com.oneplus.security",".clean.oos.FakeCleanActivity");
		b("权限控制","com.oneplus.security",".oppermission.OPPermissionMainActivity");
		b("默认应用","com.oneplus.security",".defaultapp.DefaultAppListActivity");
		
		if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O)
		{
			b("工程模式","com.android.engineeringmode",".EngineeringMode");
			b("工程测试","com.android.engineeringmode",".manualtest.ManualTest");
			b("硬件资料","com.android.engineeringmode",".manualtest.DeviceListActivity");
		}
		else
		{
			b("工程模式","com.oneplus.factorymode",".EngineeringMode");
			b("工程测试","com.oneplus.factorymode",".manualtest.ManualTest");
			b("硬件资料","com.oneplus.factorymode",".manualtest.DeviceListActivity");
		}
		
		b("系统服务","com.android.settings",".Settings$AccessibilitySettingsActivity");
		b("砖家选项","com.android.settings",".Settings$DevelopmentSettingsActivity");
		b("快捷支付","com.android.settings",".Settings$OPQuickPaySettingsActivity");
		b("触碰付款","com.android.settings",".Settings$PaymentSettingsActivity");
		b("安装权限","com.android.settings",".Settings$ManageExternalSourcesActivity");
		b("高级优化","com.android.settings",".Settings$BgOptimizeSwitchActivity");
		b("解锁方式","com.android.settings",".ChooseLockGeneric");
		b("浮窗管理","com.android.settings",".Settings$OverlaySettingsActivity");
		b("设备管理","com.android.settings",".Settings$DeviceAdminSettingsActivity");
		b("用户管理","com.android.settings",".Settings$UserSettingsActivity");
		b("夜间模式","com.android.settings",".Settings$OPNightModeActivity");
		b("阅读模式","com.android.settings",".Settings$OPReadingModeActivity");
		b("通知免扰","com.android.settings",".Settings$ZenModeVisualInterruptionSettingsActivity");
		b("游戏免扰","com.android.settings",".Settings$OPGamingModeActivity");
		b("V R 助手","com.android.settings",".Settings$VrListenersSettingsActivity");
		b("无线投屏","com.android.settings",".Settings$WifiDisplaySettingsActivity");
		
		
		o("s",0xff000000,l7);
		o("s",0xff333333,l7);
		o("s",0xff666666,l7);
		o("s",0xff999999,l7);
		o("s",0xffcccccc,l7);
		o("s",0xffffffff,l7);
		
		o("s",0xffff5555,l8);
		o("s",0xff2196F3,l8);
		o("s",0xff4CAF50,l8);
		o("s",0xffeeee33,l8);
		o("s",0xffFF9800,l8);
		o("s",0xff673AB7,l8);
		
		o("z",0xffffffff,l9);
		o("z",0xffcccccc,l9);
		o("z",0xff999999,l9);
		o("z",0xff666666,l9);
		o("z",0xff333333,l9);
		o("z",0xff000000,l9);
		
		addContentView(l,new WindowManager.LayoutParams());
		

//		if(su0==0){
//			//su("echo su");
//			Toast.makeText(this,"欢迎使用OnePlusPro!\n开启ROOT效果更佳！",500).show();
//			edit.putInt("su0",1).commit();
//		}
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Shortcut();
        }
    }

	@Override
	protected void onRestart()
	{
		l.setBackground(getWallpaper());
		super.onRestart();
	}
	
	
	@Override
	public boolean onLongClick(View v)
	{
		switch (v.getId())
		{
			case 0:
				Toast("欢迎使用基哥科技");
		}
		
		return false;
		
	}
	@Override
	public void onClick(View v)
	{
		switch(v.getId()){
			case 0:
				if(s1.isShown()){
					l3.setVisibility(View.GONE);
					l6.setVisibility(View.VISIBLE);
					l1.setBackground(d(0xffaaaaaa,r));
				}else recreate();
				
				break;
			case 1:
				cool(getPackageName());
				break;
			case 2:
				Intent intent = new Intent();
				intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3DX_wurB9Wk8d0fjn0kwJPgkwGCRiUwp2l"));
				startActivity(intent);
				break;
			case 3:
				try
				{
					String s;
					//s="alipays://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https://qr.alipay.com/tsx03791nki4qabwu92vi97";
					s="YWxpcGF5czovL3BsYXRmb3JtYXBpL3N0YXJ0YXBwP3NhSWQ9MTAwMDAwMDcmY2xpZW50VmVyc2lvbj0zLjcuMC4wNzE4JnFyY29kZT1odHRwczovL3FyLmFsaXBheS5jb20vdHN4MDM3OTFua2k0cWFid3U5MnZpOTc";
					s=new String(android.util.Base64.decode(s,android.util.Base64.DEFAULT));
					startActivity(new Intent(null,Uri.parse(s)));

				}
				catch(Exception e)
				{
					Toast("打开支付宝失败");
				}
				
				break;
			case 4:
				
				if (su != 0)edit.remove("su").commit();
				else
				{
					if (r("echo su").equals("su"))
						edit.putInt("su", 1).commit();
				}
				recreate();
				break;
			case 5:
				if(r0!=1)edit.putInt("r",1).commit();
				else edit.remove("r").commit();
				recreate();
				break;
			case 6:
				if(r("echo 基哥科技").equals("基哥科技"))
				{
					if(su!=1)edit.putInt("su", 1).commit();
					
					l3.setVisibility(View.GONE);
					s2.setVisibility(View.VISIBLE);

					rt();
					e.setText(r("getprop net.hostname"));
					((TextView)findViewById(9)).setText(r("getenforce").equals("Enforcing")?"试试关闭":"试试开启");
					
				}else
				{
					Toast("请授权ROOT后再试试！\n没有ROOT不用再试。");
				}
				
				break;
			case 7:
				s("reboot \"dm-verity enforcing\"");
				break;
			case 8:
				s("setprop net.hostname "+e.getText());
				Toast("修改为："+r("getprop net.hostname"));
				break;
			case 9:
				
				if(r("getenforce").equals("Enforcing"))
					s("setenforce 0");
				else
					s("setenforce 1");
					
				((TextView)findViewById(9)).setText(r("getenforce").equals("Enforcing")?"试试关闭":"试试开启");
				
				break;
			case 10:
				l3.setVisibility(View.GONE);
				s2.setVisibility(View.VISIBLE);

				yt();
				
				break;
			case 11:
				startActivity(new Intent(this,ff.class).putExtra("",0));
				break;
			case 12:
				startActivity(new Intent(this,ff.class).putExtra("",1));
				break;
			default:finish();
			
		}

		
	}
	void l()
	{
		l41=new LinearLayout(this);
		l41.setPadding(p,p,p,p);
		l41.setLayoutParams(lp);
		l41.setBackground(d(s,r));
		l41.setOrientation(LinearLayout.VERTICAL);
		l41.setGravity(Gravity.CENTER);
		l4.addView(l41);
	}
	
	void t()
	{
		b=new Button(this);
		b(b,"普通工具");
		b.setId(10);
		b.setOnClickListener(this);
		l31.addView(b);
		
		b=new Button(this);
		b(b,"ROOT工具");
		b.setId(6);
		b.setOnClickListener(this);
		l31.addView(b);
		
		s2=new ScrollView(this);
		s2.setVisibility(View.GONE);
		l1.addView(s2);

		l4=new LinearLayout(this);
		//l4.setPadding(0,0,0,20);
		l4.setOrientation(LinearLayout.VERTICAL);
		l4.setGravity(Gravity.CENTER);
		s2.addView(l4);

		
	}

	void yt()
	{
		t(l4,"小工具");

		l();

		t(sb("内存浮窗","数据仅供参考，切勿当真！(长按浮窗可关闭)"));

		bn(11,"开启",l41);
		
		l();

		t(sb("流量浮窗","数据仅供参考，切勿当真！(长按浮窗可关闭)"));

		bn(12,"开启",l41);
	}
	
	void rt()
	{
		t(l4,"ROOT工具");

		l();

		t(sb("消除刷机错误提示","开机的第二个5秒红字等候"));

		bn(7,"重启试试",l41);

		l();

		t(sb("修改网络主机名","试试不一定能用"));

		e=new EditText(this);
		e.setGravity(Gravity.CENTER);
		e.setTextSize(ts);
		e.setHint("在这输入！");
		e.setBackground(d(0xffeeeeee,r));
		l41.addView(e);

		bn(8,"修改试试",l41);

		l();

		t(sb("SELinux","听说关闭了手机会出错，不懂慎用！大神随意！"));

		bn(9,"关闭",l41);
		
	}
	
	SpannableStringBuilder sb(String b,String s)
	{
		SpannableStringBuilder sb = new SpannableStringBuilder();
		sb.append(b);
		sb.append("\n");
		sb.append(s);
		sb.setSpan(new RelativeSizeSpan(1.6f),0,b.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sb.setSpan(new StyleSpan(Typeface.BOLD), 0, b.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sb.setSpan(new RelativeSizeSpan(0.8f),b.length()+1,b.length()+1+s.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sb.setSpan(new StyleSpan(Typeface.ITALIC), b.length()+1,b.length()+1+s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		return sb;
	}

	private void l(LinearLayout l)
	{
		l.setOrientation(LinearLayout.HORIZONTAL);
		l.setGravity(Gravity.CENTER);
		l.setLayoutParams(ll);
	}

	private void ab(LinearLayout l, String n,int i)
	{

		Button b=new Button(this);
		b.setId(i);
		b.setOnClickListener(this);
		b(b,n);
		l.addView(b);
	}

	private void Shortcut()
	{
		ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
		ShortcutInfo 
			s4 = new ShortcutInfo.Builder(this, "id4")
			.setShortLabel("内存浮窗")
			.setIcon(Icon.createWithResource(this, R.drawable.l))
			.setIntent(new Intent(Intent.ACTION_MAIN).setClass(this, ff.class))
			.build(),
			s3 = new ShortcutInfo.Builder(this, "id3")
			.setShortLabel("权限管理")
			.setIcon(Icon.createWithResource(this, R.drawable.l))
			.setIntent(new Intent(Intent.ACTION_VIEW).setClassName("com.oneplus.security", "com.oneplus.security.oppermission.OPPermissionMainActivity"))
			.build(),
			s2 = new ShortcutInfo.Builder(this, "id2")
			.setShortLabel("网络管理")
			.setIcon(Icon.createWithResource(this, R.drawable.l))
			.setIntent(new Intent(Intent.ACTION_VIEW).setClassName("com.oneplus.security", "com.oneplus.security.firewall.NetworkRestrictActivity"))
			.build(),
			s1 = new ShortcutInfo.Builder(this, "id1")
			.setShortLabel("自启管理")
			.setIcon(Icon.createWithResource(this, R.drawable.l))
			.setIntent(new Intent(Intent.ACTION_VIEW).setClassName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"))
			.build();
		shortcutManager.setDynamicShortcuts(Arrays.asList(s4,s3,s2,s1));
	}
	
	public void cool(String s)
	{
		Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(String.format("https://www.coolapk.com/apk/%s",s)));
		try
		{
			startActivity(i.setPackage("com.coolapk.market"));
		}
		catch (Exception e)
		{
			startActivity(i.setPackage(null));
		}
	}
//	static String su(String su){
//		String s=;
//		try{
//			java.lang.Process p = Runtime.getRuntime().exec("su");
//			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
//			w.write(su+"\nexit\n");
//			w.flush();
//			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			StringBuilder i = new StringBuilder();
//			while (( s= r.readLine()) != null)
//			{
//				i.append(s+"\n");
//			}
//			r.close();
//			w.close();
//			p.destroy();
//
//			s=i.toString().trim();
//
//		}catch (IOException e){}
//
//		return s;
//	}
	static java.lang.Process pc;
	static void s(String s)
	{
		try
		{
			if (pc == null)pc = Runtime.getRuntime().exec("su");
			OutputStream o=pc.getOutputStream();
			o.write((s + "\n").getBytes());
			o.flush();

		}
		catch (IOException e)
		{}
	}
	static String r(String s)
	{
		s(s);
		try
		{
			byte[] b = new byte[40960];
			s = new String(b, 0, pc.getInputStream().read(b));
			if (s.length() == 4096)
				s+= new String(b, 0, pc.getInputStream().read(b));

		}
		catch(IOException e){}
		
		return s.trim();
	}
	
	
	private void o(final String s,final int i,LinearLayout l){
		LinearLayout o=new LinearLayout(this);
		int m=8,ll=120;
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ll, ll);
		lp.setMargins(m,m,m,m);
		
		o.setLayoutParams(lp);
		o.setBackground(o(i));
		o.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					edit.putInt(s,i).commit();
					if(s.equals("s")){
						pro.setBackground(d(i,r));
					}else{
						pro.setTextColor(i);
					}
					//recreate();
					
				}
			});
		l.addView(o);
		
	}

	private void pro(){
		pro=new Button(this);
		b(pro,"One Plus Pro");
		pro.setId(0);
		pro.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		pro.setOnClickListener(this);
		pro.setOnLongClickListener(this);
		l2.addView(pro);
	}
	
	void t(CharSequence s)
	{
		t=new TextView(this);
		t.setGravity(Gravity.CENTER);
		t.setTextColor(z);
		t.setTextSize(ts);
		t.setText(s);
		l41.addView(t);
	}
	
	private void t(LinearLayout l,String n){
		TextView t=new TextView(this);
		t.setText(n);
		t.setTextSize(20);
		t.setBackground(d(0xff333333,r));
		t.setPadding(6,6,6,6);
		t.setGravity(Gravity.CENTER);
		t.setTextColor(Color.WHITE);
		t.setLayoutParams(lp);

		l.addView(t);
	}
	
	void t(String n){
		TextView t=new TextView(this);
		t.setText(n);
		//t.setPadding(20,0,0,0);
		t.setGravity(Gravity.CENTER);
		t.setTextColor(Color.BLACK);
		t.setLayoutParams(lp);
		
		l2.addView(t);
	}

	void bn(int i,String n,LinearLayout l)
	{
		b=new Button(this);
		b.setText(n);
		b.setTextSize(ts);
		b.setTextColor(z);
		//b.setGravity(Gravity.CENTER);
		b.setPadding(p,p,p,p);
		b.setBackground(d(0,r));
		b.setLayoutParams(lp);
		b.setId(i);
		b.setOnClickListener(this);
		l.addView(b);
	}
	private void b(Button b,String n)
	{
		b.setText(n);
		b.setTextSize(ts);
		b.setTextColor(z);
		//b.setGravity(Gravity.CENTER);
		b.setPadding(p,p,p,p);
		b.setBackground(d(s,r));
		b.setLayoutParams(lp);

		
	}
	private void b(final String n,final String i,final String ii)
	{
		Button b=new Button(this);
		
		b(b,n);
		b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if(su==1){
						s("am start -n "+i+"/"+ii.toString().replace("$","\\$"));
					}else{
					try{
					Intent int1 = new Intent();
					int1.setClassName(i, i+ii);
					startActivity(int1);
					}catch(Exception e){
						Toast.makeText(c,"功能可能被官方遗弃了！ \nROOT可能！ ",500).show();
					}
					}
				}
			});
		b.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					a(n,R.drawable.l,new Intent(Intent.ACTION_MAIN).setClassName(i, i+ii));
					
					Toast.makeText(c,"桌面快捷创建中！",50).show();
					return true;
				}
			});
		l30.addView(b);
	}

	void a(String n, int i, Intent I)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
			getSystemService(ShortcutManager.class)
				.requestPinShortcut(new ShortcutInfo.Builder(this, n)
									.setIcon(Icon.createWithResource(this, i))
									.setShortLabel(n)
									.setIntent(I)
									.build(), null);
		else sendBroadcast(new Intent("com.android.launcher.action.INSTALL_SHORTCUT")
						   .putExtra(Intent.EXTRA_SHORTCUT_NAME, n)
						   .putExtra(Intent.EXTRA_SHORTCUT_ICON,
									 BitmapFactory.decodeResource(getResources(), i))
						   .putExtra(Intent.EXTRA_SHORTCUT_INTENT, I));
	}
	
	@Override
	public void finish()
	{
		if(s1.isShown())
		{
			new Handler().postDelayed(new Runnable(){  
					public void run() {  
						f();
					}  
				}, 400);  
			aa=new AlphaAnimation(1, 0);
			aa.setDuration(500);
			l.startAnimation(aa);

		}else recreate();
		
	}
	
	void f(){super.finish();}

	void Toast(CharSequence s)
	{
		Toast.makeText(this,s,50).show();
	}
	

	
	Drawable d(int c,int r)
	{
		GradientDrawable d=new GradientDrawable();
		d.setColor(c);
		d.setCornerRadius(r);
		d.setStroke(2,0xffffffff);
		return d;
	}
	Drawable o(int c)
	{
		GradientDrawable o=new GradientDrawable();
		o.setShape(GradientDrawable.OVAL);
		o.setColor(c);
		return o;
		
	}
}

class ll extends ViewGroup
{
	int n=2,H,V,W;

	ll(Context c){super(c);}

	public
	void set(int n, int h, int v)
	{
		this.n = n;
		this.H = h;
		this.V = v;
	}

	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
		W = maxWidth - getPaddingLeft() - getPaddingRight();
        int x = 0;
        int y = 0;
        int row = 0;
        for (int i = 0; i < getChildCount(); i++)
		{
			View v = getChildAt(i);
            if (v.getVisibility() != View.GONE)
			{
                v.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                int height = v.getMeasuredHeight();
                x += 1;
                y = row * height + height;
                if (x > n)
				{
                    x = 1;
                    row++;
                    y = row * height + height;
                }
            }
        }
		y += (row + 2) * V+getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(maxWidth, y);
    }

	@Override
	protected void onLayout(boolean p1, int l, int t, int r, int b)
	{
		l = getPaddingLeft();
		t = getPaddingTop();
		int x = l;
        int y = 0;
        int row = 1;
        for (int i = 0; i < getChildCount(); i++)
		{
			View v = getChildAt(i);
            if (v.getVisibility() != View.GONE)
			{
                int width = (W - H) / n - H;
                int height = v.getMeasuredHeight();
                x += width + H;
                y = row * (height + V)+t;
                if (x > l + W)
				{
                    x = l + H + width;
                    row++;
                    y = row * (height + V)+t;
                }
				v.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
						  MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
                v.layout(x - width, y - height, x, y);
            }
        }
	}
}


