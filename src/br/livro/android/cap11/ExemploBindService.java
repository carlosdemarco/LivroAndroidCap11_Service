package br.livro.android.cap11;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import br.livro.android.cap11.service.Contador;
import br.livro.android.cap11.service.ServicoComConexao;
import br.livro.android.cap11.service.ServicoComConexao.LocalBinder;

/**
 * Exemplo de utilização do bindService, para interagir com um Service que
 * utiliza a interface CountService
 * 
 * @author ricardo
 * 
 */
public class ExemploBindService extends Activity implements ServiceConnection {
	private static final String CATEGORIA = "livro";
	private Contador contador;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_bind);

		// A classe implementa a interface ServiceConnection
		final ServiceConnection conexao = this;

		// Iniciar o serviço
		Button bIniciar = (Button) findViewById(R.id.btIniciar);
		bIniciar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i(CATEGORIA, "Iniciar serviço - bindService");
				Class classeServico = ServicoComConexao.class;
				bindService(new Intent(ExemploBindService.this, classeServico), conexao, Context.BIND_AUTO_CREATE);
			}
		});

		// Parar o service, usa a connection
		Button bParar = (Button) findViewById(R.id.btParar);
		bParar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i(CATEGORIA, "Parar serviço - unbindService");
				unbindService(conexao);
			}
		});

		// Ver o retorno do método "count" da interface do serviço
		Button b = (Button) findViewById(R.id.btContador);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				int count = contador.count();
				Log.i(CATEGORIA, "Contador: " + count);
				Toast.makeText(ExemploBindService.this, "Contador: " + count, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void onServiceConnected(ComponentName className, IBinder service) {
		// Recupera a interface para interagir com o serviço
		LocalBinder binder = (LocalBinder) service;
		contador = binder.getContador();
	}

	public void onServiceDisconnected(ComponentName className) {
		contador = null;
	}
}
