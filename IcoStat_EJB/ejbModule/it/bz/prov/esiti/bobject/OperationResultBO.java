package it.bz.prov.esiti.bobject;

/**
 * Classe che fornisce l'informazione sul risultato dell'operazione eseguita con il 
 * messaggio da visualizzare a video 
 * @author bpettazzoni
 *
 */

public class OperationResultBO {
	private String _message;
	private boolean _result;
	
	public OperationResultBO()
	{
		_message = "";
		_result = false;
	}
	
	public void set_message(String _message) {
		this._message = _message;
	}
	public String get_message() {
		return _message;
	}
	public void set_result(boolean _result) {
		this._result = _result;
	}
	public boolean get_result() {
		return _result;
	}
}
