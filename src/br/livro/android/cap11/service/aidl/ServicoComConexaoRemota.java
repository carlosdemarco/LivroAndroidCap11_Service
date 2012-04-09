package br.livro.android.cap11.service.aidl;

import android.content.Intent;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.util.Log;
import br.livro.android.cap11.service.ExemploServico;

/**
 * Service simples que faz bind para a interface ContadorRemoto
 * 
 * @author ricardo
 * 
 */
public class ServicoComConexaoRemota extends ExemploServico {
	private static final String CATEGORIA = "livro";
	
	// Implementa interface definida no arquivo *.aidl
	private final ContadorRemoto.Stub conexao = new ContadorRemoto.Stub() {
		private int count;

		// Implementa o método da interface ContadorRemoto
		public int count() throws DeadObjectException {
			count++;
			return count;
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(CATEGORIA, "ServicoComConexaoRemota.onBind() conexao");
		// Retorna o Binder para a Activity utilizar
		return conexao;
	}
}
