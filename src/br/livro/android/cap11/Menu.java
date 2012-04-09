package br.livro.android.cap11;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap11.service.aidl.ExemploBindServiceRemoto;

/**
 * Exemplos de Service em background
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {

	private static final String[] ops = new String[] {
		"Exemplo Start Service",
		"Exemplo Bind Service - Local",
		"Exemplo Bind Service - AIDL (remoto)"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, ExemploIniciarServico.class));
				break;
			case 1:
				startActivity(new Intent(this, ExemploBindService.class));
				break;
			case 2:
				startActivity(new Intent(this, ExemploBindServiceRemoto.class));
				break;
			default:
				finish();
		}
	}
}