package br.livro.android.cap11.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service simples que faz bind para a interface Contador
 * 
 * @author ricardo
 * 
 */
public class ServicoComConexao extends ExemploServico implements Contador {
	private final IBinder conexao = new LocalBinder();
	private static final String CATEGORIA = "livro";
	
	// Implementação de IBinder, retorna a interface para interagir com o serviço
	public class LocalBinder extends Binder {
		public Contador getContador() {
			// Retorna o serviço SimplesBindService para a Activity
			return ServicoComConexao.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(CATEGORIA, "ServicoComConexao.onBind() conexao");
		// Retorna o Binder para a Activity utilizar
		return conexao;
	}

	/**
	 * @see br.livro.android.cap11.service.Contador#count()
	 */
	public int count() {
		// Retorna um contador
		return count;
	}
}
