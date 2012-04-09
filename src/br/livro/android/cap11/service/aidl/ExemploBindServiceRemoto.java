package br.livro.android.cap11.service.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import br.livro.android.cap11.R;

/**
 * Exemplo de utilização da interface AIDL.
 * 
 * Note a implementação do método onServiceConnected:
 * 
 * public void onServiceConnected(ComponentName className, IBinder service) {
 * //recupera a interface para interagir com o service contador =
 * ContadorRemoto.Stub.asInterface(service); }
 */
public class ExemploBindServiceRemoto extends Activity implements ServiceConnection {
	private static final String CATEGORIA = "livro";
	private ContadorRemoto contador;

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
				Class classeServico = ServicoComConexaoRemota.class;
				Intent it = new Intent(ExemploBindServiceRemoto.this, classeServico);
				bindService(it, conexao, Context.BIND_AUTO_CREATE);
			}
		});

		// Parar o serviço, usa a conexão
		Button bParar = (Button) findViewById(R.id.btParar);
		bParar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i(CATEGORIA, "Parar Service - unbindService");
				unbindService(conexao);
			}
		});

		// Ver o retorno do método "count" da interface do serviço
		Button b = (Button) findViewById(R.id.btContador);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				int count;
				try {
					count = contador.count();
					Log.i(CATEGORIA, "Contador: " + count);
					Toast.makeText(ExemploBindServiceRemoto.this, "Contador: " + count, Toast.LENGTH_SHORT).show();
				} catch (RemoteException e) {
					// DeadObjectException: pode acontecer se o o processo que criou o objeto não existe mais
					e.printStackTrace();
				}
			}
		});
	}

	public void onServiceConnected(ComponentName className, IBinder service) {
		// Recupera a interface para interagir com o serviço
		contador = ContadorRemoto.Stub.asInterface(service);
	}

	public void onServiceDisconnected(ComponentName className) {
		contador = null;
	}
}
