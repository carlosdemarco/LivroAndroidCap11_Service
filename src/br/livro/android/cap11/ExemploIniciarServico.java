package br.livro.android.cap11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Exemplo de como utilizar os métodos startService(intent) e
 * stopService(intent)
 * 
 * Service está configurado com um filtro para a ação "SERVICE_1"
 * 
 * <service android:name=".service.ExemploIniciarServico"> <intent-filter>
 * <action android:name="SERVICE_1" /> <category
 * android:name="android.intent.category.DEFAULT" /> </intent-filter> </service>
 * 
 * @author ricardo
 * 
 */
public class ExemploIniciarServico extends Activity {
	private static final String CATEGORIA = "livro";

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_start_stop);

		// mesma intent é utilizada para iniciar e parar
		final Intent it = new Intent("SERVICE_1");

		Button bIniciar = (Button) findViewById(R.id.btIniciar);
		bIniciar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// iniciar o serviço
				startService(it);
			}
		});

		Button bParar = (Button) findViewById(R.id.btParar);
		bParar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// parar o serviço
				stopService(it);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(CATEGORIA, "ExemploIniciarServico.onDestroy()");
	}
}
