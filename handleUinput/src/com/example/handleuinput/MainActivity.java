package com.example.handleuinput;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private String exe_chomd_dir = "chmod -R 777 /data/data/com.example.handleuinput";
	private String exe_input_event0 = "chmod 666 /dev/input/event0";
	private String exe_chomd_mutilShotAgent = "chmod -R 777 /data/data/com.example.handleuinput/libhandleUinput.so";
	private final static String TAG = "lenovoMain";
	private String[] exe_input_dir = { "chmod -R 777 /data/data/com.example.handleuinput" };
	private String[] exe_input_lib = { "chmod -R 777 /data/data/com.example.handleuinput/libhandleUinput.so" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("lenovo", "start to create.");
		// 1. execute command without su permisson operation
		// modify directory permission
		// try {
		// execCmd(exe_input_event0);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// // modify lib permission
		// try {
		// execCmd(exe_chomd_mutilShotAgent);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// 2. execute command with String Array, but without su permission
		// exec(exe_input_dir);
		// exec(exe_input_lib);
		// 3. execute command with 
		// execRootCmd(exe_chomd_dir);
		execRootCmd(exe_input_event0);
		// execRootCmd(exe_chomd_mutilShotAgent);
		OpenEvent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void OpenEvent() {
		open();
	}

	// execute command with root permission
	public static String execRootCmd(String cmd) {
		String result = "";
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			Process p = Runtime.getRuntime().exec("su");
			dos = new DataOutputStream(p.getOutputStream());
			dis = new DataInputStream(p.getInputStream());
			System.out.println(cmd);

			dos.writeBytes(cmd + "\n");
			dos.flush();
			dos.writeBytes("exit\n");
			dos.flush();

			String line = null;
			while ((line = dis.readLine()) != null) {
				result += line;
			}
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static void execCmd(final String cmd) throws IOException {

		new Thread() {

			public void run() {
				Runtime runtime = Runtime.getRuntime();
				Process process;
				try {
					process = runtime.exec(cmd);
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = null;
					while (null != (line = br.readLine())) {
						Log.d(TAG, "line = " + line);
					}
					Log.d(TAG, "line1 = " + line);
					process.waitFor();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.d(TAG, "Exception = " + cmd + "   " + e.getMessage());
					e.printStackTrace();
				}
			};
		}.start();
	}

	public static String exec(String[] args) {
		String result = "";
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		Process process = null;
		InputStream is = null;
		try {
			process = processBuilder.start();
			is = process.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = -1;
			while ((read = is.read()) != -1) {
				baos.write(read);
			}
			byte[] data = baos.toByteArray();
			result = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (process != null) {
				process.destroy();
			}
		}
		return result;
	}

	static {
		try {
			System.loadLibrary("handleUinput");

		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
		}
	}

	public native void open();
}
