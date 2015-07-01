/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elsibay.vertx;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.impl.codecs.JsonObjectMessageCodec;
import io.vertx.core.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tarek El-Sibay
 */
public class ExtendedJsonObjectCodec implements MessageCodec<ExtendedJsonObject, ExtendedJsonObject>{

	private static Logger logger = LoggerFactory.getLogger(ExtendedJsonObjectCodec.class);
	
	private JsonObjectMessageCodec jomCodec = new JsonObjectMessageCodec();
	
	@Override
	public void encodeToWire(Buffer buffer, ExtendedJsonObject s) {
		logger.debug("encode to wire");
		this.jomCodec.encodeToWire(buffer, s);
	}

	@Override
	public ExtendedJsonObject decodeFromWire(int pos, Buffer buffer) {
		logger.debug("decode from wire");
		JsonObject jo = this.jomCodec.decodeFromWire(pos, buffer);
		return new ExtendedJsonObject(jo.getMap());
	}

	@Override
	public ExtendedJsonObject transform(ExtendedJsonObject s) {
		logger.debug("transform");
		JsonObject jo = this.jomCodec.transform(s);
		return new ExtendedJsonObject(jo.getMap());
	}

	@Override
	public String name() {
		return "ejomCodec";
	}

	@Override
	public byte systemCodecID() {
		return -1;
	}
	
}
