package com.takeaway.gameofthree;

import static org.assertj.core.api.Assertions.assertThatCode;

import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.takeaway.gameofthree.event.model.GameMoveEvent;
import com.takeaway.gameofthree.event.service.GameEventProducer;

public class GameEventProducerTest {

	  private GameEventProducer producer;
	  private RabbitTemplate rabbitTemplateMock;
	  
	  @BeforeEach
	  public void setUp() {
	    this.rabbitTemplateMock = Mockito.mock(RabbitTemplate.class);
	    this.producer = new GameEventProducer(this.rabbitTemplateMock);
	    ReflectionTestUtils.setField(producer, "player2RoutingKey", "gameofthree-player2");
	  }
	  
	  @Test
	  public void testCreateEvent() {
		  GameMoveEvent moveEvent = new GameMoveEvent(1, 5, "Player1");
	    assertThatCode(() -> this.producer.createEvent(moveEvent)).doesNotThrowAnyException();
	    Mockito.verify(this.rabbitTemplateMock)
	        .convertAndSend(eq("gameofthree-player2"), eq(moveEvent));
	  }
}
