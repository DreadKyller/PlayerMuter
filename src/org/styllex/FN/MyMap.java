package org.styllex.FN;

public class MyMap {
	private Object[] _key;
	private Object[] _it;
	public MyMap(){
	}
	public boolean hasKey(Object ob){
		int len = this._key.length;
		for(int i=0; i<len;i++){
			if(this._key[i]==ob){
				return true;
			}
		}return false;
	}
	public boolean hasValve(Object ob){
		int len = this._it.length;
		for(int i=0; i<len;i++){
			if(this._it[i]==ob){
				return true;
			}
		}return false;
	}
	public void addKey(Object key, Object valve){
		int klen = this._key.length;
		int vlen = this._it.length;
		this._key[klen]=key;
		this._it[vlen]=valve;
	}
	public void removeKey(Object key){
		Object[] b_keys = {};
		Object[] b_valves = {};
		int len = this._key.length;
		int passive=0;
		for(int i=0;i<len;i++){
			if(!(this._key[i]==key)){
				b_keys[i-passive]=this._key[i];
				b_valves[i-passive]=this._it[i];
			}else{
				passive=(int)1;
			}
		}
	}
	public Object getByKey(Object key){
		int len = this._key.length;
		Object ret = null;
		for(int i=0;i<len;i++){
			if(this._key[i]==key){
				ret = this._it[i];
			}
		}return ret;
	}
	public Object getByValve(Object valve){
		int len = this._it.length;
		Object ret = null;
		for(int i=0;i<len;i++){
			if(this._it[i]==valve){
				ret = this._key[i];
			}
		}return ret;
	}
	public Object[] getKeys(){
		return this._key;
	}
	public Object[] getValves(){
		return this._it;
	}
}
