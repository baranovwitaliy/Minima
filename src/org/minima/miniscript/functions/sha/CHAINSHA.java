package org.minima.miniscript.functions.sha;

import org.minima.miniscript.Contract;
import org.minima.miniscript.exceptions.ExecutionException;
import org.minima.miniscript.functions.MinimaFunction;
import org.minima.miniscript.values.HEXValue;
import org.minima.miniscript.values.Value;
import org.minima.objects.base.MiniData;
import org.minima.objects.proofs.Proof;

public class CHAINSHA extends MinimaFunction {

	public CHAINSHA() {
		super("CHAINSHA");
	}
	
	@Override
	public Value runFunction(Contract zContract) throws ExecutionException {
		//Get the input hash...
		HEXValue input = (HEXValue) getParameter(0).getValue(zContract);
		
		//Get the 32 byte hash data chain + 1 byte for left right 
		HEXValue chain = (HEXValue) getParameter(1).getValue(zContract);
		
		//Now cycle through..
		byte[] indata = input.getRawData();
		byte[] chdata = chain.getRawData();
		
		//indata must be 32 bytes long - FOR NOW
		if(indata.length != 32) {
			throw new ExecutionException("Input data must be 32 bytes long.");
		}
		
		//Create a proof..
		Proof chainproof = new Proof();
		chainproof.setData(new MiniData(indata));
		chainproof.setProof(chain.getMiniData());
		
		MiniData fv = chainproof.getFinalHash();
		
		//Return..
		return new HEXValue(fv.getData());
	}
	
	@Override
	public MinimaFunction getNewFunction() {
		return new CHAINSHA();
	}
}
