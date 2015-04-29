/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elsibay.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tarek El-Sibay
 */
@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class DeliveryOptionsCodecTest {
	
	Logger logger  = LoggerFactory.getLogger(DeliveryOptionsCodecTest.class);

	static final Vertx vertx = Vertx.vertx();
		
	@Test
	public void testDeliveryOptionsCodec(TestContext context) {
		MessageCodec extJsonObjectCodec = new ExtendedJsonObjectCodec();
		DeliveryOptions deliveryOptions = new DeliveryOptions().setCodecName(extJsonObjectCodec.name());
		vertx.eventBus().registerCodec(extJsonObjectCodec);
		
		vertx.eventBus().consumer("consumer",(Message<ExtendedJsonObject> msg) -> {
			logger.info("received ExtendedJsonObject");
			msg.reply(new ExtendedJsonObject().put("status", "OK"),deliveryOptions);
		});
		
		Async replyAsync = context.async();
		
		ExtendedJsonObject request = new ExtendedJsonObject();
		vertx.eventBus().send("consumer",request, deliveryOptions, (AsyncResult<Message<ExtendedJsonObject>> asyncResult ) -> {
			logger.info("received reply");
			ExtendedJsonObject extJsonObj = asyncResult.result().body();
			context.assertNotNull(extJsonObj);
			context.assertEquals(extJsonObj.getStatus(),"OK");
			replyAsync.complete();
		});

	} 
}
