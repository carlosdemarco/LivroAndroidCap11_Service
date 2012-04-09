package br.livro.android.cap11.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service simples que exibe logs no método onCreate, onDestroy
 * 
 * O onCreate cria uma Thread para demonstrar um processamento em background
 * 
 * @author ricardo
 *
 */
public class ExemploServico extends Service implements Runnable {
	private static final int MAX = 50;
	private static final String CATEGORIA = "livro";
	protected int count;
	private boolean ativo;
	
	@Override
	public IBinder onBind(Intent i) {
		Log.i(CATEGORIA, "ExemploServico.onBind()");
		//null aqui nao queremos interagir com o service
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.i(CATEGORIA, "ExemploServico.onCreate()");
		ativo = true;
		//Delega para uma thread
		new Thread(this).start();
	}
	
	@Override
	public void onStart(Intent intent,int startId) {
		Log.i(CATEGORIA, "ExemploServico.onStart()");
	}
	
	@Override
	public void onDestroy() {
		//Ao encerrar o serviço, altera o flag para a thread parar
		ativo = false;
		Log.i(CATEGORIA, "ExemploServico.onDestroy()");
	}
	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (ativo && count < MAX) {
			fazAlgumaCoisa();

			Log.i(CATEGORIA, "ExemploServico executando... " + count);

			count++;
		}

		Log.i(CATEGORIA, "ExemploServico fim.");

		//Auto-Encerra o service se o contadador chegou a 10
        stopSelf();
	}
	
	private void fazAlgumaCoisa() {
		try {
			//simula algum processamento
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
